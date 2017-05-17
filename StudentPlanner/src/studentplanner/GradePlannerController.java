package studentplanner;

import java.util.ArrayList;

/**
 *
 * @author phillipperks
 */
public class GradePlannerController {
    private ModuleController module;
    private GradePlanner gradePlanner;
    
    public double predictedAssessmentGrade(double predicitedGrade, Assessment assessment){
        return gradePlanner.predictedAssessmentGrade(predicitedGrade, assessment);
    }
    
    public double predicitedModuleGrade(ArrayList<Double>predictedAssessmentGrades){
        return gradePlanner.predicitedModuleGrade(predictedAssessmentGrades);
    }
    
    public ModuleController getModuleController(){
        return module;
    }
    
}
