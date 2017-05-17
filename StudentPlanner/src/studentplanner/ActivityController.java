/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author natha
 */
public class ActivityController {
    private DashboardController dashboard;  
    
    public ActivityController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
    }
    
    public String getActivityName(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getActivityName();
    }
    
    public String getNotes(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getNotes();
    }
    
    public double getWeighting(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getWeighting();
    }
    
    public boolean getIsCompleted(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).isCompleted();
    }
    
    public long getTimeSpent(String moduleCode, String assessmentCode, int activityIndex, String taskID){
        
    return dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskID).getActivityByIndex(activityIndex).getTimeSpent();
    }    
    
    public void setAsCompleted(String moduleCode, String assessmentCode, int taskID, String activityID){
        
    }
    
    public boolean updateActivity(String moduleCode, String assessmentCode, String taskCode, int i, String name, String notes, boolean completed, double weighting) throws IOException{
        Activity a = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskByID(taskCode).getActivityByIndex(i);
        double originalWeight = a.getWeighting();
        a.setWeighting(weighting);
        //check none of the tasks will have a weighting of over 100
        for (Module module : dashboard.getStudent().getModules()) {
            for (Assessment assessment : module.getAssessments()) {
                
                for (Task task : assessment.getTasks()) {
                    for (Activity activity : task.getActivities()){
                        if (activity.getActivityID().equals(a.getActivityID())){
                            double summativeWeight = 0;
                            for (Activity activitie : task.getActivities()) {
                                summativeWeight += activitie.getWeighting();
                                if (summativeWeight > 100){
                                    a.setWeighting(originalWeight);
                                    return false;
                                }
                                    
                            }
                        }
                    }
                }
            }
        }
        
        a.setName(name);
        a.setNotes(notes);
        a.setCompleted(completed);
        
        if (completed == true){
            for (Module module : dashboard.getStudent().getModules()) {
                for (Assessment assessment : module.getAssessments()) {
                    for (Task task : assessment.getTasks()) {
                        for (Activity activity : task.getActivities()){
                            if (activity.getActivityID().equals(a.getActivityID())){
                                boolean taskCompleted = true;
                                for (Activity activitie : task.getActivities()) {
                                    if (activitie.isCompleted() == false){
                                        taskCompleted = false;
                                        break;
                                    }
                                }
                                if (taskCompleted){
                                    task.setAsCompleted();
                                    dashboard.updateFileForTask(module, task);
                                }
                                boolean assessmentCompleted = true;
                                for (Task tsk : assessment.getTasks()){
                                    if (tsk.isCompleted() == false){
                                        assessmentCompleted = false;
                                        break;
                                    }
                                }
                                if (assessmentCompleted){
                                    assessment.setAsCompleted();
                                    if(assessment instanceof Assignment){
                                        Assignment assignment = (Assignment)assessment;
                                        dashboard.updateFileForAssignment(module, assignment);
                                    }
                                    else if(assessment instanceof Exam){
                                        Exam exam = (Exam)assessment;
                                        dashboard.updateFileForExam(module, exam);
                                        
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        dashboard.updateFileForActivity(a);
        return true;
    }
    
    public void addActivity(String moduleCode, String assessmentCode, ArrayList<String> taskIDs, String activityName,
            String notes, double weighting){
        String activityID = "0";
        Activity a = new Activity(activityID, activityName, notes,
            false, weighting, null,null);
        for (int i = 0; i < taskIDs.size(); i++){
            //Task t = dashboard.getStudent().getModuleByCode(moduleCode).getAssessmentByCode(assessmentCode).getTaskById(taskIDs.get(i));
            //t.addActivity(activity);
        }            
    }
    
}
