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
public class ActivityController {
    private Activity activity;
    private TaskController task;
    private DashboardController dashboard;  
    
    public ActivityController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
    }
    
    public String getActivityName(String moduleCode, int assessmentIndex, int activityIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getActivityByIndex(activityIndex).getActivityName();
    }
    
    public String getNotes(String moduleCode, int assessmentIndex, int activityIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getActivityByIndex(activityIndex).getNotes();
    }
    
    public double getWeighting(String moduleCode, int assessmentIndex, int activityIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getActivityByIndex(activityIndex).getWeighting();
    }
    
    public boolean getIsCompleted(String moduleCode, int assessmentIndex, int activityIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getActivityByIndex(activityIndex).isCompleted();
    }
    
    public TaskController getTaskController(){
        return task;
    }
    
    public void setAsCompleted(String moduleCode, String assessmentCode, int taskID, String activityID){
        
    }
    
    public void addActivity(String moduleCode, String assessmentCode, ArrayList<String> taskIDs, String activityName,
            String notes, double weighting){
        String activityID = "0";
        Activity a = new Activity(activityID, activityName, notes,
            false, weighting);
        for (int i = 0; i < taskIDs.size(); i++){
            //Task t = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskById(taskIDs.get(i));
            //t.addActivity(activity);
        }            
    }
    
}
