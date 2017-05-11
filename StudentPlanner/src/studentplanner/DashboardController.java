/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.time.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author phillipperks
 */
public class DashboardController {
    private Student student;
    private ModuleController moduleController;
    //private Student student;
    //private MilestoneController milestoneController;
    
    public DashboardController() throws FileNotFoundException, IOException, ParseException {
        File semesterFile = new File("semester.txt");
        student = new Student(semesterFile);       
    }
    
    public Student getStudent(){
        return student;      
    }
    
   
    
    public DefaultTableModel viewModules(){
        String[] cols = {"Module Cod", "Module Title", "Module Organiser"};
        DefaultTableModel tableModel = new DefaultTableModel(cols, 0);

        for (int i = 0; i < student.getModules().size(); i++){
            String moduleCode = student.getModules().get(i).getModuleCode();
            String moduleName = student.getModules().get(i).getModuleName();
            String moduleOrganiser = student.getModules().get(i).getModuleOrganiser().getName();
            

            Object[] data = {moduleCode, moduleName, moduleOrganiser};

            tableModel.addRow(data);

         }
        
        return tableModel;
    }
//    public ArrayList<Module> viewModules(){
//        return dashboard.getModules();
//    }
    
    public ArrayList<Milestone> viewMilestones(){
        return student.getMilestones();
    }
    
    
    public ArrayList<Milestone> viewUpComingIncompleteMilestones(){
        ArrayList<Milestone> upComingMilestones = new ArrayList<>();
        
        Date today = new Date();
        
        for(int i=0; i<student.getMilestones().size(); i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(student.getMilestone(i).getDeadline().getTime());            
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            Date previousWeek = calendar.getTime();
            if(!(today.before(previousWeek) 
                || today.after(student.getMilestone(i).getDeadline().getTime()))){
                upComingMilestones.add(student.getMilestone(i));
            }
        }
       return upComingMilestones;
    }
    
//    public void uploadSemesterFile() throws FileNotFoundException, ParseException{
//        File semesterFile = new File("semester.txt");
//        try{
//            dashboard.setSemesterFile(semesterFile);
//        }
//        catch(IOException ioEx){
//            
//        }
//        catch(ParseException peEx){
//            
//        }
//        
//    }
    
//    public Module viewModule(String moduleName){
//       for(int i=0; i<dashboard.getModules().size(); i++){
//           if(dashboard.getModule(i).getModuleName().equals(moduleName)){
//               return dashboard.getModule(i);
//           }
//       }
//    }

    public ArrayList<Assessment> viewUpComingIncompleteAssessments(){
        ArrayList<Assessment> upComingAssessments = new ArrayList<>();
        
        Date today = new Date();
        
        for(int i=0; i<student.getModules().size(); i++){
           for(int j=0; j<student.getModule(i).getAssessments().size(); j++){
               if(!student.getModule(i).getAssessmentByIndex(j).isCompleted()){
                   Calendar calendar = Calendar.getInstance();
                    calendar.setTime(student.getModule(i).getAssessmentByIndex(j).getDeadline().getTime());            
                    calendar.add(Calendar.DAY_OF_YEAR, -7);
                    Date previousWeek = calendar.getTime();
                   if(!(today.before(previousWeek) 
                           || today.after(student.getModule(i).getAssessmentByIndex(j).getDeadline().getTime()))){
                       upComingAssessments.add(student.getModule(i).getAssessmentByIndex(j));
                    }                
               }
           }
       }
        
       return upComingAssessments;
    }
    
//    public ArrayList<Assessment> viewUpComingIncompleteAssessments(){
//        ArrayList<Assessment> upComingAssessments = new ArrayList<>();
//        
//        Date today = new Date();
//        
//        for(int i=0; i<dashboard.getModules().size(); i++){
//           for(int j=0; j<dashboard.getModule(i).getAssessments().size(); j++){
//               if(!dashboard.getModule(i).getAssessmentByIndex(j).isCompleted()){
//                   Calendar calendar = Calendar.getInstance();
//                    calendar.setTime(dashboard.getModule(i).getAssessmentByIndex(j).getDeadline().getTime());            
//                    calendar.add(Calendar.DAY_OF_YEAR, -7);
//                    Date previousWeek = calendar.getTime();
//                   if(!(today.before(previousWeek) 
//                           || today.after(dashboard.getModule(i).getAssessmentByIndex(j).getDeadline().getTime()))){
//                       upComingAssessments.add(dashboard.getModule(i).getAssessmentByIndex(j));
//                    }
//                   
//               }
//           }
//       }
//        
//       return upComingAssessments;
//    }
    
}
