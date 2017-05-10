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
    
    public Student(int studentID, String fullName, String emailAddress, 
            String schoolOfStudy, int yearOfStudy, ArrayList<Module> modules){
        this.studentID = studentID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.schoolOfStudy = schoolOfStudy;
        this.yearOfStudy = yearOfStudy;
        this.modules = modules;
    }
//    
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
}
