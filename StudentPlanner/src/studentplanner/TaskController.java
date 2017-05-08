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
public class TaskController {
    
    private AssessmentController assessment;
    private Task task;
    private Student student;  
    
    public TaskController(Student student){
        //this.assessment = assessment;
        //this.task = task;
        this.student = student;
    }
    
    public int getTaskID(){
        return task.getTaskID();
    }
    
    public String getTaskName(){
        return task.getTaskName();
    }
    
    public String getNotes(){
        return task.getNotes();
    }
    
    public double getWeighting(){
        return task.getWeighting();
    }
    
    public double getProgress(){
        return task.getProgress();
    }
    
    public Assessment getAssessment(){
        return task.getAssessment();
    }
    
    public AssessmentController getAssessmentController(){
        return assessment;
    }
    
    public void createActivity(){
        //NOT SURE WHAT TO IMPLEMENT
    }
    
    public void addActivity(String activityName, String notes, ArrayList<Task> taskList,
            boolean completed, double weighting){
        //CALCULATE NEXT ID HERE
        int activityID = 0;
        Activity a = new Activity(activityID, activityName, notes, taskList,
            completed, weighting);
        task.addActivity(a);
    }
    
    public void displayGanttChart(){
        
    }
    
    public void displayActivity(Activity activity){
        
    }
    
    public void updateProgress(){
        
    }
}
