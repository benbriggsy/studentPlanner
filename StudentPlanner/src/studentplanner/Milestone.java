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
public class Milestone {
    private int milestoneID;
    private String milestoneName;
    private String notes;
    private ArrayList<Task> tasks;
    //NEED TO CREATE THESE CLASSES
    //private Progress progress;
    private Deadline deadline;
    
//    public Milestone(){
//        this.tasks = tasks;
//        this.milestoneName = milestoneName;
//        this.notes = notes;
//        this.progress = progress;
//        this.deadline = deadline;
//    }
    
    public int getMilestoneID(){
        return milestoneID;
    }
    
    public String getMilestoneName(){
        return milestoneName;
    }
    
    public String getNotes(){
        return notes;
    }
    
    public ArrayList<Task> getTasks(){
        return tasks;
    }
    
//    public Progress getProgress(){
//        return progress;
//    }
    
    public Deadline getDeadline(){
        return deadline;
    }
    
//    public void updateProgress(Progress progress){
//        progress = progress;
//    }
}
