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
    private int activitiesAdded; 
    
    public TaskController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
        activitiesAdded = 0; 
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
    
    public void addTask(String moduleCode, String assessmentCode, String taskName,
            String notes, double weighting){
        String taskID = "0";
        //Assessment assessment = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode);
        //Task t = new Task(taskName, taskID, notes, assessment, weighting, false);
        //assessment.addTask(t);
        //dashboard.getStudent().getModuleByCode(moduleCode)..getAssessmentByCode(assessmentCode).addTask(t);
    }
    
    public void addActivity(String moduleCode, String assessmentCode, ArrayList<String> taskIDs, String activityName,
            String notes, double weighting){
        String activityID = "";
        if(assessmentCode.charAt(0) == 'A'){
            activityID += "aa";
        }
        else if(assessmentCode.charAt(0) == 'E'){
            activityID += "ea";
        }
        
        Activity a = new Activity(activityID, activityName, notes,
            false, weighting);
        for (int i = 0; i < taskIDs.size(); i++){
            //Task t = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskById(taskIDs.get(i));
            //t.addActivity(activity);
        }            
    }
}
