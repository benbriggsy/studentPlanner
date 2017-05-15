/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;
import java.util.Date;
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
    
    public Date getDeadline(String moduleCode, int i){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getDeadline().getTime();
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
    
    public DefaultTableModel viewModuleAssessmentTasks(int i) {
        String[] cols = {"Task ID", "Task Title", "Assessment Grade", "Status"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);

        for (int j = 0; j < dashboard.getStudent().getModule(i).getAssessments().size(); j++) {
            String assessmentCode = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getAssessmentCode();
            String assessmentName = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getAssessmentTitle();
            double assessmentGrade = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getGrade();
            String assessmentDeadline = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getDeadline().getTime().toString();
            Object[] data = {assessmentCode, assessmentName, assessmentGrade, assessmentDeadline};
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
