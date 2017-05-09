/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.*;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author me
 */
public class Dashboard {

    ArrayList<Module> modules;
    ArrayList<Milestone> milestones;
    Student student;
    
    public Dashboard(){
        modules = new ArrayList();
        milestones = new ArrayList();
        
    }
    
    static void setSemesterFile(File semesterFile) throws FileNotFoundException, IOException, ParseException {
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
                        //System.out.println(assignment);
                        break;
                    case 'E':
                        Date examDate = formatter.parse(details[i+3]);
                        Deadline examDeadline = new Deadline(examDate);
                        Exam exam = new Exam(details[i+5], Integer.parseInt(details[i+4]), details[i], details[i+1], 
                                Double.parseDouble(details[i+2]), 0.0, examDeadline, taskList, null );
                        i+=6;
                        module.addAssessment(exam);
                        //System.out.println(exam);
                        break;
                }
            }
            System.out.println(module);
        }  
    }

    Module getModule(int i) {
        return modules.get(i);
    }

    ArrayList<Module> getModules() {
        return modules;
    }

    Milestone getMilestone(int i) {
        return milestones.get(i);
    }

    ArrayList<Milestone> getMilestones() {
        return milestones;
    }
    
    public static void main(String[] args) throws IOException, ParseException{
        File newFile = new File("semester.txt");
        setSemesterFile(newFile);
        
        
    
    }

}
