/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;
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
    private Date startDate;
    private Date finishDate;
    private long timeSpent;
    
    public Activity(String activityID, String activityName, String notes,
            boolean completed, double weighting, Date startDate, Date finishDate){
        this.activityID = activityID;
        this.activityName = activityName;
        this.notes = notes;
        this.completed = completed;
        this.weighting = weighting;
        if(startDate == null){
            Date date = new Date();
            this.startDate = date;
        }
        else{
            this.startDate = startDate;
        }
        if(finishDate==null){
            this.finishDate = new Date();
            long secs = (this.finishDate.getTime() - this.startDate.getTime())/1000;
            this.timeSpent = secs / 3600;
        }
        else{
            this.finishDate = finishDate;
            long secs = (this.finishDate.getTime() - this.startDate.getTime())/1000;
            this.timeSpent = secs / 3600;
        }
        
    }
    
    public Date getStartDate(){
        return startDate;
    }
    public Date getEndDate(){
        return finishDate;
    }
    public long getTimeSpent(){
        return timeSpent;
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
    
    public void setName(String name){
        activityName = name;
    }
    
    public void setNotes(String notes){
        this.notes = notes;
    }
    
    public void setWeighting(double weighting){
        this.weighting = weighting;
    }
    
    public void setCompleted(boolean b){
        completed = b;
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
        activity+=startDate;
        activity+="~";
        activity+=finishDate;
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
