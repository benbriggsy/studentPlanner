/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author natha
 */
public class Student {
    
    private int studentID;
    private String fullName;
    private String userName;
    //private String password;
    private String emailAddress;
//    private enum schoolOfStudy{
//        AMA, BIO, CHE, CMP, DEV, ECO, EDU, ENV, HIS, HSC, LAW, LDC, MED, MTH, NBS, PHA, PPL, SWK
//    }
    private String schoolOfStudy;
    private int yearOfStudy;
    private ArrayList<Module> modules;
    private ArrayList<Milestone> milestones;
    //DASHBOARD CLASS MUST BE IMPLEMENTED FIRST
    //private Dashboard dashboard;
    public File semesterFile;
    
    public Student(int studentID, String fullName, String emailAddress, 
            String schoolOfStudy, int yearOfStudy, ArrayList<Module> modules){
        this.studentID = studentID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.schoolOfStudy = schoolOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.modules = modules;
    }
    
    public Student(File semesterFile) throws FileNotFoundException, IOException, ParseException{
        Scanner fileScan = new Scanner( semesterFile );
        
        ArrayList<Module> modulesFromFile = new ArrayList<>();
        String [] stu = fileScan.nextLine().split("/");
                this.studentID = Integer.parseInt(stu[0]);
                this.fullName = stu[1];
                this.userName = stu[2];
                this.emailAddress = stu[3];
                this.schoolOfStudy = stu[4];
                this.yearOfStudy = Integer.parseInt(stu[5]);
        while(fileScan.hasNextLine()){
                String [] details = fileScan.nextLine().split("/");
                Admin admin = new Admin(details[2], details[3]);
                boolean moduleCompleted = Boolean.parseBoolean(details[5]);
                Module module = new Module(details[0],details[1], admin,Double.parseDouble(details[4]),moduleCompleted,details[6] );
                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

                for(int i = 7; i<details.length;){
                    switch(details[i].charAt(0)){
                        case 'A':
                            boolean summative = Boolean.parseBoolean(details[i+7]);
                            Date assessmentDate = formatter.parse(details[i+4]);
                            Deadline assessmentDeadline = new Deadline(assessmentDate);
                            ArrayList<Task> assignmentTaskList = new ArrayList<>();

                            if(!"".equals(details[i+8])){
                                //System.out.println(details[i+8]);
                                String [] assignmentTasks = details[i+8].split("#");
                                for(int j=0; j<assignmentTasks.length;){
                                    ArrayList<Activity> assignmentTaskActivityList = new ArrayList<>();
                                    if(!"".equals(assignmentTasks[j+4])){
                                        String [] activityTaskActivities = assignmentTasks[j+4].split("~");
                                        for(int k=0; k<activityTaskActivities.length;k+=5){
                                            boolean activityComplete = Boolean.parseBoolean(activityTaskActivities[k+3]);

                                            Activity activity = new Activity(activityTaskActivities[k], activityTaskActivities[k+1],
                                            activityTaskActivities[k+4], activityComplete, Double.parseDouble(activityTaskActivities[k+2]));
                                            assignmentTaskActivityList.add(activity);

                                        }

                                    }
                                    boolean taskComplete = Boolean.parseBoolean(assignmentTasks[j+3]);
                                    Task task = new Task(assignmentTasks[j+1], assignmentTasks[j],
                                    assignmentTasks[j+5],assignmentTaskActivityList, null, Double.parseDouble(assignmentTasks[j+2]),
                                    taskComplete);

                                    assignmentTaskList.add(task);
                                    j+=6;
                                }

                            }
                            Assignment assignment = new Assignment(details[i+5], details[i+6],
                            summative, details[i], details[i+1], Double.parseDouble(details[i+2]),Double.parseDouble(details[i+3]),
                            assessmentDeadline,assignmentTaskList, details[i+9], "Assignment");
                            i+=10;
                            module.addAssessment(assignment);

                            for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
                                module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(assignment);
                            }
                            break;
                        case 'E':
                            Date examDate = formatter.parse(details[i+4]);
                            Deadline examDeadline = new Deadline(examDate);
                            ArrayList<Task> examTaskList = new ArrayList<>();
                            if(!"".equals(details[i+7])){
                                String [] examTasks = details[i+7].split("#");
                                for(int j=0; j<examTasks.length;){
                                    ArrayList<Activity> examTaskActivityList = new ArrayList<>();
                                    if(!"".equals(examTasks[j+4])){
                                        String [] examTaskActivities = examTasks[j+4].split("~");
                                        for(int k=0; k<examTaskActivities.length;k+=5){
                                            boolean activityComplete = Boolean.parseBoolean(examTaskActivities[k+3]);

                                            Activity activity = new Activity(examTaskActivities[k], examTaskActivities[k+1],
                                            examTaskActivities[k+4], activityComplete, Double.parseDouble(examTaskActivities[k+2]));
                                            examTaskActivityList.add(activity);

                                        }

                                    }
                                    boolean taskComplete = Boolean.parseBoolean(examTasks[j+3]);
                                    Task task = new Task(examTasks[j+1], examTasks[j],
                                    examTasks[j+5],examTaskActivityList, null, Double.parseDouble(examTasks[j+2]),
                                    taskComplete);

                                    examTaskList.add(task);
                                    j+=6;
                                }
                            }
                            Exam exam = new Exam(details[i+6], Integer.parseInt(details[i+5]), details[i], details[i+1], 
                                    Double.parseDouble(details[i+2]), Double.parseDouble(details[i+3]), examDeadline, examTaskList, details[i+8], "Exam" );
                            i+=9;
                            module.addAssessment(exam);
                            //System.out.println(exam);
                            for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
                                module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(exam);
                            }
                            break;
                    }
            
            }
            modulesFromFile.add(module);
        }
        this.modules = modulesFromFile;
        milestones = new ArrayList();
        this.semesterFile = semesterFile;
    }
    
    public int getStudentID(){
        return studentID;
    }
    
    public String getFullName(){
        return fullName;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }
    
    public String getSchoolOfStudy(){
        return schoolOfStudy;
    }
    
    public int getYearOfStudy(){
        return yearOfStudy;
    }
    
    public Module getModule(int i){
        return modules.get(i);
    }
    
    public ArrayList<Module> getModules(){
        return modules;
    }
    
    public Milestone getMilestone(int i){
        return milestones.get(i);
    }
    
    public ArrayList<Milestone> getMilestones(){
        return milestones;
    }
    
    public File getSemesterFile(){
        return semesterFile;
    }
    
    public void setFile(File f){
        semesterFile = f;
    }
    
    public void printSemesterFile() throws FileNotFoundException{
        Scanner fileScan = new Scanner( semesterFile );
        while(fileScan.hasNextLine()){
            String line = fileScan.nextLine();
            System.out.println(line);
        }
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Name: \t\t").append(fullName).append("\n");
        str.append("User Name: \t\t").append(userName).append("\n");
        str.append("Email:\t\t").append(emailAddress).append("\n");
        str.append("School:\t\t").append(schoolOfStudy).append("\n");
        str.append("Year: \t\t").append(yearOfStudy).append("\n");
        return str.toString();
    }
    
    public void updateFileForModule(Module mod) throws FileNotFoundException, IOException{
        Scanner fileScan = new Scanner( semesterFile );
        String updatedModuleString ="";
        
        while(fileScan.hasNextLine()){
            String [] module = fileScan.nextLine().split("/");
                
            if(module[0].equals(mod.getModuleCode())){
                updatedModuleString += mod.getModuleCode() + "/";
                updatedModuleString += mod.getModuleName()+ "/";
                updatedModuleString += mod.getModuleOrganiser().getName()+ "/";
                updatedModuleString += mod.getModuleOrganiser().getEmail()+ "/";
                updatedModuleString += Double.toString(mod.getCurrentGrade())+ "/";
                updatedModuleString += Boolean.toString(mod.getModuleCompleted()) + "/";
                updatedModuleString += mod.getNotes()+ "/";
                
                int assessmentIndex = 0;
                for(int i=7; i<module.length;){
                    
                    if(module[i].charAt(0) == 'A'){
                        Assignment assignment = (Assignment)mod.getAssessmentByIndex(assessmentIndex);
                        updatedModuleString += assignment.assignmentToFile()+ "/";
                        i+=10;
                    }
                    else if(module[i].charAt(0) == 'E'){
                        Exam exam = (Exam)mod.getAssessmentByIndex(assessmentIndex);
                        updatedModuleString += exam.examToFile()+ "/";
                        i+=9;
                    }
                    assessmentIndex++;
                }
                updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                updatedModuleString += "\n";
                
                
            }
            else{
                for(int i=0; i<module.length; i++){
                    updatedModuleString += module[i]+ "/";
                }
                updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                updatedModuleString += "\n";
            }
        }
        
        updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
    }
 
    
    public void updateFileForAssignment(Module mod,  Assignment assignment) throws IOException{
        
        
        Scanner fileScan = new Scanner( semesterFile );
        Format formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String updatedModuleString ="";
            while(fileScan.hasNextLine()){
                String [] module = fileScan.nextLine().split("/");
                
                if(module[0].equals(mod.getModuleCode())){
                    
                    String moduleString ="";
                    moduleString += module[0] + "/";
                    moduleString += module[1] + "/";
                    moduleString += module[2] + "/";
                    moduleString += module[3] + "/";
                    moduleString += module[4] + "/";
                    moduleString += module[5] + "/";
                    moduleString += module[6] + "/";
                    for(int i=7; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                    updatedModule[4] = Double.toString(mod.getCurrentGrade());
                    updatedModule[5] = Boolean.toString(mod.getModuleCompleted());
                    updatedModule[6] = mod.getNotes();
                            
                    for(int i=7; i<module.length;){
                        
                        if(module[i].charAt(0) == 'E'){
                        i+=9;
                        }
                        else if(module[i].equals(assignment.getAssessmentCode())){
                                updatedModule[i+1] = assignment.getAssessmentTitle();
                                updatedModule[i+2] = Double.toString(assignment.getWeighting());
                                updatedModule[i+3] = Double.toString(assignment.getGrade());
                                updatedModule[i+4] = formatter.format(assignment.getDeadline().getTime());
                                updatedModule[i+5] = assignment.getHandInProcedure();
                                updatedModule[i+6] = assignment.getAssignmentType();
                                updatedModule[i+7] = String.valueOf(assignment.getIsSummative());
                                updatedModule[i+8] = "";
                                for(Task task: assignment.getTasks()){
                                    updatedModule[i+8] += task.taskToFile();
                                    updatedModule[i+8] += "#";
                                }
                                updatedModule[i+8] = updatedModule[i+8].substring(0,updatedModule[i+8].length()-1);
                                updatedModule[i+9] = assignment.getNotes();
                              
                                
                         i+=10;    
                        }
                        
                    }
                    
                    
                    updatedModuleString += updatedModule[0] + "/";
                    updatedModuleString += updatedModule[1] + "/";
                    updatedModuleString += updatedModule[2] + "/";
                    updatedModuleString += updatedModule[3] + "/";
                    updatedModuleString += updatedModule[4] + "/";
                    updatedModuleString += updatedModule[5] + "/";
                    updatedModuleString += updatedModule[6] + "/";
                    for(int i=7; i< module.length; i++){
                        updatedModuleString += updatedModule[i] + "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
//                    System.out.println(moduleString);
//                       System.out.println(updatedModuleString);
//                       System.out.println("\n\n\n");
                }
                else{
                    for(int i=0; i<module.length; i++){
                        updatedModuleString += module[i]+ "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
                }
                
            }
            updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
            
    }
    
    
    public void updateFileForExam(Module mod,  Exam exam) throws IOException{
        Scanner fileScan = new Scanner( semesterFile );
        Format formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String updatedModuleString ="";
            while(fileScan.hasNextLine()){
                String [] module = fileScan.nextLine().split("/");
                
                if(module[0].equals(mod.getModuleCode())){
                    
                    String moduleString ="";
                    moduleString += module[0] + "/";
                    moduleString += module[1] + "/";
                    moduleString += module[2] + "/";
                    moduleString += module[3] + "/";
                    moduleString += module[4] + "/";
                    moduleString += module[5] + "/";
                    moduleString += module[6] + "/";
                    for(int i=7; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                    updatedModule[4] = Double.toString(mod.getCurrentGrade());
                    updatedModule[5] = Boolean.toString(mod.getModuleCompleted());
                    updatedModule[6] = mod.getNotes();
                    
                    for(int i=7; i<module.length;){
                        
                        if(module[i].charAt(0) == 'A'){
                        i+=10;
                        }
                        else if(module[i].equals(exam.getAssessmentCode())){
                                updatedModule[i+1] = exam.getAssessmentTitle();
                                updatedModule[i+2] = Double.toString(exam.getWeighting());
                                updatedModule[i+3] = Double.toString(exam.getGrade());
                                updatedModule[i+4] = formatter.format(exam.getDeadline().getTime());
                                updatedModule[i+5] = Integer.toString(exam.getExamDuration());
                                updatedModule[i+6] = exam.getExamRoom();
                                updatedModule[i+7] = "";
                                for(Task task: exam.getTasks()){
                                    updatedModule[i+7] += task.taskToFile();
                                    updatedModule[i+7] += "#";
                                }
                                updatedModule[i+7] = updatedModule[i+7].substring(0,updatedModule[i+7].length()-1);
                                updatedModule[i+8] = exam.getNotes(); 
                         i+=9;    
                        }
                    }
                    
                    updatedModuleString += updatedModule[0] + "/";
                    updatedModuleString += updatedModule[1] + "/";
                    updatedModuleString += updatedModule[2] + "/";
                    updatedModuleString += updatedModule[3] + "/";
                    updatedModuleString += updatedModule[4] + "/";
                    updatedModuleString += updatedModule[5] + "/";
                    updatedModuleString += updatedModule[6] + "/";
                    for(int i=7; i< module.length; i++){
                        updatedModuleString += updatedModule[i] + "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
//                    System.out.println(moduleString);
//                       System.out.println(updatedModuleString);
//                       System.out.println("\n\n\n");
                }
                else{
                    for(int i=0; i<module.length; i++){
                        updatedModuleString += module[i]+ "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
                }
                
            }
            updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
                
            }
            
    

    
    public void updateFileForTask(Module mod,  Task task) throws IOException{
        Scanner fileScan = new Scanner( semesterFile );
        String updatedModuleString ="";
            while(fileScan.hasNextLine()){
                String [] module = fileScan.nextLine().split("/");
                
                if(module[0].equals(mod.getModuleCode())){
                    
                    String moduleString ="";
                    moduleString += module[0] + "/";
                    moduleString += module[1] + "/";
                    moduleString += module[2] + "/";
                    moduleString += module[3] + "/";
                    moduleString += module[4] + "/";
                    moduleString += module[5] + "/";
                    moduleString += module[6] + "/";
                    
                    for(int i=7; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                    updatedModule[4] = Double.toString(mod.getCurrentGrade());
                    updatedModule[5] = Boolean.toString(mod.getModuleCompleted());
                    updatedModule[6] = mod.getNotes();
                    
                    for(int i=7; i<module.length;){
                        if(module[i].charAt(0) == 'A' && task.getTaskID().charAt(0) == 'a'){
                            String [] tasks = module[i+8].split("#");
                            for(int j=0; j<tasks.length; j+=6){
                                if(tasks[j].equals(task.getTaskID())){
                                    tasks[j] = task.getTaskID();
                                    tasks[j+1] = task.getTaskName();
                                    tasks[j+2] = Double.toString(task.getWeighting());
                                    tasks[j+3] = String.valueOf(task.isCompleted());
                                    tasks[j+4] = "";
                                    for(Activity activity: task.getActivities()){
                                        tasks[j+4] += activity.activityToFile();
                                        tasks[j+4] += "~";
                                    }   
                                    tasks[j+4] = tasks[j+4].substring(0,tasks[j+4].length()-1);
                                    tasks[j+5] = task.getNotes();
                                }
                            }
                                String updatedTasks = "";
                                for(int j=0; j<tasks.length; j++){
                                    updatedTasks += tasks[j] + "#";
                                }
                                updatedTasks = updatedTasks.substring(0,updatedTasks.length()-1);
                                updatedModule[i+8] = updatedTasks;
                             
                        i+=10;
                        }
                        else if(module[i].charAt(0) == 'E' && task.getTaskID().charAt(0) == 'e'){
                                String [] tasks = module[i+7].split("#");
                            for(int j=0; j<tasks.length; j+=6){
                                if(tasks[j].equals(task.getTaskID())){
                                    tasks[j] = task.getTaskID();
                                    tasks[j+1] = task.getTaskName();
                                    tasks[j+2] = Double.toString(task.getWeighting());
                                    tasks[j+3] = String.valueOf(task.isCompleted());
                                    tasks[j+4] = "";
                                    for(Activity activity: task.getActivities()){
                                        tasks[j+4] += activity.activityToFile();
                                        tasks[j+4] += "~";
                                    }   
                                    tasks[j+4] = tasks[j+4].substring(0,tasks[j+4].length()-1);
                                    tasks[j+5] = task.getNotes();
                                }
                            }
                            
                            
                                String updatedTasks = "";
                                for(int j=0; j<tasks.length; j++){
                                    updatedTasks += tasks[j] + "#";
                                }
                                updatedTasks = updatedTasks.substring(0,updatedTasks.length()-1);
                                updatedModule[i+7] = updatedTasks;
                            
                         i+=9;    
                        }
                        else if(module[i].charAt(0) == 'A'){
                            i+=10;
                        }
                        else if(module[i].charAt(0) == 'E'){
                            i+=9;
                        }
                    }
                    updatedModuleString += updatedModule[0] + "/";
                    updatedModuleString += updatedModule[1] + "/";
                    updatedModuleString += updatedModule[2] + "/";
                    updatedModuleString += updatedModule[3] + "/";
                    updatedModuleString += updatedModule[4] + "/";
                    updatedModuleString += updatedModule[5] + "/";
                    updatedModuleString += updatedModule[6] + "/";
                    for(int i=7; i< module.length; i++){
                        updatedModuleString += updatedModule[i] + "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
//                    System.out.println(moduleString);
//                       System.out.println(updatedModuleString);
//                       System.out.println("\n\n\n");
                }
                else{
                    for(int i=0; i<module.length; i++){
                        updatedModuleString += module[i]+ "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
                }
                
            }
            updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
    }
    
    public void addTaskToFile(Module mod, Task task) throws FileNotFoundException, IOException{
        Scanner fileScan = new Scanner( semesterFile );
        String updatedModuleString ="";
            while(fileScan.hasNextLine()){
                String [] module = fileScan.nextLine().split("/");
                if(module[0].equals(mod.getModuleCode())){
                    for(int i=7; i<module.length;){
                        if(module[i].charAt(0) == 'A' && task.getTaskID().charAt(0) == 'a'){
                            if(!"".equals(module[i+8])){
                            module[i+8] = module[i+8].substring(0,module[i+8].length()-1);
                            module[i+8] += "#";
                            }
                            module[i+8] += task.getTaskID() + "#";
                            module[i+8] += task.getTaskName() + "#";
                            module[i+8] += Double.toString(task.getWeighting()) + "#";
                            module[i+8] += String.valueOf(task.isCompleted()) + "#";
//                            for(Activity activity: task.getActivities()){
//                                module[i+8] += activity.activityToFile();
//                                module[i+8] += "~";
//                            } 
//                            module[i+8] = module[i+8].substring(0,module[i+8].length()-1);
                            module[i+8] += "#" + task.getNotes();
                           
                            i+=10; 
                        }
                        else if(module[i].charAt(0) == 'E' && task.getTaskID().charAt(0) == 'e'){
                            if(!"".equals(module[i+7])){
                            module[i+7] = module[i+7].substring(0,module[i+7].length()-1);
                            module[i+7] += "#";
                            }
                            module[i+7] += task.getTaskID() + "#";
                            module[i+7] += task.getTaskName() + "#";
                            module[i+7] += Double.toString(task.getWeighting()) + "#";
                            module[i+7] += String.valueOf(task.isCompleted()) + "#";
//                            for(Activity activity: task.getActivities()){
//                                module[i+7] += activity.activityToFile();
//                                module[i+7] += "~";
//                            } 
//                            module[i+7] = module[i+7].substring(0,module[i+7].length()-1);
                            module[i+7] += "#" + task.getNotes();
                            i+=9;
                        }
                        else if(module[i].charAt(0) == 'A'){
                            i+=10;
                        }
                        else if(module[i].charAt(0) == 'E'){
                            i+=9;
                        }
                        
                        
                    }
                }
                for(int i=0; i<module.length; i++){
                    updatedModuleString += module[i]+ "/";
                }
                updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                updatedModuleString += "\n";
            }
            
            updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
            
    }
    
    public void updateFileForActivity(Module mod, Activity activity) throws IOException{
        Scanner fileScan = new Scanner( semesterFile );
        String updatedModuleString ="";
            while(fileScan.hasNextLine()){
                String [] module = fileScan.nextLine().split("/");
                
                if(module[0].equals(mod.getModuleCode())){
                    
                    String moduleString ="";
                    moduleString += module[0] + "/";
                    moduleString += module[1] + "/";
                    moduleString += module[2] + "/";
                    moduleString += module[3] + "/";
                    moduleString += module[4] + "/";
                    moduleString += module[5] + "/";
                    moduleString += module[6] + "/";
                    for(int i=7; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                    updatedModule[4] = Double.toString(mod.getCurrentGrade());
                    updatedModule[5] = Boolean.toString(mod.getModuleCompleted());
                    updatedModule[6] = mod.getNotes();      
                    for(int i=7; i<module.length;){
                        if(module[i].charAt(0) == 'A' && activity.getActivityID().charAt(0) == 'a' && activity.getActivityID().charAt(1) == 'a'){
                            String [] tasks = module[i+8].split("#");
                            
                            for(int j=4; j<tasks.length; j+=6){
                                String [] activities =tasks[j].split("~");
                                for(int k=0; k<activities.length; k+=5){
                                    if(activities[k].equals(activity.getActivityID())){
                                        activities[k] = activity.getActivityID();
                                        activities[k+1] = activity.getActivityName();
                                        activities[k+2] = Double.toString(activity.getWeighting());
                                        activities[k+3] = String.valueOf(activity.isCompleted());
                                        activities[k+4] = activity.getNotes();
                                    }
                               }
                                String updatedActivities = "";
                                for(int k=0; k<activities.length; k++){
                                    updatedActivities += activities[k] + "~";
                                }
                                updatedActivities = updatedActivities.substring(0,updatedActivities.length()-1);
                                tasks[j] = updatedActivities;
                                String updatedTasks = "";
                                for(int k=0; k<tasks.length; k++){
                                    updatedTasks += tasks[k] + "#";
                                }
                                updatedTasks = updatedTasks.substring(0,updatedTasks.length()-1);
                                updatedModule[i+8] = updatedTasks;
                            }
                                
                             
                        i+=10;
                        }
                        else if(module[i].charAt(0) == 'E' && activity.getActivityID().charAt(0) == 'e' && activity.getActivityID().charAt(1) == 'a'){
                            
                            String [] tasks = module[i+7].split("#");
                            
                            for(int j=4; j<tasks.length; j+=6){
                                String [] activities =tasks[j].split("~");
                                for(int k=0; k<activities.length; k+=5){
                                    if(activities[k].equals(activity.getActivityID())){;
                                        activities[k] = activity.getActivityID();
                                        activities[k+1] = activity.getActivityName();
                                        activities[k+2] = Double.toString(activity.getWeighting());
                                        activities[k+3] = String.valueOf(activity.isCompleted());
                                        activities[k+4] = activity.getNotes();
                                    }
                                }
                                String updatedActivities = "";
                                for(int k=0; k<activities.length; k++){
                                    updatedActivities += activities[k] + "~";
                                }
                                updatedActivities = updatedActivities.substring(0,updatedActivities.length()-1);
                                tasks[j] = updatedActivities;
                                String updatedTasks = "";
                                for(int k=0; k<tasks.length; k++){
                                    updatedTasks += tasks[k] + "#";
                                }
                                updatedTasks = updatedTasks.substring(0,updatedTasks.length()-1);
                                updatedModule[i+7] = updatedTasks;
                            }
                         i+=9;    
                        }
                        else if(module[i].charAt(0) == 'A'){
                            //System.out.println("Assignment and Exam activity" + module[i]);
                            i+=10;
                        }
                        else if(module[i].charAt(0) == 'E'){
                            //System.out.println("Exam and assignment activity" + module[i]);
                            i+=9;
                        }
                    }
                    
                    updatedModuleString += updatedModule[0] + "/";
                    updatedModuleString += updatedModule[1] + "/";
                    updatedModuleString += updatedModule[2] + "/";
                    updatedModuleString += updatedModule[3] + "/";
                    updatedModuleString += updatedModule[4] + "/";
                    updatedModuleString += updatedModule[5] + "/";
                    updatedModuleString += updatedModule[6] + "/";
                    for(int i=7; i< module.length; i++){
                        updatedModuleString += updatedModule[i] + "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString +="\n";
//                    System.out.println(moduleString);
//                       System.out.println(updatedModuleString);
//                       System.out.println("\n\n\n");
                    
                }
                else{
                    for(int i=0; i<module.length; i++){
                        updatedModuleString += module[i]+ "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
                }
                
                
            }
            updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
    }
    
    public void addActivityToFile(Module mod, Task task, Activity activity) throws FileNotFoundException, IOException{
        Scanner fileScan = new Scanner( semesterFile );
        String updatedModuleString ="";
        while(fileScan.hasNextLine()){
            String [] module = fileScan.nextLine().split("/");
            if(module[0].equals(mod.getModuleCode())){
                for(int i=7; i<module.length;){
                    if(module[i].charAt(0) == 'A' && activity.getActivityID().charAt(0) == 'a' && activity.getActivityID().charAt(1) == 'a'){
                        String [] tasks = module[i+8].split("#");
                        for(int j=4; j<tasks.length; j+=6){
                            if(task.getTaskID().equals(tasks[j-4])){
                                if(!"".equals(tasks[j])){
                                    tasks[j] = tasks[j].substring(0,tasks[j].length()-1);
                                    tasks[j] += "~";
                                }
                                tasks[j] += activity.getActivityID() + "~";
                                tasks[j] += activity.getActivityName() + "~";
                                tasks[j] += Double.toString(activity.getWeighting()) + "~";
                                tasks[j] += String.valueOf(activity.isCompleted()) + "~";
                                tasks[j] += activity.getNotes();
                             } 
                        }
                        String updatedTasks = "";
                        for(int j=0; j<tasks.length; j++){
                            updatedTasks += tasks[j] + "#";
                        }
                        updatedTasks = updatedTasks.substring(0,updatedTasks.length()-1);
                        module[i+8] = updatedTasks;
                        //System.out.println(updatedTasks);
                        i+=10;
                    }
                    else if(module[i].charAt(0) == 'E' && activity.getActivityID().charAt(0) == 'e' && activity.getActivityID().charAt(1) == 'a'){
                        String [] tasks = module[i+7].split("#");
                        for(int j=4; j<tasks.length; j+=6){
                            if(task.getTaskID().equals(tasks[j-4])){
                                if(!"".equals(tasks[j])){
                                    tasks[j] = tasks[j].substring(0,tasks[j].length()-1);
                                    tasks[j] += "~";
                                }
                                tasks[j] += activity.getActivityID() + "~";
                                tasks[j] += activity.getActivityName() + "~";
                                tasks[j] += Double.toString(activity.getWeighting()) + "~";
                                tasks[j] += String.valueOf(activity.isCompleted()) + "~";
                                tasks[j] += activity.getNotes();
                             } 
                        }
                        String updatedTasks = "";
                        for(int j=0; j<tasks.length; j++){
                            updatedTasks += tasks[j] + "#";
                        }
                        updatedTasks = updatedTasks.substring(0,updatedTasks.length()-1);
                        module[i+7] = updatedTasks;
                        //System.out.println(updatedTasks);
                        i+=9;
                    }
                    else if(module[i].charAt(0) == 'A'){
                        i+=10;
                    }
                    else if(module[i].charAt(0) == 'E'){
                        i+=9;
                    }
                    
                }
                for(int i=0; i<module.length; i++){
                    updatedModuleString += module[i] + "/";
                }
                updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                updatedModuleString +="\n";
                
            }
            else{
                    for(int i=0; i<module.length; i++){
                        updatedModuleString += module[i]+ "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    updatedModuleString += "\n";
                }
        }
        updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream("semester.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    File file = new File("semester.txt");
                    this.semesterFile = file;
    }
    
     public static boolean checkFile(File semesterFile) throws FileNotFoundException, ParseException{
        boolean acceptable = true;
        String regExp = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?"
        + "(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p"
        + "{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))"
        + "[fFdD]?))[\\x00-\\x20]*";
        Scanner fileScan = new Scanner( semesterFile );
        
        String [] stu = fileScan.nextLine().split("/");
                for(int i=0; i<stu[0].length(); i++){
                    if(!Character.isDigit(stu[0].charAt(i))){
                        System.out.println("Error on line 1: studentID must only"
                                + " consist of numbers");
                        acceptable = false;
                    }     
                }
                for(int i=0; i<stu[1].length(); i++){
                    if(!Character.isLetter(stu[1].charAt(i)) && stu[1].charAt(i)!=' ' &&
                            stu[1].charAt(i) != '-'){
                        System.out.println("Error on line 1: A name can only "
                                + "contain letters, spaces and dashes");
                        acceptable = false;
                    }     
                }
                if(stu[2].length()!=8){
                    System.out.println("Error on line 1: A username must have"
                            + " 8 characers");
                    acceptable = false;
                }
                for(int i=0; i<stu[2].length(); i++){
                    if(i<3){  
                        if(!Character.isLetter(stu[2].charAt(i))){
                        System.out.println("Error on line 1: The first 3 characters"
                                + " of a username must be letters");
                        acceptable = false;
                        } 
                    }
                    else if(i<5){
                        if(!Character.isDigit(stu[2].charAt(i))){
                        System.out.println("Error on line 1: Characters 4 and 5 "
                                + "of a username bust be numbers");
                        acceptable = false;
                        } 
                    }
                    else{
                        if(!Character.isLetter(stu[2].charAt(i))){
                        System.out.println("Error on line 1: The last 3 characters"
                                + " of a username must be letters");
                        acceptable = false;
                        }
                    }
                }
                if(stu[3].contains("@")){
                    int pos = stu[3].indexOf("@");
                    if(pos+1 != stu[3].lastIndexOf("uea.ac.uk") ){
                        System.out.println("Error on line 1: and email must finish with"
                                + " \"@uea.ac.uk\"");
                        acceptable = false;
                    }
                }
                else{
                     System.out.println("Error on line 1: and email must finish with"
                                + " \"@uea.ac.uk\"");
                     acceptable = false;
                }
                
//                this.schoolOfStudy = stu[4];
//                this.yearOfStudy = Integer.parseInt(stu[5]);
        int line = 2;
        while(fileScan.hasNextLine()){
                String [] details = fileScan.nextLine().split("/");
                if(details[0].length()!=9){
                    System.out.println("Error on line " + line + ": A module code must have"
                            + " 9 characers");
                    acceptable = false;
                }
                for(int i=0; i<details[0].length(); i++){
                    if(i<3){  
                        if(!Character.isLetter(details[0].charAt(i))){
                        System.out.println("Error on line " + line + ": The first 3 characters"
                                + " of a module code must be letters");
                        acceptable = false;
                        } 
                    }
                    else if(i==3){
                        if(details[0].charAt(i)!='-'){
                        System.out.println("Error on line " + line + ": Character 4 "
                                + "of a module code must be a '-'");
                        acceptable = false;
                        } 
                    }
                    else if(i<8){
                        if(!Character.isDigit(details[0].charAt(i))){
                        System.out.println("Error on line " + line + ": Characters"
                                + " 5-8 of a module code must be numbers");
                        acceptable = false;
                        }
                    }
                    else{
                        if(!Character.isLetter(details[0].charAt(i))){
                        System.out.println("Error on line " + line + ": The last character"
                                + " of a module code must be a letter");
                        acceptable = false;
                        } 
                    }
                }
                
                for(int i=0; i<details[1].length(); i++){
                    if(!Character.isLetter(details[1].charAt(i)) && details[1].charAt(i)!=' ' &&
                            details[1].charAt(i) != '-' && !Character.isDigit(details[1].charAt(i))){
                        System.out.println("Error on line " + line + ": A module name can only contain "
                                + "characters, spaces, dashes and digits");
                        acceptable = false;
                    }
                }
                
                for(int i=0; i<details[2].length(); i++){
                    if(!Character.isLetter(details[2].charAt(i)) && details[2].charAt(i)!=' ' &&
                            details[2].charAt(i) != '-'){
                        System.out.println("Error on line " + line + ": An Andmin name can only "
                                + "contain letters, spaces and dashes");
                        acceptable = false;
                    }     
                }
                  if(details[3].contains("@")){
                    int pos = details[3].indexOf("@");
                    if(pos+1 != details[3].lastIndexOf("uea.ac.uk") ){
                        System.out.println("Error on line " + line + ": and email must finish with"
                                + " \"@uea.ac.uk\"");
                        acceptable = false;
                    }
                }
                else{
                     System.out.println("Error on line " + line + ": and email must finish with"
                                + " \"@uea.ac.uk\"");
                     acceptable = false;
                }
                if(!details[4].matches(regExp)){
                    System.out.println("Error on line " + line + ": The weighting for an"
                    + " assignment task must be a double");
                    acceptable = false;
                }
                if(!details[5].equals("true") && !details[5].equals("false")){
                    System.out.println("Error on line " + line + ": \""
                    + details[5] + "\" must be a boolean value");
                    acceptable = false;
                }
                  
                for(int i = 7; i<details.length;){
                    switch(details[i].charAt(0)){
                        case 'A':
                            if(!details[i+7].equals("true") && !details[i+7].equals("false")){
                                System.out.println("Error on line " + line + ": \""
                                + details[i+7] + "\" must be a boolean value");
                                acceptable = false;
                            }
                            
                            if(details[i+4].length()!= 19){
                                System.out.println("Error on line " + line + ": A deadline"
                                        + " must have 19 characters");
                                acceptable = false;
                            }
                            for(int j=0; j<details[i+4].length(); j++){
                                if(j == 4 || j==7){
                                    if(details[i+4].charAt(j) != '-'){
                                        System.out.println("Error on line " + line + ": The year, month and day"
                                                + " must be seperated with a '-'");
                                    acceptable = false;
                                    }
                                }
                                else if(j== 13|| j== 16){
                                    if(details[i+4].charAt(j) != ':'){
                                    System.out.println("Error on line " + line + ": The hours, minuts and"
                                                + " seconds must be seperated with a ':'");
                                    acceptable = false;
                                    }
                                }
                                else if(j==10){
                                    if(details[i+4].charAt(j)!= ' '){
                                        System.out.println("Error on line " + line + ": The date"
                                                + " and time must be seperated by a space");
                                    }
                                }
                                else{
                                    if(!Character.isDigit(details[i+4].charAt(j))){
                                        
                                        System.out.println("Error on line " + line + ": The year, "
                                                + "month, day, hours, minutes and seconds must be "
                                                + "represented as numbers");
                                        System.out.println(details[i+4].charAt(j));
                                    acceptable = false;
                                    }
                                    }
                                }
                            
                            Date date=new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            int year = calendar.get(Calendar.YEAR);
                            int nextYear = year + 1;
                            String y = details[i+4].substring(0, Math.min(details[i+4].length(), 4));
                            if(!y.equals(Integer.toString(year)) && !y.equals(Integer.toString(nextYear))){
                                System.out.println("Error on line " + line + ": The year for a deadline "
                                        + "must be this year or next year");
                                acceptable = false;
                            }
                            int month = Integer.parseInt(details[i+4].substring(5, Math.min(details[i+4].length(), 7)));
                            int day = Integer.parseInt(details[i+4].substring(8, Math.min(details[i+4].length(), 10)));
                            
                            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                                if(day > 31){
                                    System.out.println("Error on line " + line + ": There can't be "
                                    + day + "s in the " + month + " month (max 31 days)");
                                    acceptable = false;
                                }
                            }
                            else if(month == 4 || month == 6 || month == 9 || month == 11){
                                if(day > 30){
                                    System.out.println("Error on line " + line + ": There can't be "
                                    + day + "s in the " + month + " month (max 30 days)");
                                    acceptable = false;
                                }
                            }
                            else{
                                if(Integer.parseInt(y)%4 == 0){
                                    if(day > 29){
                                        System.out.println("Error on line " + line + ": There can't be "
                                        + day + "s in the " + month + " month in a leap year(max 29 days)");
                                        acceptable = false;
                                    }
                                }
                                else{
                                    if(day>28){
                                        System.out.println("Error on line " + line + ": There can't be "
                                        + day + "s in the " + month + " month(max 28 days)");
                                        acceptable = false;
                                    }
                                }
                            }

                            if(!"".equals(details[i+8])){
                                //System.out.println(details[i+8]);
                                String [] assignmentTasks = details[i+8].split("#");
                                for(int j=0; j<assignmentTasks.length;){
                                    if(!"".equals(assignmentTasks[j+4])){
                                        String [] activityTaskActivities = assignmentTasks[j+4].split("~");
                                        for(int k=0; k<activityTaskActivities.length;k+=5){
                                            
                                            if(!activityTaskActivities[k+3].equals("true") && 
                                                    !activityTaskActivities[k+3].equals("false")){
                                                System.out.println("Error on line " + line + ": \""
                                                + activityTaskActivities[k+3] + "\" must be a boolean value");
                                                acceptable = false;
                                            }
                                            
                                            if(activityTaskActivities[k].length()!= 5){
                                                System.out.println("Error on line " + line + ": The ID for an"
                                                        + " assignment activity must be 5 characters long");
                                                acceptable = false;
                                            }
                                                
                                            String activityID1 = activityTaskActivities[k].substring(0, Math.min(activityTaskActivities[k].length(), 2));
                                            if(!activityID1.equals("aa")){
                                                System.out.println("Error on line " + line + ": The ID for an"
                                                        + " assignment activity must start with \"aa\"");
                                                acceptable = false;
                                            }
                                            
                                            String activityID2 = activityTaskActivities[k].substring(2, Math.min(activityTaskActivities[k].length(), 25));
                                            for(int h=0; h<activityID2.length(); h++){
                                               if(!Character.isDigit(activityID2.charAt(h))){
                                                   System.out.println("Error on line " + line + ": The ID for an"
                                                        + " assignment activity must end in 3 digits");
                                                acceptable = false;
                                               }
                                            }
                                            
                                            if(!activityTaskActivities[k+2].matches(regExp)){
                                                System.out.println("Error on line " + line + ": The weighting for an"
                                                        + " assignment activity must be a double");
                                                acceptable = false;
                                            }

                                        }

                                    }
                                    if(!assignmentTasks[j+3].equals("true") && 
                                            !assignmentTasks[j+3].equals("false")){
                                        System.out.println("Error on line " + line + ": \""
                                        + assignmentTasks[j+3] + "\" must be a boolean value");
                                        acceptable = false;
                                    }
                                    
                                    if(assignmentTasks[j].length()!= 5){
                                                System.out.println("Error on line " + line + ": The ID for an"
                                                        + " assignment activity must be 5 characters long");
                                                acceptable = false;
                                    }
                                                
                                    String taskID1 = assignmentTasks[j].substring(0, Math.min(assignmentTasks[j].length(), 2));
                                    if(!taskID1.equals("aT")){
                                        System.out.println("Error on line " + line + ": The ID for an"
                                            + " assignment activity must start with \"aT\"");
                                        acceptable = false;
                                    }
                                            
                                    String taskID2 = assignmentTasks[j].substring(2, Math.min(assignmentTasks[j].length(), 25));
                                    for(int h=0; h<taskID2.length(); h++){
                                        if(!Character.isDigit(taskID2.charAt(h))){
                                            System.out.println("Error on line " + line + ": The ID for an"
                                                + " assignment activity must end in 3 digits");
                                                acceptable = false;
                                        }
                                    }
                                    
                                    if(!assignmentTasks[j+2].matches(regExp)){
                                        System.out.println("Error on line " + line + ": The weighting for an"
                                        + " assignment task must be a double");
                                        acceptable = false;
                                    }
                                    j+=6;
                                }

                            }
                            
                            for(int j=0; j<details[i+5].length(); j++){
                                if(!Character.isLetter(details[i+5].charAt(j)) &&
                                        details[i+5].charAt(j) != ' '){
                                    System.out.println("Error on line " + line + ": The hand in procedure "
                                            + "for an assignment can only contain letters and spaces");
                                        acceptable = false;
                                }
                            }
                            
                            for(int j=0; j<details[i+6].length(); j++){
                                if(!Character.isLetter(details[i+6].charAt(j)) &&
                                        details[i+6].charAt(j) != ' '){
                                    System.out.println("Error on line " + line + ": The assignment type "
                                            + "can only contain letters and spaces");
                                        acceptable = false;
                                }
                            }
                            
                            if(details[i].length()!= 4){
                            System.out.println("Error on line " + line + ": The assignment ID"
                                    + " must be 4 characters long");
                                        acceptable = false;
                            }
                            
                            if(details[i].charAt(0)!='A'){
                                System.out.println("Error on line " + line + ": The first character "
                                        + "for an assignment ID must be an 'A'");
                                        acceptable = false;
                            }
                            
                            for(int j=1; j<details[i].length(); j++){
                                if(!Character.isDigit(details[i].charAt(j))){
                                    System.out.println("Error on line " + line + ": The last 3 characters "
                                        + "for an assignment ID must be digits");
                                        acceptable = false;
                                }
                            }
                            
                            for(int j=0; j<details[i+1].length(); j++){
                                if(!Character.isLetter(details[i+1].charAt(j)) &&
                                        details[i+1].charAt(j) != ' ' && !Character.isDigit(details[i+1].charAt(j))
                                        && details[i+1].charAt(j) != '-'){
                                    System.out.println("Error on line " + line + ": The assignment name "
                                            + "can only contain letters, spaces, numbers and dashes");
                                        acceptable = false;
                                }
                            }
                            
                            if(!details[i+2].matches(regExp)){
                                        System.out.println("Error on line " + line + ": The weighting for an"
                                        + " assignment must be a double");
                                        acceptable = false;
                            }
                            
                            if(!details[i+3].matches(regExp)){
                                        System.out.println("Error on line " + line + ": The grade for an"
                                        + " assignment must be a double");
                                        acceptable = false;
                            }
                            i+=10;
                            break;
                        case 'E':
                            if(details[i+4].length()!= 19){
                                System.out.println("Error on line " + line + ": A deadline"
                                        + " must have 19 characters");
                                acceptable = false;
                            }
                            for(int j=0; j<details[i+4].length(); j++){
                                if(j == 4 || j==7){
                                    if(details[i+4].charAt(j) != '-'){
                                        System.out.println("Error on line " + line + ": The year, month and day"
                                                + " must be seperated with a '-'");
                                    acceptable = false;
                                    }
                                }
                                else if(j== 13|| j== 16){
                                    if(details[i+4].charAt(j) != ':'){
                                    System.out.println("Error on line " + line + ": The hours, minuts and"
                                                + " seconds must be seperated with a ':'");
                                    acceptable = false;
                                    }
                                }
                                
                                else if(j==10){
                                    if(details[i+4].charAt(j)!= ' '){
                                        System.out.println("Error on line " + line + ": The date"
                                                + " and time must be seperated by a space");
                                    }
                                }
                                else{
                                    if(!Character.isDigit(details[i+4].charAt(j))){
                                        System.out.println("Error on line " + line + ": The year, "
                                                + "month, day, hours, minuts and seconds must be "
                                                + "represented as numbers");
                                    acceptable = false;
                                    }
                                }
                            }
                            
                            Date examDate=new Date();
                            Calendar examCalendar = Calendar.getInstance();
                            examCalendar.setTime(examDate);
                            int examYear = examCalendar.get(Calendar.YEAR);
                            int nextExamYear = examYear + 1;
                            String examY = details[i+4].substring(0, Math.min(details[i+4].length(), 4));
                            if(!examY.equals(Integer.toString(examYear)) && !examY.equals(Integer.toString(nextExamYear))){
                                System.out.println("Error on line " + line + ": The year for a deadline "
                                        + "must be this year or next year");
                                acceptable = false;
                            }
                            int examMonth = Integer.parseInt(details[i+4].substring(5, Math.min(details[i+4].length(), 7)));
                            int examDay = Integer.parseInt(details[i+4].substring(8, Math.min(details[i+4].length(), 10)));
                            
                            if(examMonth == 1 || examMonth == 3 || examMonth == 5 || examMonth == 7 || examMonth == 8 || examMonth == 10 || examMonth == 12){
                                if(examDay > 31){
                                    System.out.println("Error on line " + line + ": There can't be "
                                    + examDay + "s in the " + examMonth + " month (max 31 days)");
                                    acceptable = false;
                                }
                            }
                            else if(examMonth == 4 || examMonth == 6 || examMonth == 9 || examMonth == 11){
                                if(examDay > 30){
                                    System.out.println("Error on line " + line + ": There can't be "
                                    + examDay + "s in the " + examMonth + " month (max 30 days)");
                                    acceptable = false;
                                }
                            }
                            else{
                                if(Integer.parseInt(examY)%4 == 0){
                                    if(examDay > 29){
                                        System.out.println("Error on line " + line + ": There can't be "
                                        + examDay + "s in the " + examMonth + " month in a leap year(max 29 days)");
                                        acceptable = false;
                                    }
                                }
                                else{
                                    if(examDay>28){
                                        System.out.println("Error on line " + line + ": There can't be "
                                        + examDay + "s in the " + examMonth + " month(max 28 days)");
                                        acceptable = false;
                                    }
                                }
                            }
                            if(!"".equals(details[i+7])){
                                String [] examTasks = details[i+7].split("#");
                                for(int j=0; j<examTasks.length;){
                                    ArrayList<Activity> examTaskActivityList = new ArrayList<>();
                                    if(!"".equals(examTasks[j+4])){
                                        String [] examTaskActivities = examTasks[j+4].split("~");
                                        for(int k=0; k<examTaskActivities.length;k+=5){
                                            if(!examTaskActivities[k+3].equals("true") && 
                                                    !examTaskActivities[k+3].equals("false")){
                                                System.out.println("Error on line " + line + ": \""
                                                + examTaskActivities[k+3] + "\" must be a boolean value");
                                                acceptable = false;
                                            }
                                            
                                            if(examTaskActivities[k].length()!= 5){
                                                System.out.println("Error on line " + line + ": The ID for an"
                                                        + " exam activity must be 5 characters long");
                                                System.out.println(examTaskActivities[k]);
                                                acceptable = false;
                                            }
                                                
                                            String activityID1 = examTaskActivities[k].substring(0, Math.min(examTaskActivities[k].length(), 2));
                                            if(!activityID1.equals("ea")){
                                                System.out.println("Error on line " + line + ": The ID for an"
                                                        + " exam activity must start with \"ea\"");
                                                acceptable = false;
                                            }
                                            
                                            String activityID2 = examTaskActivities[k].substring(2, Math.min(examTaskActivities[k].length(), 25));
                                            for(int h=0; h<activityID2.length(); h++){
                                               if(!Character.isDigit(activityID2.charAt(h))){
                                                   System.out.println("Error on line " + line + ": The ID for an"
                                                        + "exam activity must end in 3 digits");
                                                acceptable = false;
                                               }
                                            }
                                            
                                            if(!examTaskActivities[k+2].matches(regExp)){
                                                System.out.println("Error on line " + line + ": The weighting for an"
                                                        + " exam activity must be a double");
                                                acceptable = false;
                                            }

                                        }

                                    }
                                    if(!examTasks[j+3].equals("true") && 
                                            !examTasks[j+3].equals("false")){
                                        System.out.println("Error on line " + line + ": \""
                                        + examTasks[j+3] + "\" must be a boolean value");
                                        acceptable = false;
                                    }
                                    
                                    if(examTasks[j].length()!= 5){
                                                System.out.println("Error on line " + line + ": The ID for an"
                                                        + " exam task must be 5 characters long");
                                                System.out.println(examTasks[j]);
                                                acceptable = false;
                                    }
                                                
                                    String taskID1 = examTasks[j].substring(0, Math.min(examTasks[j].length(), 2));
                                    if(!taskID1.equals("eT")){
                                        System.out.println("Error on line " + line + ": The ID for an"
                                            + " exam activity must start with \"eT\"");
                                        System.out.println(examTasks[j]);
                                        acceptable = false;
                                    }
                                            
                                    String taskID2 = examTasks[j].substring(2, Math.min(examTasks[j].length(), 25));
                                    for(int h=0; h<taskID2.length(); h++){
                                        if(!Character.isDigit(taskID2.charAt(h))){
                                            System.out.println("Error on line " + line + ": The ID for an"
                                                + " exam activity must end in 3 digits");
                                                acceptable = false;
                                        }
                                    }
                                    
                                    if(!examTasks[j+2].matches(regExp)){
                                        System.out.println("Error on line " + line + ": The weighting for an"
                                        + " exam task must be a double");
                                        acceptable = false;
                                    }
                                    j+=6;
                                }
                            }
                            for(int j=0; j<details[i+5].length(); j++){
                                if(!Character.isDigit(details[i+5].charAt(j))){
                                    System.out.println("Error on line " + line + ": The duration for an"
                                        + " exam task must be an integer");
                                        acceptable = false;
                                }
                            }
                            
                             if(details[i].length()!= 4){
                            System.out.println("Error on line " + line + ": The exam ID"
                                    + " must be 4 characters long");
                                        acceptable = false;
                            }
                            
                            if(details[i].charAt(0)!='E'){
                                System.out.println("Error on line " + line + ": The first character "
                                        + "for an exam ID must be an 'E'");
                                        acceptable = false;
                            }
                            
                            for(int j=1; j<details[i].length(); j++){
                                if(!Character.isDigit(details[i].charAt(j))){
                                    System.out.println("Error on line " + line + ": The last 3 characters "
                                        + "for an exam ID must be digits");
                                        acceptable = false;
                                }
                            }
                            
                             for(int j=0; j<details[i+1].length(); j++){
                                if(!Character.isLetter(details[i+1].charAt(j)) &&
                                        details[i+1].charAt(j) != ' ' && !Character.isDigit(details[i+1].charAt(j))
                                        && details[i+1].charAt(j) != '-'){
                                    System.out.println("Error on line " + line + ": The exam name "
                                            + "can only contain letters, spaces, numbers and dashes");
                                        acceptable = false;
                                }
                            }
                            
                            if(!details[i+2].matches(regExp)){
                                        System.out.println("Error on line " + line + ": The weighting for an"
                                        + " exam must be a double");
                                        acceptable = false;
                            }
                            
                            if(!details[i+3].matches(regExp)){
                                        System.out.println("Error on line " + line + ": The grade for an"
                                        + " exam must be a double");
                                        acceptable = false;
                            }
                            i+=9;
                            break;
                    }
            
            }
            line++;
        }
        return acceptable;
     }

    
    
     public static void main(String[] args) throws IOException, ParseException{
        File semesterFile = new File("semester.txt");
        boolean read = checkFile(semesterFile);
        System.out.println(read);
        
        if(read){
        Student student = new Student(semesterFile);
        System.out.println(student);
        
        
        
//        Date date = new Date();
//        Deadline deadline = new Deadline(date);
//        ArrayList<Task> tasks = new ArrayList<>();
//        Assignment assignment = new Assignment("Online", "Individual", true, "A002", "Assessment 2", 35.0, 0.0, deadline, tasks, "notes" );
//        student.getModule(0).addAssessment(assignment);
//        
//        System.out.println(student.getModule(0).getAssessmentByIndex(2));
    

    if(student.getModule(0).getAssessmentByIndex(1) instanceof Assignment){
        Assignment assignment = (Assignment)student.getModule(0).getAssessmentByIndex(1);
        assignment.setGrade(70.0);
    student.updateFileForAssignment(student.getModule(0) , assignment);
    }
    
//        Scanner fileScan = new Scanner( semesterFile );
//        String [] module = fileScan.nextLine().split("/");
//        String moduleString ="";
//                    moduleString += module[0] + "/";
//                    moduleString += module[1] + "/";
//                    moduleString += module[2] + "/";
//                    moduleString += module[3] + "/";
//                    
//                    for(int i=4; i< module.length; i++){
//                        moduleString += module[i] + "/";
//                    }
//                    moduleString = moduleString.substring(0,moduleString.length()-1);
//                    
//                    System.out.println(moduleString);
                    
                    
       if(student.getModule(0).getAssessmentByIndex(0) instanceof Exam){
        Exam exam = (Exam)student.getModule(0).getAssessmentByIndex(0);
        exam.setGrade(80.0);
    student.updateFileForExam(student.getModule(0) , exam);
    }
       
       student.getModule(0).setModuleCompleted(true);
       student.updateFileForModule(student.getModule(0));
       
       student.getModule(0).getAssessmentByIndex(0).getTask(0).setAsCompleted();
       student.updateFileForTask(student.getModule(0) , student.getModule(0).getAssessmentByIndex(0).getTask(0));
       
       student.getModule(0).getAssessmentByIndex(1).getTask(0).setAsCompleted();
       student.updateFileForTask(student.getModule(0) , student.getModule(0).getAssessmentByIndex(1).getTask(0));
//       
       student.getModule(0).getAssessmentByIndex(0).getTask(0).getActivityByIndex(0).setAsCompleted();
       student.updateFileForActivity(student.getModule(0) , student.getModule(0).getAssessmentByIndex(0).getTask(0).getActivityByIndex(0));
    
       student.getModule(0).getAssessmentByIndex(1).getTask(0).getActivityByIndex(0).setAsCompleted();
       student.updateFileForActivity(student.getModule(0) ,student.getModule(0).getAssessmentByIndex(1).getTask(0).getActivityByIndex(0));
    
       for(int i=0; i<student.getModules().size();i++){
         System.out.println(student.getModule(i));
                 }
       student.printSemesterFile();
       
       System.out.println("\nADD TASK \n");
       ArrayList<Activity> taskActivities = new ArrayList<>();
       Task task = new Task("I'VE JUST ADDED THIS TASK DOES IT WORK","aT303", "this is a note", taskActivities, student.getModule(3).getAssessmentByIndex(1)
       ,23.0,false);
       student.getModule(3).getAssessmentByIndex(1).addTask(task);
       student.addTaskToFile(student.getModule(3) , task);
       student.printSemesterFile();
       
       System.out.println("ADD ACTIVITY \n");
       Activity act = new Activity("aa303", "WILL THIS WORK WHO KNOWS", "this is a note", false, 10.0);
       student.getModule(3).getAssessmentByIndex(1).getTask(0).addActivity(act);
       student.addActivityToFile(student.getModule(3) ,student.getModule(3).getAssessmentByIndex(1).getTask(1), act);
       student.printSemesterFile();
       
       

        }
    }
}
