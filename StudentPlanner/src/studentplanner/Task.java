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
    private String taskID;
    private String notes;
    private ArrayList<Activity> activities;
    private Assessment assessment;
    private double weighting;
    private boolean completed;
    
    Task(String taskName, String taskID, String notes, ArrayList<Activity> activies, 
            Assessment assessment, double weighting, boolean completed){
        this.taskName = taskName;
        this.taskID = taskID;
        this.assessment = assessment;
        this.notes = notes;
        this.weighting = weighting;
        this.activities = activies;
        this.completed = completed;
    }
    
    public String getTaskID(){
        return taskID;
    }
    
    public String getTaskName(){
        return taskName;
    }
    
    public Assessment getAssessment(){
        return assessment;
    }
    
    public ArrayList<Activity> getActivities(){
        return activities;
    }
    
    public double getWeighting(){
        return weighting;
    }
    
    public String getNotes(){
        return notes;
    }
    
    public double getProgress(){
        int numberCompleted = 0;
        for(Activity activity : activities){
            if(activity.isCompleted()){
                numberCompleted += 1;
            }
        }
        return (numberCompleted/activities.size())*100;
    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public void setAssessment(Assessment assessment){
        this.assessment = assessment;
    }
    public void addActivity(Activity a){
        activities.add(a);
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(taskName).append("\t").append(taskID).append("\n");
        str.append("Assessment:\t\t").append(assessment.getAssessmentTitle()).append("\n");
        return str.toString();
    }
}
