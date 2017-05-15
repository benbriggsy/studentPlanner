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
public class AssessmentController {
    private ModuleController module;
    private Assessment assessment;
    private DashboardController dashboard;  
    
    public AssessmentController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
    }
        
    public String getAssessmentCode(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getAssessmentCode();
    }
    
    public String getAssessmentTitle(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getAssessmentTitle();
    }
    
    public double getGrade(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getGrade();
    }
    
    public double getWeighting(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getWeighting();
    }
    
    public Deadline getDeadline(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getDeadline();
    }
    
    public String getNotes(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getNotes();
    }
    
    public ArrayList<Task> getTasks(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTasks();
    }
    
    public double getAssessmentProgress(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getProgress();
    }
    
    public ModuleController getModuleController(){
        return module;
    }
    
//    private String taskName;
//    private String taskID;
//    private String notes;
//    private ArrayList<Activity> activities;
//    private Assessment assessment;
//    private double weighting;
//    private boolean completed;
    
    public DefaultTableModel viewAssessmentTasks(String moduleCode, int i) {
        String[] cols = {"Task Name", "Task Weighting", "Completed?"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);

        for (int j = 0; j < dashboard.getStudent().getModuleByCode(moduleCode).getAssessments().size(); j++){
                
            String taskName = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTask(j).getTaskName();
            Double weighting = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTask(j).getWeighting();
            boolean completed = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTask(j).isCompleted();
            Object[] data = {taskName, weighting, completed};
            tableModel.addRow(data);
        }
        return tableModel;
    }
    
    public void ViewTask(Task task){
        //NOT SURE WHAT TO DO HEREz
    }
    
    public void createTask(){
        
    }
    
    public void AddTask(){
        
    }
    
    public void CreateActivity(){
        
    }
    
    public void AddActivity(){
        
    }  
    
    public void displayGanttChart(){
        
    }
    
    public void updateProgress(){
        
    }
    
    public void setGrade(double grade){
        
    }
}
