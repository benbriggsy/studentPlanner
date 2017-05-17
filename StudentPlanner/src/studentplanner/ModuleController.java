package studentplanner;

import java.sql.Time;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author phillipperks
 */
public class ModuleController {
    private DashboardController dashboard;  
    
    public ModuleController(DashboardController dashboard){
        this.dashboard = dashboard;
    }
        
    public String getModuleCode(int i){
        return dashboard.getStudent().getModule(i).getModuleCode();
    }
    
    public String getModuleTitle(int i){
        return dashboard.getStudent().getModule(i).getModuleName();
    }
      
    public String getModuleOrganiserName(int i){
        return dashboard.getStudent().getModule(i).getModuleOrganiser().getName();
    }  
     
    public double getModuleGrade(int i){
        return dashboard.getStudent().getModule(i).getCurrentGrade();
    }

    public boolean getModuleStatus(int i){
        return dashboard.getStudent().getModule(i).getModuleCompleted();
    }
    
    public String getModuleNotes(int i){
        return dashboard.getStudent().getModule(i).getNotes();
    }

    public ArrayList<Module> getModules(){
        return dashboard.getStudent().getModules();
    }
    
    public ArrayList<Assessment> getModuleAssessments(int i){
        return dashboard.getStudent().getModule(i).getAssessments();
    }
    
    public double getModuleProgress(int i){
        return dashboard.getStudent().getModule(i).getProgress();
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
    
    public DefaultTableModel viewModuleAssessments(int i) {
        String[] cols = {"Assessment Code", "Assessment Title", "Assessment Grade", "Assessment Deadline"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);
        
        for (int j = 0; j < dashboard.getStudent().getModule(i).getAssessments().size(); j++) {
            String assessmentCode = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getAssessmentCode();
            String assessmentName = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getAssessmentTitle();
            double assessmentGrade = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getGrade();
            String assessmentDeadline = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getDeadline().getTime().toString();
            Object[] data = {assessmentCode, assessmentName, assessmentGrade, assessmentDeadline};
            tableModel.addRow(data);
        }
        return tableModel;
    }
    
    public DefaultListModel viewModulesTitles() {
        DefaultListModel listModel = new DefaultListModel();

        for (int i = 0; i < dashboard.getStudent().getModules().size(); i++) {
            String moduleName = dashboard.getStudent().getModules().get(i).getModuleName();
            listModel.addElement(moduleName);
        }
        return listModel;
    }
    
    public DefaultListModel viewModuleAssessmentsTitles(int i) {
        DefaultListModel listModel = new DefaultListModel();
        for (int j = 0; j < dashboard.getStudent().getModule(i).getAssessments().size(); j++) {
            String assessmentName = dashboard.getStudent().getModule(i).getAssessmentByIndex(j).getAssessmentTitle();
            listModel.addElement(assessmentName);
        }
        return listModel;
    }
}
