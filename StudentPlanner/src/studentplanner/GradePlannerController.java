package studentplanner;

/**
 *
 * @author phillipperks
 */
public class GradePlannerController {
    private ModuleController module;
    private GradePlanner gradePlanner;
    
    public double calculateGrade(){
        return gradePlanner.calculateGrade();
    }
    
    public ModuleController getModuleController(){
        return module;
    }
    
}
