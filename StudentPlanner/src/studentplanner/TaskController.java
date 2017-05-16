    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

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
    
    public String getTaskID(String moduleCode, String assessmentCode, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(taskIndex).getTaskID();
    }
    
    public String getTaskName(String moduleCode, String assessmentCode, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(taskIndex).getTaskName();
    }
    
    public String getNotes(String moduleCode, String assessmentCode, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(taskIndex).getNotes();
    }
    
    public double getWeighting(String moduleCode, String assessmentCode, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(taskIndex).getWeighting();
    }
    
    public double getProgress(String moduleCode, String assessmentCode, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(taskIndex).getProgress();
    }
    
    public Assessment getAssessment(String moduleCode, String assessmentCode, int taskIndex){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(taskIndex).getAssessment();
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
    
    public DefaultTableModel viewTaskActivities(String moduleCode, String assessmentCode, String taskID, int i){
        
        String[] cols = {"Activity Name", "Completed?", "Activity Weighting"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
        dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID);
        for (int j = 0; j < dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivities().size(); j++) {
            String activityName = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(j).getActivityName();
            boolean activityComplete = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(j).isCompleted();
            double activity = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(j).getWeighting();
            Object[] data = {activityName, activityComplete, activity};
            tableModel.addRow(data);
        }
        return tableModel;
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
        
        //NEED TO ADD TO FILE
    }
    
    public void viewAllTasks(){
        ArrayList<Task> tasks = new ArrayList();
        for (int i = 0; i < dashboard.getStudent().getModules().size(); i++){
            for (int j = 0; j < dashboard.getStudent().getModule(i).getAssessments().size(); i ++){
                for (int k = 0;k < dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getTasks().size(); i++){
                    tasks.add(dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getTask(k));
                }
            }
        }
    }
}
