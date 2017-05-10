/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author me
 */
public class Dashboard {

    //ArrayList<Module> modules;
    ArrayList<Milestone> milestones;
    Student student;
    static Student setSemesterFile(File semesterFile) throws FileNotFoundException, IOException, ParseException {
        Scanner fileScan = new Scanner( semesterFile );
        
        ArrayList<Module> modules = new ArrayList<>();
        while(fileScan.hasNextLine()){
             
            String [] details = fileScan.nextLine().split("/");
            Admin admin = new Admin(details[2], details[3]);
            Module module = new Module(details[0],details[1], admin);
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            
            
            for(int i = 4; i<details.length;){
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
                        assessmentDeadline,assignmentTaskList, details[i+9]);
                        i+=10;
                        module.addAssessment(assignment);
                        
                        for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
                            module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(assignment);
                        }
                        
                        for(Task task: module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks()){
                            System.out.println(task);
                            for(Activity activity: task.getActivities()){
                                System.out.println(activity);
                            }
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
                                Double.parseDouble(details[i+2]), Double.parseDouble(details[i+3]), examDeadline, examTaskList, details[i+8] );
                        i+=9;
                        module.addAssessment(exam);
                        //System.out.println(exam);
                        for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
                            module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(exam);
                        }
                        
                        
                        for(Task task: module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks()){
                            System.out.println(task);
                            for(Activity activity: task.getActivities()){
                                System.out.println(activity);
                            }
                        }
                        break;
                }
            }
            System.out.println(module);
            modules.add(module);
        }  
        
         Student student = new Student(001, "Phillip Perks", "phillip@uea.ac.uk", 
            "CMP", 2, modules);
         
         return student;
    }
    
    static File updateFile(File semesterFile){
        return semesterFile;
    }

//    Module getModule(int i) {
//        return modules.get(i);
//    }
//
//    ArrayList<Module> getModules() {
//        return modules;
//    }

    Milestone getMilestone(int i) {
        return milestones.get(i);
    }

    ArrayList<Milestone> getMilestones() {
        return milestones;
    }
    
    public static void main(String[] args) throws IOException, ParseException{
        File semesterFile = new File("semester.txt");
        Student student = setSemesterFile(semesterFile);
        
        
    
    }

}
