/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

/**
 *
 * @author Michael Meyne
 */
public class Assignment extends Assessment {
    
    private String handInProcedure;
    private String assignmentType;
    private boolean isSummative;
    
    public Assignment(String handInProcedure, String assignmentType, Double weight, 
            Deadline deadline, String code, String title, Double grade, String notes, boolean isSummative){
        
        this.handInProcedure = handInProcedure;
        this.assignmentType = assignmentType;
        this.weighting = weight;
        this.deadline = deadline;
        this.assessmentCode = code;
        this.assessmentTitle = title;
        this.grade = grade;
    }
}
