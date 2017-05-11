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
    private String password;
    private String emailAddress;
    //THIS CAN BE AN ENUM
    private String schoolOfStudy;
    private int yearOfStudy;
    private ArrayList<Module> modules;
    private ArrayList<Milestone> milestones;
    //DASHBOARD CLASS MUST BE IMPLEMENTED FIRST
    private Dashboard dashboard;
    public File semesterFile;
    
    public Student(int studentID, String fullName, String emailAddress, 
            String schoolOfStudy, int yearOfStudy, ArrayList<Module> modules){
        this.studentID = studentID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.schoolOfStudy = schoolOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.modules = modules;
        //this.semesterFile = semesterFile;
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
        str.append("Email:\t\t").append(emailAddress).append("\n");
        str.append("School:\t\t").append(schoolOfStudy).append("\n");
        return str.toString();
    }
    
    void setSemesterFile(File semesterFile) throws FileNotFoundException, IOException, ParseException {
        Scanner fileScan = new Scanner( semesterFile );
        
        while(fileScan.hasNextLine()){
             
            String [] details = fileScan.nextLine().split("/");
            Admin admin = new Admin(details[2], details[3]);
            Module module = new Module(details[0],details[1], admin);
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            ArrayList<Task> taskList = new ArrayList<>();
            
            for(int i = 4; i<details.length;){
                switch(details[i].charAt(0)){
                    case 'A':
                        boolean summative = Boolean.parseBoolean(details[i+6]);
                        Date assessmentDate = formatter.parse(details[i+3]);
                        Deadline assessmentDeadline = new Deadline(assessmentDate);
                        Assignment assignment = new Assignment(details[i+4], details[i+5],
                        summative, details[i], details[i+1], Double.parseDouble(details[i+2]),0.0,
                        assessmentDeadline,taskList, null);
                        i+=7;
                        module.addAssessment(assignment);
                        break;
                        
                    case 'E':
                        Date examDate = formatter.parse(details[i+3]);
                        Deadline examDeadline = new Deadline(examDate);
                        Exam exam = new Exam(details[i+5], Integer.parseInt(details[i+4]), details[i], details[i+1], 
                                Double.parseDouble(details[i+2]), 0.0, examDeadline, taskList, null );
                        i+=6;
                        module.addAssessment(exam);
                        break;
                }
            }
            modules.add(module);
        }  
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
                    
                    for(int i=4; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                            
                    for(int i=4; i<module.length;){
                        
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
                    
                    for(int i=4; i< module.length; i++){
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
                    
                    for(int i=4; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                            
                    for(int i=4; i<module.length;){
                        
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
                    
                    for(int i=4; i< module.length; i++){
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
                    
                    for(int i=4; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                            
                    for(int i=4; i<module.length;){
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
                    
                    for(int i=4; i< module.length; i++){
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
    
    public void updateFileForActivity(Module mod, Task t,  Activity activity) throws IOException{
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
                    
                    for(int i=4; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                            
                    for(int i=4; i<module.length;){
                        if(module[i].charAt(0) == 'A' && activity.getActivityID().charAt(0) == 'a' && activity.getActivityID().charAt(1) == 'a'){
                            String [] tasks = module[i+8].split("#");
                            
                            for(int j=4; j<tasks.length; j+=6){
                                if(t.getTaskID().equals(tasks[j-4])){
                                    
                                
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
                            }
                                
                             
                        i+=10;
                        }
                        else if(module[i].charAt(0) == 'E' && activity.getActivityID().charAt(0) == 'e' && activity.getActivityID().charAt(1) == 'a'){
                            
                            String [] tasks = module[i+7].split("#");
                            
                            for(int j=4; j<tasks.length; j+=6){
                                if(t.getTaskID().equals(tasks[j-4])){
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
                    
                    for(int i=4; i< module.length; i++){
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
}
