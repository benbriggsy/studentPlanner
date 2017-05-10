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
public class Activity {
    
    private String activityName;
    private String activityID;
    private String notes;
    //private ArrayList<Task> taskList;
    private boolean completed;
    private double weighting;
    
    public Activity(String activityID, String activityName, String notes,
            boolean completed, double weighting){
        this.activityID = activityID;
        this.activityName = activityName;
        this.notes = notes;
        this.completed = completed;
        this.weighting = weighting;
    }
    
    public String getActivityID(){
        return activityID;
    }
    
    public String getActivityName(){
        return activityName;
    }
    
//    public ArrayList<Task> getTasks(){
//        return taskList;
//    }
    
    public String getNotes(){
        return notes;
    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public double getWeighting(){
        return weighting;
    }
    
//    public void addTask(Task task){
//        taskList.add(task);
//    }
    
    public void setAsCompleted(){
        completed = true;
    }
    
    public String activityToFile(){
        String activity = "";
        activity+=activityID;
        activity+="~";
        activity+=activityName;
        activity+="~";
        activity+=weighting;
        activity+="~";
        activity+=completed;
        activity+="~";
        activity+=notes;
        
        return activity;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(activityName).append("\t").append(activityID).append("\n");
        str.append("Weighting:\t\t").append(weighting).append("\n");
        str.append("Completed:\t\t").append(completed).append("\n");
        str.append("Notes:\t\t").append(notes).append("\n");
        return str.toString();
    }
}
