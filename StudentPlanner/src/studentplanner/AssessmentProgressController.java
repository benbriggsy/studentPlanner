package studentplanner;

/**
 *
 * @author phillipperks
 */
public class AssessmentProgressController {
    private AssessmentController assessment;
   
    
    public double progress(){
        return assessment.getAssessment().getAssessmentProgress().progress();
    }
    
    
    
}
