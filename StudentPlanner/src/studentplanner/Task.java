/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;

/**
 *
 * @author natha
 */
public class Task {
    
    private String taskName;
    private int taskID;
    private String notes;
    private ArrayList<Activity> activityList;
    private Assessment assessment;
    private double weighting;
    //private Progress progress;
    
//    Task(String taskName, int taskID, String notes, ArrayList<Activity> activityList, 
//            Assessment assessment, double weighting, Progress progress ){
//        this.taskName = taskName;
//        this.taskID = taskID;
//        this.assessment = assessment;
//        this.notes = notes;
//        this.weighting = weighting;
//        this.activityList = activityList;
//        this.progress = progress;
//    }
    
    public int getTaskID(){
        return taskID;
    }
    
    public String getTaskName(){
        return taskName;
    }
    
    public Assessment getAssessment(){
        return assessment;
    }
    
    public ArrayList<Activity> getActivities(){
        return activityList;
    }
    
    public double getWeighting(){
        return weighting;
    }
    
    public String getNotes(){
        return notes;
    }
    
//    public String getProgress(){
//        return progress;
//    }
    
//    public void updateProgress(Progress progress){
//        this.progress = progress;
//    }
    
    public void addActivity(Activity a){
        activityList.add(a);
    }
}
