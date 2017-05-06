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
    //private Deadline deadline;
    private ArrayList<Task> taskList;
    private String notes;
    //private Progress progress;
    
//    public Assessment(String assessmentCode, String assessmentTitle, double weighting,
//        double grade, Deadline deadline, ArrayList<Task> taskList, String notes,
//        Progress progress){
//        this.assessmentCode = assessmentCode;
//        this.assessmentTitle = assessmentTitle;
//        this.weighting = weighting;
//        this.grade = grade;
//        this.deadline = deadline;
//        this.taskList = taskList;
//        this.notes = notes;
//        this.progress = progress;
//    }
    
    public String getAssessmentCode(){
        return assessmentCode;
    }
}
