package studentplanner;

import java.util.ArrayList;

/**
 *
 * @author natha
 */
public class Task {
    
    private String taskName;
    private String taskID;
    private String notes;
    private ArrayList<Activity> activities;
    private Assessment assessment;
    private double weighting;
    private boolean completed;
    
    Task(String taskName, String taskID, String notes, 
            Assessment assessment, double weighting, boolean completed){
        this.taskName = taskName;
        this.taskID = taskID;
        this.assessment = assessment;
        this.notes = notes;
        this.weighting = weighting;
        this.activities = new ArrayList();
        this.completed = completed;
    }
    
    Task(String taskName, String taskID, String notes, ArrayList<Activity> activies, 
            Assessment assessment, double weighting, boolean completed){
        this.taskName = taskName;
        this.taskID = taskID;
        this.assessment = assessment;
        this.notes = notes;
        this.weighting = weighting;
        this.activities = activies;
        this.completed = completed;
    }
    
    public String getTaskID(){
        return taskID;
    }
    
    public String getTaskName(){
        return taskName;
    }
    
    public void changeTaskName(String name){
        taskName = name;
    }
    
    public Assessment getAssessment(){
        return assessment;
    }
    
    public ArrayList<Activity> getActivities(){
        return activities;
    }
    
    public Activity getActivityByIndex(int i){
        return activities.get(i);
    }
    public double getWeighting(){
        return weighting;
    }
    
    public String getNotes(){
        return notes;
    }
    
    public double getProgress(){
        double percentage = 0;
        if(activities.size()!=0){
            for(Activity activity : activities){
                if(activity.isCompleted()){
                    percentage += activity.getWeighting();
                }
            }
        return percentage;
        }
        else 
            return 0;
    }
    
    public boolean isCompleted(){
        return completed;
    }
    
    public void setTaskName(String name){
        taskName = name;
    }
    
    public void setTaskNotes(String notes){
        this.notes = notes;
    }
     
    public void setAsCompleted(){
        completed = true;
    }
    
    public void setWeighting(double weighting){
        this.weighting = weighting;
    }
    
    public void setAssessment(Assessment assessment){
        this.assessment = assessment;
    }
    public void addActivity(Activity a){
        activities.add(a);
    }
    
    public void removeActivity(Activity a){
        activities.remove(a);
    }
    
    public String taskToFile(){
        String task = "";
        task+=taskID;
        task+="#";
        task+=taskName;
        task+="#";
        task+=weighting;
        task+="#";
        task+=completed;
        task+="#";
        for(Activity taskActivity: activities){
            task+=taskActivity.activityToFile();
            task+="~";
        }
        task = task.substring(0,task.length()-1);
        task+="#";
        task+=notes;
        
        return task;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(taskName).append("\t").append(taskID).append("\n");
        str.append("Assessment:\t\t").append(assessment.getAssessmentTitle()).append("\n");
        return str.toString();
    }
}
