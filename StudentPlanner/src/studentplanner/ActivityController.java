/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author natha
 */
public class ActivityController {
    private DashboardController dashboard;  
    
    public ActivityController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
    }
    
    public String getActivityName(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getActivityName();
    }
    
    public String getNotes(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getNotes();
    }
    
    public double getWeighting(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getWeighting();
    }
    
    public boolean getIsCompleted(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).isCompleted();
    }
    
    public long getTimeSpent(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        
    return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getTimeSpent();
    }    
    
    public void setAsCompleted(String moduleCode, String assessmentCode, int taskID, String activityID){
        
    }
    
    public void updateActivity(String moduleCode, String assessmentCode, String taskCode, int i, String name, String notes, boolean completed) throws IOException{
        Activity a = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskCode).getActivityByIndex(i);
        a.setName(name);
        a.setNotes(notes);
        a.setCompleted(completed);
        Task t = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskCode);
        boolean taskCompleted = true;
        for (int j = 0; j < t.getActivities().size(); j++){
            if (t.getActivityByIndex(j).isCompleted() == false){
                taskCompleted = false;
                break;
            }
        }
        if (taskCompleted)
            t.setAsCompleted();
        
        Assessment assessment = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode);
        boolean assessmentCompleted = true;
        for (int k = 0; k < assessment.getTasks().size(); k++){
            if (assessment.getTask(k).isCompleted() == false){
                assessmentCompleted = false;
                break;
            }
        }
        if (assessmentCompleted)
            assessment.setAsCompleted();
        dashboard.updateFileForActivity(a);
    }
    
    public void addActivity(String moduleCode, String assessmentCode, ArrayList<String> taskIDs, String activityName,
            String notes, double weighting){
        String activityID = "0";
        Activity a = new Activity(activityID, activityName, notes,
            false, weighting, null,null);
        for (int i = 0; i < taskIDs.size(); i++){
            //Task t = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskById(taskIDs.get(i));
            //t.addActivity(activity);
        }            
    }
    
}
