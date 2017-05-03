
package studentplanner;
import java.util.*;
/**
 *
 * @author phillipperks
 */
public class Module {
    private String moduleCode;
    private String moduleTitle;
    private ModuleOrganiser moduleOrganiser;
    private ArrayList<Assessments> assessments;
    private ArrayList<Double> assessmentGrades;
    private double currentGrade;
    private boolean moduleCompleted;
    private String notes;
    
    public Module(String moduleCode, String moduleTitle, ModuleOrganiser moduleOrganiser){
        this.moduleCode = moduleCode;
        this.moduleTitle = moduleTitle;
        this.moduleOrganiser = moduleOrganiser;
        this.assessments = new ArrayList<>();
        this.assessmentGrades = new ArrayList<>();
        this.currentGrade = 0.0;
        this.moduleCompleted = false;
        this.notes = null;
    }
    
    public String getModuleCode(){
        return moduleCode;
    }
    
    public String getModuleTitle(){
        return moduleTitle;
    }
    
    public ModuleOrganiser getModuleOrganiser(){
        return moduleOrganiser;
    }
    
    public ArrayList<Assessmensts> getAssessments(){
        return assessments;
    }
    
    public ArrayList<Double> getAssessmentGrades(){
        return assessmentGrades;
    }
    
    public double getCurrentGrade(){
        return currentGrade;
    }
    
    public boolean getModuleCompleted(){
        return moduleCompleted;
    }
    
    public String getNotes(){
        return notes;
    }
}
