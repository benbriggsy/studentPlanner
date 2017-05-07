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
    private int activityID;
    private String notes;
    private ArrayList<Task> taskList;
    private boolean completed;
    private double weighting;
    
    public Activity(int activityID, String activityName, String notes, ArrayList<Task> taskList,
            boolean completed, double weighting){
        this.activityID = activityID;
        this.activityName = activityName;
        this.notes = notes;
        this.taskList = taskList;
        this.completed = completed;
        this.weighting = weighting;
    }
    
    public int getActivityID(){
        return activityID;
    }
    
    public String getActivityName(){
        return activityName;
    }
    
    public ArrayList<Task> getTasks(){
        return taskList;
    }
    
    public String getNotes(){
        return notes;
    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public double getWeighting(){
        return weighting;
    }
    
    public void addTask(Task task){
        taskList.add(task);
    }
    
    public void setAsCompleted(){
        completed = true;
    }
}
