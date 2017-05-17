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
    private Deadline deadline;
    
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
    
    public Deadline getDeadline(){
        return deadline;
    }
}
