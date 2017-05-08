/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.*;
import java.io.*;

/**
 *
 * @author me
 */
public class Dashboard {
    
    ArrayList<Module> modules;
    ArrayList<Milestone> milestones;
    
    void setSemesterFile(File semesterFile) throws FileNotFoundException, IOException{
        InputStream semesterFileStream = new FileInputStream(semesterFile);
        BufferedReader semesterFileReader = new BufferedReader(new InputStreamReader(semesterFileStream));
        String temp = "";
        while((temp = semesterFileReader.readLine()) != null){ //*Might* readline() twice, needs testing
            temp = semesterFileReader.readLine();
            String[] tokens = temp.split(",");
            Admin newAdmin = new Admin(tokens[2], tokens[3]);
            Module newModule = new Module(tokens[0], tokens[1], newAdmin);
            temp = semesterFileReader.readLine();
            if(temp.charAt(0)=='A'){
                Assignment newAssignment = new Assignment()
            }
            else if(temp.charAt(0)=='E'){
            
            }
            
            
            
            
            ///Assignment
//            public Assignment(String handInProcedure, String assignmentType, Double weight, 
//            Deadline deadline, String code, String title, Double grade, String notes, boolean isSummative){
            
            
            
            
            ///EXAM
//            String examRoom, int examDuration, String assessmentCode, String assessmentTitle, double weighting,
//        double grade, Deadline deadline, ArrayList<Task> taskList, 
//        String notes
            
            
            
            
        }
    
    
    
    }
    
    
}
