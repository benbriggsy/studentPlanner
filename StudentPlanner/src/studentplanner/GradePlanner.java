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
    private ArrayList<Assessment> moduleAssessments;
    
    public GradePlanner(Module module){
        this.moduleAssessments=module.getAssessments();
    }
    
    public double predictedAssessmentGrade(double predicitedGrade, Assessment assessment){
        return predicitedGrade*assessment.getWeighting();
    }
    
    public double predicitedModuleGrade(ArrayList<Double>predictedAssessmentGrades){
        double predicitedModuleGrade = 0.0;
        for(Double predicitedGrade:predictedAssessmentGrades){
            predicitedModuleGrade+=predicitedGrade;
        }
        return predicitedModuleGrade;
    }
}
