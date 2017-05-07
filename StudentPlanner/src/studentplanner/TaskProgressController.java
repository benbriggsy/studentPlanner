/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

/**
 *
 * @author phillipperks
 */
public class TaskProgressController {
    private TaskController task;
   
    
    public double progress(){
        return task.getTask().getTaskProgress().progress();
    }
}
