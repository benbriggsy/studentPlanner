/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;

/**
 *
 * @author phillipperks
 */
public class TaskProgress {
    private double progress;
    private ArrayList<Activity> activities;
    
    public TaskProgress(double progress){
        this.progress = progress;
        this.activities = new ArrayList<>();
    }
    
    public int getNumberOfActivities(){
        return activities.size();
    }
    
    public int getNumberOfCompletedActivities(){
        int numberCompleted = 0;
        for(Activity activitie : activities){
            if(activities.getCompleted()){
                numberCompleted += 1;
            }
        }
        
        return numberCompleted;
        
    }
}
