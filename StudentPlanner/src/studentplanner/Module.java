
package studentplanner;
import java.util.*;
/**
 *
 * @author phillipperks
 */
public class Module {
    private String moduleCode;
    private String moduleName;
    private Admin moduleOrganiser;
    private ArrayList<Assessment> assessments;
    private ArrayList<Double> assessmentGrades;
    private double currentGrade;
    private boolean moduleCompleted;
    private String notes;
    
    public Module(String moduleCode, String moduleTitle, Admin moduleOrganiser){
        this.moduleCode = moduleCode;
        this.moduleName = moduleTitle;
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
    
    public String getModuleName(){
        return moduleName;
    }
    
    public Admin getModuleOrganiser(){
        return moduleOrganiser;
    }
    
    public ArrayList<Assessment> getAssessments(){
        return assessments;
    }
    
    public Assessment getAssessmentByIndex(int i){
        return assessments.get(i);
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
    
    public void addNote(String note){
        notes+=note;
        notes+="\n";
    }
    
    public void setModuleCompleted(boolean c){
        moduleCompleted = c;
    }
    
    public void addAssessment(Assessment assessment){
        assessments.add(assessment);
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        for(int i=0; i<assessments.size(); i++){
        str.append(assessments.get(i)).append("\n");
        
        }
        return str.toString();
    }
}
