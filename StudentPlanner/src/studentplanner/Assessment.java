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
public class Assessment {
    private String assessmentCode;
    private String assessmentTitle;
    private double weighting;
    private double grade;
    private Deadline deadline;
    private ArrayList<Task> taskList;
    private ArrayList<Module> modules;
    private String notes;
    private AssessmentProgress progress;
    private boolean completed;
    
//    public Assessment(String assessmentCode, String assessmentTitle, double weighting,
//        double grade, Deadline deadline, ArrayList<Task> taskList, ArrayList<Module> modules, 
//        String notes, Progress progress){
//        this.assessmentCode = assessmentCode;
//        this.assessmentTitle = assessmentTitle;
//        this.weighting = weighting;
//        this.grade = grade;
//        this.deadline = deadline;
//        this.taskList = taskList;
//        this.notes = notes;
//        this.progress = progress;
//    this.modules = modules;
//    }
    
    public String getAssessmentCode(){
        return assessmentCode;
    }
    
    public String getAssessmentTitle(){
        return assessmentTitle;
    }
    
    public double getWeighting(){
        return weighting;
    }
    
    public double getGrade(){
        return grade;
    }
     
    public Deadline getDeadline(){
        return deadline;
    }
    
    public ArrayList<Task> getTasks(){
        return taskList;
    }
    
    public String getNotes(){
        return notes;
    }
        
//    public Progress getProgress(){
//        return progress;
//    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public void setAsCompleted(){
        completed = true;
    }
}
