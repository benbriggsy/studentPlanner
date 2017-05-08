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
    private ArrayList<Task> tasks;
    private String notes;
    private AssessmentProgress progress;
    private boolean completed;
    
    public Assessment(String assessmentCode, String assessmentTitle, double weighting,
        double grade, Deadline deadline, ArrayList<Task> taskList, 
        String notes, AssessmentProgress progress){
        this.assessmentCode = assessmentCode;
        this.assessmentTitle = assessmentTitle;
        this.weighting = weighting;
        this.grade = grade;
        this.deadline = deadline;
        this.tasks = tasks;
        this.notes = notes;
        this.progress = progress;
    }
    
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
    
    public double getProgress(){
        int numberCompleted = 0;
        for(Task task : tasks){
            if(task.isCompleted()){
                numberCompleted += 1;
            }
        }
        return (numberCompleted/tasks.size())*100;
    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public void setAsCompleted(){
        completed = true;
    }
}
