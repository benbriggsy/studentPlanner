/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

/**
 *
 * @author natha
 */
public class Exam {
    private String examRoom;
    private int examDuration;
    
    public Exam(String examRoom, int examDuration){
        this.examRoom = examRoom;
        this.examDuration = examDuration;
    }
    
    public String getExamRoom(){
        return examRoom;
    }
}
