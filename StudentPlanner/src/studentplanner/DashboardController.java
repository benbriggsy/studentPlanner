/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

    public Student getStudent() {
        return student;
    }
    
    public String getStudentFullName() {
        return student.getFullName();
    }

    public String getStudentUsername() {
        return student.getUserName();
    }

    public String getStudentEmail() {
        return student.getEmailAddress();
    }

    public String getStudentSchoolOfStudy() {
        return student.getSchoolOfStudy();
    }

    public int getStudentYearOfStudy() {
        return student.getYearOfStudy();
    }
    
    public Module getStudentModule(int i){
        return student.getModule(i);
    }
    
    public Milestone getStudentMilestone(int i){
        return student.getMilestone(i);
    }
    
    public ArrayList<Module> getStudentModuleList(){
        return student.getModules();
    }
    
    public ArrayList<Milestone> getStudentMilestoneList(){
        return student.getMilestones();
    }
    
    public ModuleController getModuleController(){
        return moduleController;
    }
    
//    public ArrayList<Module> viewModules(){
//        return dashboard.getModules();
//    }

    public ArrayList<Milestone> viewMilestones() {
        return student.getMilestones();
    }

    public ArrayList<Milestone> viewUpComingIncompleteMilestones() {
        ArrayList<Milestone> upComingMilestones = new ArrayList<>();

        Date today = new Date();

        for (int i = 0; i < student.getMilestones().size(); i++) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(student.getMilestone(i).getDeadline().getTime());
            calendar.add(Calendar.DAY_OF_YEAR, -7);
            Date previousWeek = calendar.getTime();
            if (!(today.before(previousWeek)
                    || today.after(student.getMilestone(i).getDeadline().getTime()))) {
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
    public ArrayList<Assessment> viewUpComingIncompleteAssessments() {
        ArrayList<Assessment> upComingAssessments = new ArrayList<>();

        Date today = new Date();

        for (int i = 0; i < student.getModules().size(); i++) {
            for (int j = 0; j < student.getModule(i).getAssessments().size(); j++) {
                if (!student.getModule(i).getAssessmentByIndex(j).isCompleted()) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(student.getModule(i).getAssessmentByIndex(j).getDeadline().getTime());
                    calendar.add(Calendar.DAY_OF_YEAR, -7);
                    Date previousWeek = calendar.getTime();
                    if (!(today.before(previousWeek)
                            || today.after(student.getModule(i).getAssessmentByIndex(j).getDeadline().getTime()))) {
                        upComingAssessments.add(student.getModule(i).getAssessmentByIndex(j));
                    }
                }
            }
        }
        return upComingAssessments;
    }
    
    public boolean findSemesterFile(String username){
        String filename = username + ".txt";
        File dir = new File(".");
        FilenameFilter condition = (File dir1, String name) -> name.equals(filename); 
        String[] files = dir.list(condition);
        System.out.println(filename);
        return (files != null && dir.list(condition).length > 0);
    }
    
    public boolean uploadFile(String username, String source) throws IOException{
        //check file is valid here. method will return false if not valid.
        String fileName = username + ".txt";
        Path FROM = Paths.get(source);
        Path TO = Paths.get(".\\" + fileName);
        CopyOption[] options = new CopyOption[]{
            StandardCopyOption.REPLACE_EXISTING,
            StandardCopyOption.COPY_ATTRIBUTES
          };
        Files.copy(FROM, TO, options);
        return true;
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
