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
public class Exam extends Assessment {
    private String examRoom;
    private int examDuration;
    
    public Exam(String examRoom, int examDuration, String assessmentCode, String assessmentTitle, double weighting,
        double grade, Deadline deadline, ArrayList<Task> taskList, 
        String notes){
        super(assessmentCode, assessmentTitle, weighting, grade, deadline, 
                taskList, notes);
        this.examRoom = examRoom;
        this.examDuration = examDuration;
    }
    
    public String getExamRoom(){
        return examRoom;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(super.toString());
        String summative;
        str.append("Room:\t\t").append(examRoom).append("\n");
        str.append("Duration:\t").append(examDuration).append(" minutes\n");
        return str.toString();
    }
}
