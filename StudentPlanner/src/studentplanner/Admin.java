/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author phillipperks
 */
public class Admin {
    private ArrayList<Module> modules;
    private String name;
    private String email;
    
    public Admin(String name, String email){
        this.name = name;
        this.email = email;
        this.modules = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public ArrayList<Module> getModules(){
        return modules;
    }
    
    public void setDeadline(Assessment assessment, Deadline deadline){
        assessment.setDeadline(deadline);
    }
    
//    public void checkFile(File semesterFile) throws FileNotFoundException{
//        boolean acceptable = true;
//        Scanner fileScan = new Scanner( semesterFile );
//        
//        ArrayList<Module> modulesFromFile = new ArrayList<>();
//        String [] stu = fileScan.nextLine().split("/");
//                for(int i=0; i<stu[0].length(); i++){
//                    if(!Character.isDigit(stu[0].charAt(i))){
//                        System.out.println("Error on line 1: studentID must only"
//                                + "consist of numbers");
//                    }     
//                }
//                for(int i=0; i<stu[1].length(); i++){
//                    if(!Character.isLetter(stu[0].charAt(i)) || stu[0].charAt(i)!=' ' ||
//                            stu[0].charAt(i) != '-'){
//                        System.out.println("Error on line 1: A name can only "
//                                + "contain letters, spaces and dashes");
//                    }     
//                }
//                if(stu[2].length()!=8){
//                    System.out.println("Error on line 1: A username must have"
//                            + "8 characers");
//                }
//                for(int i=0; i<stu[2].length(); i++){
//                    if(i<3){  
//                        if(!Character.isLetter(stu[2].charAt(i))){
//                        System.out.println("Error on line 1: The first 3 characters"
//                                + " of a username must be letters");
//                        } 
//                    }
//                    else if(i<5){
//                        if(!Character.isDigit(stu[2].charAt(i))){
//                        System.out.println("Error on line 1: Characters 4 and 5 "
//                                + "of a username bust be numbers");
//                        } 
//                    }
//                    else{
//                        if(!Character.isLetter(stu[2].charAt(i))){
//                        System.out.println("Error on line 1: The last 3 characters"
//                                + " of a username must be letters");
//                        }
//                    }
//                }
//                
//                this.emailAddress = stu[3];
//                this.schoolOfStudy = stu[4];
//                this.yearOfStudy = Integer.parseInt(stu[5]);
//        while(fileScan.hasNextLine()){
//                String [] details = fileScan.nextLine().split("/");
//                Admin admin = new Admin(details[2], details[3]);
//                Module module = new Module(details[0],details[1], admin);
//                SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
//
//                for(int i = 4; i<details.length;){
//                    switch(details[i].charAt(0)){
//                        case 'A':
//                            boolean summative = Boolean.parseBoolean(details[i+7]);
//                            Date assessmentDate = formatter.parse(details[i+4]);
//                            Deadline assessmentDeadline = new Deadline(assessmentDate);
//                            ArrayList<Task> assignmentTaskList = new ArrayList<>();
//
//                            if(!"".equals(details[i+8])){
//                                //System.out.println(details[i+8]);
//                                String [] assignmentTasks = details[i+8].split("#");
//                                for(int j=0; j<assignmentTasks.length;){
//                                    ArrayList<Activity> assignmentTaskActivityList = new ArrayList<>();
//                                    if(!"".equals(assignmentTasks[j+4])){
//                                        String [] activityTaskActivities = assignmentTasks[j+4].split("~");
//                                        for(int k=0; k<activityTaskActivities.length;k+=5){
//                                            boolean activityComplete = Boolean.parseBoolean(activityTaskActivities[k+3]);
//
//                                            Activity activity = new Activity(activityTaskActivities[k], activityTaskActivities[k+1],
//                                            activityTaskActivities[k+4], activityComplete, Double.parseDouble(activityTaskActivities[k+2]));
//                                            assignmentTaskActivityList.add(activity);
//
//                                        }
//
//                                    }
//                                    boolean taskComplete = Boolean.parseBoolean(assignmentTasks[j+3]);
//                                    Task task = new Task(assignmentTasks[j+1], assignmentTasks[j],
//                                    assignmentTasks[j+5],assignmentTaskActivityList, null, Double.parseDouble(assignmentTasks[j+2]),
//                                    taskComplete);
//
//                                    assignmentTaskList.add(task);
//                                    j+=6;
//                                }
//
//                            }
//                            Assignment assignment = new Assignment(details[i+5], details[i+6],
//                            summative, details[i], details[i+1], Double.parseDouble(details[i+2]),Double.parseDouble(details[i+3]),
//                            assessmentDeadline,assignmentTaskList, details[i+9]);
//                            i+=10;
//                            module.addAssessment(assignment);
//
//                            for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
//                                module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(assignment);
//                            }
//                            break;
//                        case 'E':
//                            Date examDate = formatter.parse(details[i+4]);
//                            Deadline examDeadline = new Deadline(examDate);
//                            ArrayList<Task> examTaskList = new ArrayList<>();
//                            if(!"".equals(details[i+7])){
//                                String [] examTasks = details[i+7].split("#");
//                                for(int j=0; j<examTasks.length;){
//                                    ArrayList<Activity> examTaskActivityList = new ArrayList<>();
//                                    if(!"".equals(examTasks[j+4])){
//                                        String [] examTaskActivities = examTasks[j+4].split("~");
//                                        for(int k=0; k<examTaskActivities.length;k+=5){
//                                            boolean activityComplete = Boolean.parseBoolean(examTaskActivities[k+3]);
//
//                                            Activity activity = new Activity(examTaskActivities[k], examTaskActivities[k+1],
//                                            examTaskActivities[k+4], activityComplete, Double.parseDouble(examTaskActivities[k+2]));
//                                            examTaskActivityList.add(activity);
//
//                                        }
//
//                                    }
//                                    boolean taskComplete = Boolean.parseBoolean(examTasks[j+3]);
//                                    Task task = new Task(examTasks[j+1], examTasks[j],
//                                    examTasks[j+5],examTaskActivityList, null, Double.parseDouble(examTasks[j+2]),
//                                    taskComplete);
//
//                                    examTaskList.add(task);
//                                    j+=6;
//                                }
//                            }
//                            Exam exam = new Exam(details[i+6], Integer.parseInt(details[i+5]), details[i], details[i+1], 
//                                    Double.parseDouble(details[i+2]), Double.parseDouble(details[i+3]), examDeadline, examTaskList, details[i+8] );
//                            i+=9;
//                            module.addAssessment(exam);
//                            //System.out.println(exam);
//                            for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
//                                module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(exam);
//                            }
//                            break;
//                    }
//            
//            }
//            modulesFromFile.add(module);
//        }
//        
//    }
    
    @Override
    public String toString(){
        StringBuilder admin = new StringBuilder();
        admin.append(name).append("\n").append(email).append("\n");
        return admin.toString();
    }
    
    public static void main(String[] args) throws IOException, ParseException{
        File semesterFile = new File("semester.txt");
        Admin HUB = new Admin("HUB", "hub@uea.ac.uk");
    
    }
}
