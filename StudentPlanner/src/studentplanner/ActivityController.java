/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

/**
 *
 * @author natha
 */
public class ActivityController {
    private Activity activity;
    private TaskController task;
    
    public String getActivityName(){
        return activity.getActivityName();
    }
    
    public String getNotes(){
        return activity.getNotes();
    }
    
    public double getWeighting(){
        return activity.getWeighting();
    }
    
    public boolean getIsCompleted(){
        return activity.isCompleted();
    }
    
    public TaskController getTaskController(){
        return task;
    }
    
    public void setAsCompleted(){
        activity.setAsCompleted();
    }
    
    
}
