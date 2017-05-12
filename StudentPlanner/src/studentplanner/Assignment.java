/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author Michael Meyne
 */
public class Assignment extends Assessment {
    
    private String handInProcedure;
    private String assignmentType;
    private boolean isSummative;

    public Assignment(String handInProcedure, String assignmentType, boolean isSummative, String assessmentCode, String assessmentTitle, double weighting, double grade, Deadline deadline, ArrayList<Task> taskList, String notes) {
        super(assessmentCode, assessmentTitle, weighting, grade, deadline, taskList, notes);
        this.handInProcedure = handInProcedure;
        this.assignmentType = assignmentType;
        this.isSummative = isSummative;
    }
    
    public String getHandInProcedure(){
        return handInProcedure;
    }
    
    public String getAssignmentType(){
        return assignmentType;
    }
    
    public boolean getIsSummative(){
        return isSummative;
    }
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(super.toString());
        String summative;
        if(isSummative){
            summative = "Summative";
        }
        else{
            summative = "Formative";
        }
        str.append(summative).append("\n");
        return str.toString();
    }
    
    public String assignmentToFile(){
        Format formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String assignment="";
        assignment += super.getAssessmentCode() + "/";
        assignment += super.getAssessmentTitle()+ "/";
        assignment += super.getWeighting()+ "/";
        assignment += super.getGrade() + "/";
        assignment += formatter.format(super.getDeadline().getTime()) + "/";
        assignment += handInProcedure + "/";
        assignment += assignmentType + "/";
        assignment += isSummative + "/";
        for(Task task: super.getTasks()){
            assignment += task.taskToFile() + "#";
        }
        assignment = assignment.substring(0,assignment.length()-1);
        assignment+="/";
        assignment += super.getNotes();
        return assignment;
    }
    
    
    
}
