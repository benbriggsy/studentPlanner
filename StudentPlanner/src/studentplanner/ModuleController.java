package studentplanner;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phillipperks
 */
public class ModuleController {
    private Module module;
    private DashboardController dashboard;  
    
    public ModuleController(DashboardController dashboard){
        //this.assessment = assessment;
        //this.task = task;
        this.dashboard = dashboard;
    }
    
    public DefaultTableModel viewModules() {
        String[] cols = {"Module Code", "Module Title", "Module Organiser"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);

        for (int i = 0; i < dashboard.getStudent().getModules().size(); i++) {
            String moduleCode = dashboard.getStudent().getModules().get(i).getModuleCode();
            String moduleName = dashboard.getStudent().getModules().get(i).getModuleName();
            String moduleOrganiser = dashboard.getStudent().getModules().get(i).getModuleOrganiser().getName();

            Object[] data = {moduleCode, moduleName, moduleOrganiser};

            tableModel.addRow(data);
        }
        return tableModel;
    }
    
    public Module getModule(){
        return module;
    }
    //needs improving
    public Assessment viewAssessment(int i){
        return module.getAssessments().get(i);
    }
    public String getModuleCode(){
       return module.getModuleCode();
    }
    
    public String getModuleTitle(){
        return module.getModuleName();
    }
    
    public Admin getModuleOrganiser(){
        return module.getModuleOrganiser();
    }
    
    public ArrayList<Assessment> getAssessments(){
        return module.getAssessments();
    }
    
    public ArrayList<Double> getAssessmentGrades(){
        return module.getAssessmentGrades();
    }
    
    public double getCurrentGrade(){
        return module.getCurrentGrade();
    }
    
    public boolean getModuleCompleted(){
        return module.getModuleCompleted();
    }
    
    public String getNotes(){
        return module.getNotes();
    }
    
    public void addNote(String note){
        module.addNote(note);
    }
    
    public void setModuleCompleted(boolean c){
        module.setModuleCompleted(c);
    }
    
    public DashboardController getDashboardController(){
        return dashboard;
    }
}
