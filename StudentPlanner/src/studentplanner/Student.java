/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author natha
 */
public class Student {
    
    private int studentID;
    private String fullName;
    private String userName;
    private String password;
    private String emailAddress;
    //THIS CAN BE AN ENUM
    private String schoolOfStudy;
    private int yearOfStudy;
    private ArrayList<Module> modules;
    private ArrayList<Milestone> milestones;
    //DASHBOARD CLASS MUST BE IMPLEMENTED FIRST
    private Dashboard dashboard;
    public File semesterFile;
    
    public Student(int studentID, String fullName, String emailAddress, 
            String schoolOfStudy, int yearOfStudy, ArrayList<Module> modules, File semesterFile){
        this.studentID = studentID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.schoolOfStudy = schoolOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.modules = modules;
        this.semesterFile = semesterFile;
    }
    
    public int getStudentID(){
        return studentID;
    }
    
    public String getFullName(){
        return fullName;
    }
    
    public String getUserName(){
        return userName;
    }
    
    public String getEmailAddress(){
        return emailAddress;
    }
    
    public String getSchoolOfStudy(){
        return schoolOfStudy;
    }
    
    public int getYearOfStudy(){
        return yearOfStudy;
    }
    
    Module getModule(int i){
        return modules.get(i);
    }
    
    ArrayList<Module> getModules(){
        return modules;
    }
    
    Milestone getMilestone(int i){
        return milestones.get(i);
    }
    
    ArrayList<Milestone> getMilestones(){
        return milestones;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Name: \t\t").append(fullName).append("\n");
        str.append("Email:\t\t").append(emailAddress).append("\n");
        str.append("School:\t\t").append(schoolOfStudy).append("\n");
        return str.toString();
    }
    
    void setSemesterFile(File semesterFile) throws FileNotFoundException, IOException, ParseException {
        Scanner fileScan = new Scanner( semesterFile );
        
        while(fileScan.hasNextLine()){
             
            String [] details = fileScan.nextLine().split("/");
            Admin admin = new Admin(details[2], details[3]);
            Module module = new Module(details[0],details[1], admin);
            SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
            ArrayList<Task> taskList = new ArrayList<>();
            
            for(int i = 4; i<details.length;){
                switch(details[i].charAt(0)){
                    case 'A':
                        boolean summative = Boolean.parseBoolean(details[i+6]);
                        Date assessmentDate = formatter.parse(details[i+3]);
                        Deadline assessmentDeadline = new Deadline(assessmentDate);
                        Assignment assignment = new Assignment(details[i+4], details[i+5],
                        summative, details[i], details[i+1], Double.parseDouble(details[i+2]),0.0,
                        assessmentDeadline,taskList, null);
                        i+=7;
                        module.addAssessment(assignment);
                        break;
                        
                    case 'E':
                        Date examDate = formatter.parse(details[i+3]);
                        Deadline examDeadline = new Deadline(examDate);
                        Exam exam = new Exam(details[i+5], Integer.parseInt(details[i+4]), details[i], details[i+1], 
                                Double.parseDouble(details[i+2]), 0.0, examDeadline, taskList, null );
                        i+=6;
                        module.addAssessment(exam);
                        break;
                }
            }
            modules.add(module);
        }  
    }
    
    public void updateFileForAssessment(File semesterFile, Module mod,  Assignment assignment) throws IOException{
        
        
        Scanner fileScan = new Scanner( semesterFile );
        Format formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        
            while(fileScan.hasNextLine()){
                String [] module = fileScan.nextLine().split("/");
                
                if(module[0].equals(mod.getModuleCode())){
                    
                    String moduleString ="";
                    moduleString += module[0] + "/";
                    moduleString += module[1] + "/";
                    moduleString += module[2] + "/";
                    moduleString += module[3] + "/";
                    
                    for(int i=4; i< module.length; i++){
                        moduleString += module[i] + "/";
                    }
                    moduleString = moduleString.substring(0,moduleString.length()-1);
                    
                    String [] updatedModule = module;
                    updatedModule[0] = mod.getModuleCode();
                    updatedModule[1] = mod.getModuleName();
                    updatedModule[2] = mod.getModuleOrganiser().getName();
                    updatedModule[3] = mod.getModuleOrganiser().getEmail();
                            
                    for(int i=4; i<module.length;){
                        
                        if(module[i].charAt(0) == 'E'){
                        i+=9;
                        }
                        else if(module[i].equals(assignment.getAssessmentCode())){
                            System.out.println("here");
                                
                                updatedModule[i+1] = assignment.getAssessmentTitle();
                                updatedModule[i+2] = Double.toString(assignment.getWeighting());
                                updatedModule[i+3] = Double.toString(assignment.getGrade());
                                updatedModule[i+4] = formatter.format(assignment.getDeadline().getTime());
                                updatedModule[i+5] = assignment.getHandInProcedure();
                                updatedModule[i+6] = assignment.getAssignmentType();
                                updatedModule[i+7] = String.valueOf(assignment.getIsSummative());
                                updatedModule[i+8] = "";
                                for(Task task: assignment.getTasks()){
                                    updatedModule[i+8] += task.taskToFile();
                                    updatedModule[i+8] += "#";
                                }
                                updatedModule[i+8] = updatedModule[i+8].substring(0,updatedModule[i+8].length()-1);
                                updatedModule[i+9] = assignment.getNotes();
                                
//                                if(i+10 < module.length){
//                                    updatedModule[i+9] +=  "/";
//                                }
                                
                         i+=10;    
                        }
                        
                    }
                    
                    String updatedModuleString ="";
                    updatedModuleString += updatedModule[0] + "/";
                    updatedModuleString += updatedModule[1] + "/";
                    updatedModuleString += updatedModule[2] + "/";
                    updatedModuleString += updatedModule[3] + "/";
                    
                    for(int i=4; i< module.length; i++){
                        updatedModuleString += updatedModule[i] + "/";
                    }
                    updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
                    
                    System.out.println(moduleString);
                       System.out.println(updatedModuleString);
                       System.out.println("\n\n\n");
                    
                    
                    FileOutputStream fileOut = new FileOutputStream("semster.txt");
                    fileOut.write(updatedModuleString.getBytes());
                    fileOut.close();
                    
                }
            }
            
    }
}
