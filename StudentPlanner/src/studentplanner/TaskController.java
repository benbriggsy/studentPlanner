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
    private DashboardController dashboard;
    
    public TaskController(DashboardController dashboard){
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
    
    public void updateTask(String moduleCode, String assessmentCode, int i, String name, String notes){
        Task t = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTask(i);
        t.setTaskName(name);
        t.setTaskNotes(notes);
        Module m = dashboard.getStudent().getModuleByCode(moduleCode);
        Assessment a = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode);       
        //dashboard.updateFileForTask(m, a, t);
    }
    
    public DefaultTableModel viewTaskActivities(String moduleCode, String assessmentCode, String taskID, int i){
        
        String[] cols = {"Activity Name", "Completed", "Activity Weighting"};
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
    
    public void addActivity(ArrayList<Integer> moduleIndexes, ArrayList<Integer> assessmentIndexes, ArrayList<Integer> taskIndexes, String activityName,
            String notes, double weighting){
        String activityID = "";
        Assessment assessment = dashboard.getStudent().getModule(moduleIndexes.get(0)).getAssessmentByIndex(assessmentIndexes.get(0));
        if(assessment.getAssessmentCode().charAt(0) == 'A'){
            activityID += "aa";
        }
        else if(assessment.getAssessmentCode().charAt(0) == 'E'){
            activityID += "ea";
        }
        
        Activity a = new Activity(activityID, activityName, notes,
            false, weighting);
        for (int i = 0; i < taskIndexes.size(); i++){
            Task t = dashboard.getStudent().getModule(moduleIndexes.get(i)).getAssessmentByIndex(assessmentIndexes.get(i)).getTask(taskIndexes.get(i));
            t.addActivity(a);
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
