
package studentplanner;
import java.util.*;
/**
 *
 * @author phillipperks
 */
public class Module {
    private String moduleCode;
    private String moduleTitle;
    private Admin moduleOrganiser;
    private ArrayList<Assessment> assessments;
    private ArrayList<Double> assessmentGrades;
    private double currentGrade;
    private boolean moduleCompleted;
    private String notes;
    
    public Module(String moduleCode, String moduleTitle, Admin moduleOrganiser){
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
    
    public Admin getModuleOrganiser(){
        return moduleOrganiser;
    }
    
    public ArrayList<Assessment> getAssessments(){
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
