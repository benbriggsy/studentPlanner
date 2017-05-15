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
public abstract class Assessment {
    private String assessmentCode;
    private String assessmentTitle;
    private double weighting;
    private double grade;
    private Deadline deadline;
    private ArrayList<Task> tasks;
    private String notes;
    private boolean completed;
    private String assessmentType;
    
    public Assessment(String assessmentCode, String assessmentTitle, double weighting,
        double grade, Deadline deadline, ArrayList<Task> taskList, 
        String notes, String assessmentType){
        this.assessmentCode = assessmentCode;
        this.assessmentTitle = assessmentTitle;
        this.weighting = weighting;
        this.grade = grade;
        this.deadline = deadline;
        this.tasks = taskList;
        this.notes = notes;
        this.completed = false;
        this.assessmentType = assessmentType;
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
        return tasks;
    }
    
    public Task getTask(int i){
        return tasks.get(i);
    }
    
    public void addTask(Task t){
        tasks.add(t);
    }
    
    public void removeTask(Task t){
        tasks.remove(t);
    }
    public String getNotes(){
        return notes;
    }
    
    public void setGrade(double g){
        grade = g;
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
    
    public void setDeadline(Deadline deadline){
        this.deadline = deadline;     
    }
    
     @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(assessmentTitle).append("\t").append(assessmentCode).append("\n");
        str.append("Deadline:\t").append(deadline).append("\n");
        str.append("Weighting:\t").append(weighting).append("\n");
        str.append("Grade:\t\t").append(grade).append("\n");
        str.append("Notes: \t\t").append(notes).append("\n");
        return str.toString();
    }
}
