/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;

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
    //private Dashboard dashboard;
    
//    public Student(int studentID, String fullName, String emailAddress, 
//            String schoolOfStudy, int yearOfStudy, Dashboard dashboard){
//        this.studentID = studentID;
//        this.fullName = fullName;
//        this.emailAddress = emailAddress;
//        this.schoolOfStudy = schoolOfStudy;
//        this.yearOfStudy = yearOfStudy;
//        this.dashboard = dashboard;
//    }
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
}
