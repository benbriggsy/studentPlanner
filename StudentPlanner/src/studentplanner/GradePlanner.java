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
public class GradePlanner {
    private ArrayList<Assessment> assessments;
    
    public GradePlanner(ArrayList<Assessment> assessments){
        this.assessments=assessments;
    }
    
    public double calculateGrade(){
        double currentGrade = 0.0;
        for(Assessment assessment : assessments){
            currentGrade += assessment.getMark()*assessment.getWeighting();
        }
        
        return currentGrade;
    }
}
