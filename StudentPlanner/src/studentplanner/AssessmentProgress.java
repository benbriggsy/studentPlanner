/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;
import java.util.*;
/**
 *
 * @author phillipperks
 */
public class AssessmentProgress {
    private double progress;
    private ArrayList<Task> tasks;
    
    public AssessmentProgress(double progress){
        this.progress = progress;
        this.tasks = new ArrayList<>();
    }
    
    public int getNumberOfCompletedTasks(){
        int numberCompleted = 0;
        for(Task task : tasks){
            if(task.isCompleted()){
                numberCompleted += 1;
            }
        }
        
        return numberCompleted;
        
    }
    
    public double progress(){
        return (this.getNumberOfCompletedTasks()/tasks.size())*100;
    }
}
