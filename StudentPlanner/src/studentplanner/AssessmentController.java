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
public class AssessmentController {
    ModuleController module;
    Assessment assessment;
    
    public AssessmentController(ModuleController module, Assessment assessment){
        this.module = module;
        this.assessment = assessment;
    }
        
    public String getAssessmentCode(){
        return assessment.getAssessmentCode();
    }
    
    public String getAssessmentTitle(){
        return assessment.getAssessmentTitle();
    }
    
    public double getGrade(){
        return assessment.getGrade();
    }
    
    public double getWeighting(){
        return assessment.getWeighting();
    }
    
    public Deadline getDeadline(){
        return assessment.getDeadline();
    }
    
    public String getNotes(){
        return assessment.getNotes();
    }
    
    public ArrayList<Task> getTasks(){
        return assessment.getTasks();
    }
    
    public AssessmentProgress getAssessmentProgress(){
        return assessment.getProgress();
    }
    
    public ModuleController getModuleController(){
        return module;
    }
    
    public void ViewTask(Task task){
        //NOT SURE WHAT TO DO HERE
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
