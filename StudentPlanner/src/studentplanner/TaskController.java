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
     private DashboardController dashboard;  
    
    public TaskController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
    }
    
    public String getTaskID(String moduleCode, int assessmentIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getTaskID();
    }
    
    public String getTaskName(String moduleCode, int assessmentIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getTaskName();
    }
    
    public String getNotes(String moduleCode, int assessmentIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getNotes();
    }
    
    public double getWeighting(String moduleCode, int assessmentIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getWeighting();
    }
    
    public double getProgress(String moduleCode, int assessmentIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getProgress();
    }
    
    public Assessment getAssessment(String moduleCode, int assessmentIndex, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(assessmentIndex).getTask(taskIndex).getAssessment();
    }
    
    public AssessmentController getAssessmentController(){
        return assessment;
    }
    
    public void createActivity(){
        //NOT SURE WHAT TO IMPLEMENT
    }
    
//    public void addActivity(String activityName, String notes, ArrayList<Task> taskList,
//            boolean completed, double weighting){
//        //CALCULATE NEXT ID HERE
//        int activityID = 0;
//        Activity a = new Activity(activityID, activityName, notes, taskList,
//            completed, weighting);
//        task.addActivity(a);
//    }
    
    public void displayGanttChart(){
        
    }
    
    public void displayActivity(Activity activity){
        
    }
    
    public void updateProgress(){
        
    }
}
