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
    static void setSemesterFile(File semesterFile) throws FileNotFoundException, IOException, ParseException {
//        InputStream semesterFileStream = new FileInputStream(semesterFile);
//        BufferedReader semesterFileReader = new BufferedReader(new InputStreamReader(semesterFileStream));
//        String temp = "";
//        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");//Needs tweaking to correct date format
//        while ((temp = semesterFileReader.readLine()) != null) { //*Might* readline() twice, needs testing
//            temp = semesterFileReader.readLine();
//            String[] tokens = temp.split(",");
//            Admin newAdmin = new Admin(tokens[2], tokens[3]);
//            Module newModule = new Module(tokens[0], tokens[1], newAdmin);
//            while (temp.charAt(0) == 'A' || temp.charAt(0) == 'E') {
//                temp = semesterFileReader.readLine();
//                tokens = temp.split(",");
//                if (temp.charAt(0) == 'A') {
//                    Date newDate = formatter.parse(tokens[3]);
//                    Deadline newDeadline = new Deadline(newDate);
//                    boolean isSummative = Boolean.valueOf(tokens[6]);
//                    double weighting = Double.parseDouble(tokens[2]);
//                    Assignment newAssignment = new Assignment(tokens[4], tokens[5], isSummative, tokens[0], tokens[1], weighting, 0, newDeadline, null, null);
//                } else if (temp.charAt(0) == 'E') {
//                    double weighting = Double.parseDouble(tokens[2]);
//                    int duration = Integer.parseInt(tokens[5]);
//                    Date newDate = formatter.parse(tokens[3]);
//                    Deadline newDeadline = new Deadline(newDate);
//                    Exam newExam = new Exam(tokens[4], duration, tokens[0], tokens[1], weighting, 0, newDeadline, null, null);
//                }
//            }

            ///EXAM
//            String examRoom, int examDuration, String assessmentCode, String assessmentTitle, double weighting,
//        double grade, Deadline deadline, ArrayList<Task> taskList, 
//        String notes
//        }
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
                        System.out.println(assignment);
                        break;
                    case 'E':
                        Date examDate = formatter.parse(details[i+3]);
                        Deadline examDeadline = new Deadline(examDate);
                        Exam exam = new Exam(details[i+5], Integer.parseInt(details[i+4]), details[i], details[i+1], 
                                Double.parseDouble(details[i+2]), 0.0, examDeadline, taskList, null );
                        i+=6;
                        module.addAssessment(exam);
                        System.out.println(exam);
                        break;
                }
            }
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
