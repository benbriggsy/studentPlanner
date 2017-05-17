/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author natha
 */
public class AssessmentController {
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
    
    public DefaultTableModel viewAssessmentTasks(String moduleCode, int i) {
        String[] cols = {"Task Name", "Task Weighting", "Completed"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);

        for (int j = 0; j < dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTasks().size(); j++){            
            String taskName = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTask(j).getTaskName();
            Double weighting = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTask(j).getWeighting();
            boolean completed = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByIndex(i).getTask(j).isCompleted();
            Object[] data = {taskName, weighting, completed};
            tableModel.addRow(data);
        }
        return tableModel;
    }
    
    public DefaultListModel viewAssessmentTasksTitles(int i, int j) {
        DefaultListModel listModel = new DefaultListModel();
        for (int k = 0; k < dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getTasks().size(); k++){            
            String taskName = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getTask(k).getTaskName();
            Object[] data = {taskName};
            listModel.addElement(taskName);
        }
        return listModel;
    }
    
    public void ViewTask(Task task){
        //NOT SURE WHAT TO DO HEREz
    }
    
    public void createTask(){
        
    }
    
    public boolean addTask(String moduleCode, String assessmentCode, String taskName,
            String notes, double weighting) throws IOException{
        String taskID = "";
        Assessment assessment = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode);
        double summativeWeight = 0;
        for (int j = 0; j < assessment.getTasks().size(); j++){
            summativeWeight += assessment.getTask(j).getWeighting();
        }
        summativeWeight += weighting;
        if (summativeWeight > 100)
            return false;
        if(assessmentCode.charAt(0)=='A'){
            taskID+="aT";
        }
        else if(assessmentCode.charAt(0)=='E'){
            taskID+="eT";
        }
        taskID += assessmentCode.substring(1, Math.min(assessmentCode.length(), 4));
        dashboard.getStudent().incrementNumberOfTasks();
        if(dashboard.getStudent().getNumberOfTasks()<10){
           taskID +='0'; 
        }
        taskID += dashboard.getStudent().getNumberOfTasks();
        
        Task t = new Task(taskName, taskID, notes, assessment, weighting, false);
        assessment.addTask(t);
        Module mod = dashboard.getStudent().getModuleByCode(moduleCode);
        dashboard.addTaskToFile(mod, assessment, t);
        return true;
    }
    
}
