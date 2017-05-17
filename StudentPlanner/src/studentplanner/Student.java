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
import java.util.Calendar;
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
    private String emailAddress;
    private String schoolOfStudy;
    private int yearOfStudy;
    private ArrayList<Module> modules;
    private ArrayList<Milestone> milestones;
    private File semesterFile;
    private int numberOfTasks;
    private int numberOfActivities;
    
    
    public Student(int studentID, String fullName, String emailAddress, 
            String schoolOfStudy, int yearOfStudy, ArrayList<Module> modules){
        this.studentID = studentID;
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.schoolOfStudy = schoolOfStudy;
        
        this.yearOfStudy = yearOfStudy;
        this.modules = modules;
    }
    
    public Student(File semesterFile) throws FileNotFoundException, IOException, ParseException{
        
        Scanner fileScan = new Scanner( semesterFile );
        
        ArrayList<Activity>activitiesList = new ArrayList<>();
        ArrayList<Module> modulesFromFile = new ArrayList<>();
        String [] stu = fileScan.nextLine().split("/");
                this.studentID = Integer.parseInt(stu[0]);
                this.fullName = stu[1];
                this.userName = stu[2];
                this.emailAddress = stu[3];
                this.schoolOfStudy = stu[4];
                this.yearOfStudy = Integer.parseInt(stu[5]);
                numberOfTasks = 0;
                numberOfActivities = 0;
        while(fileScan.hasNextLine()){
                String [] details = fileScan.nextLine().split("/");
                Admin admin = new Admin(details[2], details[3]);
                boolean moduleCompleted = Boolean.parseBoolean(details[5]);
                Module module = new Module(details[0],details[1], admin,Double.parseDouble(details[4]),moduleCompleted,details[6] );
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                
                for(int i = 7; i<details.length;){
                    switch(details[i].charAt(0)){
                        
                        case 'A':
                            boolean summative = Boolean.parseBoolean(details[i+7]);
                            Date assessmentDate = formatter.parse(details[i+4]);
                            Deadline assessmentDeadline = new Deadline(assessmentDate);
                            ArrayList<Task> assignmentTaskList = new ArrayList<>();

                            if(!"".equals(details[i+8])){
                                String [] assignmentTasks = details[i+8].split("#");
                                for(int j=0; j<assignmentTasks.length;){
                                    ArrayList<Activity> assignmentTaskActivityList = new ArrayList<>();
                                    
                                    if(!"".equals(assignmentTasks[j+4])){
                                        String [] activityTaskActivities = assignmentTasks[j+4].split("~");
                                        for(int k=0; k<activityTaskActivities.length;k+=7){
                                            boolean found = false;
                                            Activity a = null;
                                            for(int h=0; h<activitiesList.size(); h++){
                                                if(activityTaskActivities[k].equals(activitiesList.get(h).getActivityID())){
                                                    found = true;
                                                    a = activitiesList.get(h);
                                                }
                                            }
                                            if(found){
                                                assignmentTaskActivityList.add(a);
                                            }
                                            else{
                                                Date startDate;
                                                if(activityTaskActivities[k+4].equals("")){
                                                    startDate = null;
                                                }
                                                else{
                                                    startDate = formatter.parse(activityTaskActivities[k+4]);
                                                    
                                                }
                                                Date endDate;
                                                if(activityTaskActivities[k+5].equals("")){
                                                    endDate = null;
                                                }
                                                else{
                                                    endDate = formatter.parse(activityTaskActivities[k+5]);
                                                }
                                                boolean activityComplete = Boolean.parseBoolean(activityTaskActivities[k+3]);
                                                Activity activity = new Activity(activityTaskActivities[k], activityTaskActivities[k+1],
                                                activityTaskActivities[k+6], activityComplete, Double.parseDouble(activityTaskActivities[k+2]),startDate,endDate);
                                                numberOfActivities++;
                                                assignmentTaskActivityList.add(activity);
                                                activitiesList.add(activity);
                                            }

                                        }

                                    }
                                    boolean taskComplete = Boolean.parseBoolean(assignmentTasks[j+3]);
                                    Task task = new Task(assignmentTasks[j+1], assignmentTasks[j],
                                    assignmentTasks[j+5],assignmentTaskActivityList, null, Double.parseDouble(assignmentTasks[j+2]),
                                    taskComplete);
                                    numberOfTasks++;
                                    assignmentTaskList.add(task);
                                    j+=6;
                                }

                            }
                            Assignment assignment = new Assignment(details[i+5], details[i+6],
                            summative, details[i], details[i+1], Double.parseDouble(details[i+2]),Double.parseDouble(details[i+3]),
                            assessmentDeadline,assignmentTaskList, details[i+9], "Assignment");
                            i+=10;
                            module.addAssessment(assignment);

                            for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
                                module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(assignment);
                            }
                            break;
                        case 'E':
                            Date examDate = formatter.parse(details[i+4]);
                            Deadline examDeadline = new Deadline(examDate);
                            ArrayList<Task> examTaskList = new ArrayList<>();
                            if(!"".equals(details[i+7])){
                                String [] examTasks = details[i+7].split("#");
                                for(int j=0; j<examTasks.length;){
                                    ArrayList<Activity> examTaskActivityList = new ArrayList<>();
                                    System.out.println(examTasks[j]);
                                    if(!"".equals(examTasks[j+4])){
                                        String [] examTaskActivities = examTasks[j+4].split("~");
                                        for(int k=0; k<examTaskActivities.length;k+=7){
                                            boolean found = false;
                                            Activity a = null;
                                            for(int h=0; h<activitiesList.size(); h++){
                                                if(examTaskActivities[k].equals(activitiesList.get(h).getActivityID())){
                                                    found = true;
                                                    a = activitiesList.get(h);
                                                }
                                            }
                                            if(found){
                                                examTaskActivityList.add(a);
                                            }
                                            else{
                                                Date startDate;
                                                if(examTaskActivities[k+4].equals("")){
                                                    startDate = null;
                                                }
                                                else{
                                                    startDate = formatter.parse(examTaskActivities[k+4]);
                                                }
                                                
                                                Date endDate;
                                                if(examTaskActivities[k+5].equals("")){
                                                    endDate = null;
                                                }
                                                else{
                                                    endDate = formatter.parse(examTaskActivities[k+5]);
                                                }
                                                boolean activityComplete = Boolean.parseBoolean(examTaskActivities[k+3]);
                                                Activity activity = new Activity(examTaskActivities[k], examTaskActivities[k+1],
                                                examTaskActivities[k+4], activityComplete, Double.parseDouble(examTaskActivities[k+2]),startDate,endDate);
                                                numberOfActivities++;
                                                examTaskActivityList.add(activity);
                                                activitiesList.add(activity);
                                            }
                                        }
                                    }
                                    boolean taskComplete = Boolean.parseBoolean(examTasks[j+3]);
                                    Task task = new Task(examTasks[j+1], examTasks[j],
                                    examTasks[j+5],examTaskActivityList, null, Double.parseDouble(examTasks[j+2]),
                                    taskComplete);
                                    numberOfTasks++;
                                    examTaskList.add(task);
                                    j+=6;
                                }
                            }
                            Exam exam = new Exam(details[i+6], Integer.parseInt(details[i+5]), details[i], details[i+1], 
                                    Double.parseDouble(details[i+2]), Double.parseDouble(details[i+3]), examDeadline, examTaskList, details[i+8], "Exam" );
                            i+=9;
                            module.addAssessment(exam);
                            for(int j=0; j<module.getAssessmentByIndex(module.getAssessments().size()-1).getTasks().size();j++){
                                module.getAssessmentByIndex(module.getAssessments().size()-1).getTask(j).setAssessment(exam);
                            }
                            break;
                    }
            
            }
                module.calculateCurrentGrade();
            modulesFromFile.add(module);
        }
        this.modules = modulesFromFile;
        milestones = new ArrayList();
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
    
    public Module getModule(int i){
        return modules.get(i);
    }
    
    public ArrayList<Module> getModules(){
        return modules;
    }
    
    public Module getModuleByCode(String moduleCode){
        for(Module currentModule: modules){
            if(currentModule.getModuleCode().equals(moduleCode)){
                return currentModule;
            }
        }
        return null;
    }
    
    public Milestone getMilestone(int i){
        return milestones.get(i);
    }
    
    public ArrayList<Milestone> getMilestones(){
        return milestones;
    }
    
    public File getSemesterFile(){
        return semesterFile;
    }
    
    public void setFile(File f){
        semesterFile = f;
    }
    
    public void printSemesterFile() throws FileNotFoundException{
        Scanner fileScan = new Scanner( semesterFile );
        while(fileScan.hasNextLine()){
            String line = fileScan.nextLine();
            System.out.println(line);
        }
    }
    
    public int getNumberOfTasks(){
        return numberOfTasks;
    }
    
    public void incrementNumberOfTasks(){
        numberOfTasks++;
    }
    
    public int getNumberOfActivities(){
        return numberOfActivities;
    }
    
    public void incrementNumberOfActivities(){
        numberOfActivities++;
    }
  
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("Name: \t\t").append(fullName).append("\n");
        str.append("User Name: \t\t").append(userName).append("\n");
        str.append("Email:\t\t").append(emailAddress).append("\n");
        str.append("School:\t\t").append(schoolOfStudy).append("\n");
        str.append("Year: \t\t").append(yearOfStudy).append("\n");
        return str.toString();
    }
    
     public static boolean checkFile(File semesterFile) throws FileNotFoundException, ParseException{
        boolean acceptable = true;
        String errors = "";
        String regExp = "[\\x00-\\x20]*[+-]?(((((\\p{Digit}+)(\\.)?((\\p{Digit}+)?)([eE][+-]?"
        + "(\\p{Digit}+))?)|(\\.((\\p{Digit}+))([eE][+-]?(\\p{Digit}+))?)|(((0[xX](\\p"
        + "{XDigit}+)(\\.)?)|(0[xX](\\p{XDigit}+)?(\\.)(\\p{XDigit}+)))[pP][+-]?(\\p{Digit}+)))"
        + "[fFdD]?))[\\x00-\\x20]*";
        Scanner fileScan = new Scanner( semesterFile );
        
        String [] stu = fileScan.nextLine().split("/");
                for(int i=0; i<stu[0].length(); i++){
                    if(!Character.isDigit(stu[0].charAt(i))){
                        errors += "Error on line 1: studentID must only"
                                + " consist of numbers\n";
                        acceptable = false;
                    }     
                }
                for(int i=0; i<stu[1].length(); i++){
                    if(!Character.isLetter(stu[1].charAt(i)) && stu[1].charAt(i)!=' ' &&
                            stu[1].charAt(i) != '-'){
                        errors +="Error on line 1: A name can only "
                                + "contain letters, spaces and dashes\n";
                        acceptable = false;
                    }     
                }
                if(stu[2].length()!=8){
                    errors +="Error on line 1: A username must have"
                            + " 8 characers\n";
                    acceptable = false;
                }
                for(int i=0; i<stu[2].length(); i++){
                    if(i<3){  
                        if(!Character.isLetter(stu[2].charAt(i))){
                        errors +="Error on line 1: The first 3 characters"
                                + " of a username must be letters\n";
                        acceptable = false;
                        } 
                    }
                    else if(i<5){
                        if(!Character.isDigit(stu[2].charAt(i))){
                        errors +="Error on line 1: Characters 4 and 5 "
                                + "of a username bust be numbers\n";
                        acceptable = false;
                        } 
                    }
                    else{
                        if(!Character.isLetter(stu[2].charAt(i))){
                        errors +="Error on line 1: The last 3 characters"
                                + " of a username must be letters\n";
                        acceptable = false;
                        }
                    }
                }
                if(stu[3].contains("@")){
                    int pos = stu[3].indexOf("@");
                    if(pos+1 != stu[3].lastIndexOf("uea.ac.uk") ){
                        errors +="Error on line 1: and email must finish with"
                                + " \"@uea.ac.uk\"\n";
                        acceptable = false;
                    }
                }
                else{
                     errors +="Error on line 1: and email must finish with"
                                + " \"@uea.ac.uk\"\n";
                     acceptable = false;
                }
        int line = 2;
        while(fileScan.hasNextLine()){
                String [] details = fileScan.nextLine().split("/");
                if(details[0].length()!=9){
                    errors +="Error on line " + line + ": A module code must have"
                            + " 9 characers\n";
                    acceptable = false;
                }
                for(int i=0; i<details[0].length(); i++){
                    if(i<3){  
                        if(!Character.isLetter(details[0].charAt(i))){
                        errors +="Error on line " + line + ": The first 3 characters"
                                + " of a module code must be letters\n";
                        acceptable = false;
                        } 
                    }
                    else if(i==3){
                        if(details[0].charAt(i)!='-'){
                        errors +="Error on line " + line + ": Character 4 "
                                + "of a module code must be a '-'\n";
                        acceptable = false;
                        } 
                    }
                    else if(i<8){
                        if(!Character.isDigit(details[0].charAt(i))){
                        errors +="Error on line " + line + ": Characters"
                                + " 5-8 of a module code must be numbers\n";
                        acceptable = false;
                        }
                    }
                    else{
                        if(!Character.isLetter(details[0].charAt(i))){
                        errors +="Error on line " + line + ": The last character"
                                + " of a module code must be a letter\n";
                        acceptable = false;
                        } 
                    }
                }
                
                for(int i=0; i<details[1].length(); i++){
                    if(!Character.isLetter(details[1].charAt(i)) && details[1].charAt(i)!=' ' &&
                            details[1].charAt(i) != '-' && !Character.isDigit(details[1].charAt(i))){
                        errors +="Error on line " + line + ": A module name can only contain "
                                + "characters, spaces, dashes and digits\n";
                        acceptable = false;
                    }
                }
                
                for(int i=0; i<details[2].length(); i++){
                    if(!Character.isLetter(details[2].charAt(i)) && details[2].charAt(i)!=' ' &&
                            details[2].charAt(i) != '-'){
                        errors +="Error on line " + line + ": An Andmin name can only "
                                + "contain letters, spaces and dashes\n";
                        acceptable = false;
                    }     
                }
                  if(details[3].contains("@")){
                    int pos = details[3].indexOf("@");
                    if(pos+1 != details[3].lastIndexOf("uea.ac.uk") ){
                        errors +="Error on line " + line + ": and email must finish with"
                                + " \"@uea.ac.uk\"\n";
                        acceptable = false;
                    }
                }
                else{
                     errors +="Error on line " + line + ": and email must finish with"
                                + " \"@uea.ac.uk\"\n";
                     acceptable = false;
                }
                if(!details[4].matches(regExp)){
                    errors +="Error on line " + line + ": The weighting for an"
                    + " assignment task must be a double\n";
                    acceptable = false;
                }
                if(!details[5].equals("true") && !details[5].equals("false")){
                    errors +="Error on line " + line + ": \""
                    + details[5] + "\" must be a boolean value\n";
                    acceptable = false;
                }
                  
                for(int i = 7; i<details.length;){
                    switch(details[i].charAt(0)){
                        case 'A':
                            if(!details[i+7].equals("true") && !details[i+7].equals("false")){
                                errors +="Error on line " + line + ": \""
                                + details[i+7] + "\" must be a boolean value\n";
                                acceptable = false;
                            }
                            
                            if(details[i+4].length()!= 19){
                                errors +="Error on line " + line + ": A deadline"
                                        + " must have 19 characters\n";
                                acceptable = false;
                            }
                            for(int j=0; j<details[i+4].length(); j++){
                                if(j == 4 || j==7){
                                    if(details[i+4].charAt(j) != '-'){
                                        errors +="Error on line " + line + ": The year, month and day"
                                                + " must be seperated with a '-'\n";
                                    acceptable = false;
                                    }
                                }
                                else if(j== 13|| j== 16){
                                    if(details[i+4].charAt(j) != ':'){
                                    errors +="Error on line " + line + ": The hours, minuts and"
                                                + " seconds must be seperated with a ':'\n";
                                    acceptable = false;
                                    }
                                }
                                else if(j==10){
                                    if(details[i+4].charAt(j)!= ' '){
                                        errors +="Error on line " + line + ": The date"
                                                + " and time must be seperated by a space\n";
                                    }
                                }
                                else{
                                    if(!Character.isDigit(details[i+4].charAt(j))){
                                        
                                        errors +="Error on line " + line + ": The year, "
                                                + "month, day, hours, minutes and seconds must be "
                                                + "represented as numbers\n";
                                    acceptable = false;
                                    }
                                    }
                                }
                            
                            Date date=new Date();
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(date);
                            int year = calendar.get(Calendar.YEAR);
                            int nextYear = year + 1;
                            int previousYear = year -1;
                            String y = details[i+4].substring(0, Math.min(details[i+4].length(), 4));
                            if(!y.equals(Integer.toString(year)) && !y.equals(Integer.toString(nextYear))
                                    && !y.equals(Integer.toString(previousYear))){
                                errors +="Error on line " + line + ": The year for a deadline "
                                        + "must be this year, the previous year or the next year\n";
                                acceptable = false;
                            }
                            int month = Integer.parseInt(details[i+4].substring(5, Math.min(details[i+4].length(), 7)));
                            int day = Integer.parseInt(details[i+4].substring(8, Math.min(details[i+4].length(), 10)));
                            
                            if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
                                if(day > 31){
                                    errors +="Error on line " + line + ": There can't be "
                                    + day + "s in the " + month + " month (max 31 days)\n";
                                    acceptable = false;
                                }
                            }
                            else if(month == 4 || month == 6 || month == 9 || month == 11){
                                if(day > 30){
                                    errors +="Error on line " + line + ": There can't be "
                                    + day + "s in the " + month + " month (max 30 days)\n";
                                    acceptable = false;
                                }
                            }
                            else{
                                if(Integer.parseInt(y)%4 == 0){
                                    if(day > 29){
                                        errors +="Error on line " + line + ": There can't be "
                                        + day + "s in the " + month + " month in a leap year(max 29 days)\n";
                                        acceptable = false;
                                    }
                                }
                                else{
                                    if(day>28){
                                        errors +="Error on line " + line + ": There can't be "
                                        + day + "s in the " + month + " month(max 28 days)\n";
                                        acceptable = false;
                                    }
                                }
                            }

                            if(!"".equals(details[i+8])){
                                String [] assignmentTasks = details[i+8].split("#");
                                for(int j=0; j<assignmentTasks.length;){
                                    if(!"".equals(assignmentTasks[j+4])){
                                        String [] activityTaskActivities = assignmentTasks[j+4].split("~");
                                        for(int k=0; k<activityTaskActivities.length;k+=7){
                                            if(!activityTaskActivities[k+3].equals("true") && 
                                                    !activityTaskActivities[k+3].equals("false")){
                                                errors +="Error on line " + line + ": \""
                                                + activityTaskActivities[k+3] + "\" must be a boolean value\n";
                                                acceptable = false;
                                            }
                                            
                                            if(activityTaskActivities[k].length()!= 8){
                                                errors +="Error on line " + line + ": The ID for an"
                                                        + " assignment activity must be 8 characters long\n";
                                                acceptable = false;
                                            }
                                                
                                            
                                            if(activityTaskActivities[k].charAt(0)!='a'){
                                                errors +="Error on line " + line + ": The ID for an"
                                                        + " activity must start with 'a'\n";
                                                acceptable = false;
                                            }
                                            
                                            String activityID2 = activityTaskActivities[k].substring(2, Math.min(activityTaskActivities[k].length(), 8));
                                            for(int h=0; h<activityID2.length(); h++){
                                               if(!Character.isDigit(activityID2.charAt(h))){
                                                   errors +="Error on line " + line + ": The ID for an"
                                                        + " activity must end in 7 digits\n";
                                                acceptable = false;
                                               }
                                            }
                                            
                                            if(!activityTaskActivities[k+2].matches(regExp)){
                                                errors +="Error on line " + line + ": The weighting for an"
                                                        + " assignment activity must be a double\n";
                                                acceptable = false;
                                            }

                                        }

                                    }
                                    if(!assignmentTasks[j+3].equals("true") && 
                                            !assignmentTasks[j+3].equals("false")){
                                        errors +="Error on line " + line + ": \""
                                        + assignmentTasks[j+3] + "\" must be a boolean value\n";
                                        acceptable = false;
                                    }
                                    
                                    if(assignmentTasks[j].length()!= 7){
                                                errors +="Error on line " + line + ": The ID for an"
                                                        + " assignment task must be 7 characters long\n";
                                                acceptable = false;
                                    }
                                                
                                    String taskID1 = assignmentTasks[j].substring(0, Math.min(assignmentTasks[j].length(), 2));
                                    if(!taskID1.equals("aT")){
                                        errors +="Error on line " + line + ": The ID for an"
                                            + " assignment activity must start with \"aT\"\n";
                                        acceptable = false;
                                    }
                                            
                                    String taskID2 = assignmentTasks[j].substring(2, Math.min(assignmentTasks[j].length(), 7));
                                    for(int h=0; h<taskID2.length(); h++){
                                        if(!Character.isDigit(taskID2.charAt(h))){
                                            errors +="Error on line " + line + ": "
                                                + " assignment activity must end in 5 digits\n";
                                                acceptable = false;
                                        }
                                    }
                                    
                                    if(!assignmentTasks[j+2].matches(regExp)){
                                        errors +="Error on line " + line + ": The weighting for an"
                                        + " assignment task must be a double\n";
                                        acceptable = false;
                                    }
                                    j+=6;
                                }

                            }
                            
                            for(int j=0; j<details[i+5].length(); j++){
                                if(!Character.isLetter(details[i+5].charAt(j)) &&
                                        details[i+5].charAt(j) != ' '){
                                    errors +="Error on line " + line + ": The hand in procedure "
                                            + "for an assignment can only contain letters and spaces\n";
                                        acceptable = false;
                                }
                            }
                            
                            for(int j=0; j<details[i+6].length(); j++){
                                if(!Character.isLetter(details[i+6].charAt(j)) &&
                                        details[i+6].charAt(j) != ' '){
                                    errors +="Error on line " + line + ": The assignment type "
                                            + "can only contain letters and spaces\n";
                                        acceptable = false;
                                }
                            }
                            
                            if(details[i].length()!= 4){
                            errors +="Error on line " + line + ": The assignment ID"
                                    + " must be 4 characters long\n";
                                        acceptable = false;
                            }
                            
                            if(details[i].charAt(0)!='A'){
                                errors +="Error on line " + line + ": The first character "
                                        + "for an assignment ID must be an 'A'\n";
                                        acceptable = false;
                            }
                            
                            for(int j=1; j<details[i].length(); j++){
                                if(!Character.isDigit(details[i].charAt(j))){
                                    errors +="Error on line " + line + ": The last 3 characters "
                                        + "for an assignment ID must be digits\n";
                                        acceptable = false;
                                }
                            }
                            
                            for(int j=0; j<details[i+1].length(); j++){
                                if(!Character.isLetter(details[i+1].charAt(j)) &&
                                        details[i+1].charAt(j) != ' ' && !Character.isDigit(details[i+1].charAt(j))
                                        && details[i+1].charAt(j) != '-' && details[i+1].charAt(j) != '+'){
                                    errors +="Error on line " + line + ": The assignment name "
                                            + "can only contain letters, spaces, numbers and dashes\n";
                                        acceptable = false;
                                }
                            }
                            
                            if(!details[i+2].matches(regExp)){
                                        errors +="Error on line " + line + ": The weighting for an"
                                        + " assignment must be a double\n";
                                        acceptable = false;
                            }
                            
                            if(!details[i+3].matches(regExp)){
                                        errors +="Error on line " + line + ": The grade for an"
                                        + " assignment must be a double\n";
                                        acceptable = false;
                            }
                            i+=10;
                            break;
                        case 'E':
                            if(details[i+4].length()!= 19){
                                errors +="Error on line " + line + ": A deadline"
                                        + " must have 19 characters\n";
                                acceptable = false;
                            }
                            for(int j=0; j<details[i+4].length(); j++){
                                if(j == 4 || j==7){
                                    if(details[i+4].charAt(j) != '-'){
                                        errors +="Error on line " + line + ": The year, month and day"
                                                + " must be seperated with a '-'\n";
                                    acceptable = false;
                                    }
                                }
                                else if(j== 13|| j== 16){
                                    if(details[i+4].charAt(j) != ':'){
                                    errors +="Error on line " + line + ": The hours, minuts and"
                                                + " seconds must be seperated with a ':'\n";
                                    acceptable = false;
                                    }
                                }
                                
                                else if(j==10){
                                    if(details[i+4].charAt(j)!= ' '){
                                        errors +="Error on line " + line + ": The date"
                                                + " and time must be seperated by a space\n";
                                    }
                                }
                                else{
                                    if(!Character.isDigit(details[i+4].charAt(j))){
                                        errors +="Error on line " + line + ": The year, "
                                                + "month, day, hours, minuts and seconds must be "
                                                + "represented as numbers\n";
                                    acceptable = false;
                                    }
                                }
                            }
                            
                            Date examDate=new Date();
                            Calendar examCalendar = Calendar.getInstance();
                            examCalendar.setTime(examDate);
                            int examYear = examCalendar.get(Calendar.YEAR);
                            int nextExamYear = examYear + 1;
                            int previousExamYear = examYear -1;
                            String examY = details[i+4].substring(0, Math.min(details[i+4].length(), 4));
                            if(!examY.equals(Integer.toString(examYear)) && !examY.equals(Integer.toString(nextExamYear))
                                    &&!examY.equals(Integer.toString(previousExamYear))){
                                errors +="Error on line " + line + ": The year for a deadline "
                                        + "must be this year, the previous year or the next year\n";
                                acceptable = false;
                            }
                            int examMonth = Integer.parseInt(details[i+4].substring(5, Math.min(details[i+4].length(), 7)));
                            int examDay = Integer.parseInt(details[i+4].substring(8, Math.min(details[i+4].length(), 10)));
                            
                            if(examMonth == 1 || examMonth == 3 || examMonth == 5 || examMonth == 7 || examMonth == 8 || examMonth == 10 || examMonth == 12){
                                if(examDay > 31){
                                    errors +="Error on line " + line + ": There can't be "
                                    + examDay + "s in the " + examMonth + " month (max 31 days)\n";
                                    acceptable = false;
                                }
                            }
                            else if(examMonth == 4 || examMonth == 6 || examMonth == 9 || examMonth == 11){
                                if(examDay > 30){
                                    errors +="Error on line " + line + ": There can't be "
                                    + examDay + "s in the " + examMonth + " month (max 30 days)\n";
                                    acceptable = false;
                                }
                            }
                            else{
                                if(Integer.parseInt(examY)%4 == 0){
                                    if(examDay > 29){
                                        errors +="Error on line " + line + ": There can't be "
                                        + examDay + "s in the " + examMonth + " month in a leap year(max 29 days)\n";
                                        acceptable = false;
                                    }
                                }
                                else{
                                    if(examDay>28){
                                        errors +="Error on line " + line + ": There can't be "
                                        + examDay + "s in the " + examMonth + " month(max 28 days)\n";
                                        acceptable = false;
                                    }
                                }
                            }
                            if(!"".equals(details[i+7])){
                                String [] examTasks = details[i+7].split("#");
                                for(int j=0; j<examTasks.length;){
                                    if(!"".equals(examTasks[j+4])){
                                        String [] examTaskActivities = examTasks[j+4].split("~");
                                        for(int k=0; k<examTaskActivities.length;k+=7){
                                            if(!examTaskActivities[k+3].equals("true") && 
                                                    !examTaskActivities[k+3].equals("false")){
                                                errors +="Error on line " + line + ": \""
                                                + examTaskActivities[k+3] + "\" must be a boolean value\n";
                                                acceptable = false;
                                            }
                                            
                                            if(examTaskActivities[k].length()!= 8){
                                                errors +="Error on line " + line + ": The ID for an"
                                                        + "  activity must be 8 characters long\n";
                                                acceptable = false;
                                            }
                                                
                                            if(examTaskActivities[k].charAt(0)!='a'){
                                                errors +="Error on line " + line + ": The ID for an"
                                                        + " exam activity must start with an 'a'\n";
                                                acceptable = false;
                                            }
                                            
                                            String activityID2 = examTaskActivities[k].substring(2, Math.min(examTaskActivities[k].length(), 8));
                                            for(int h=0; h<activityID2.length(); h++){
                                               if(!Character.isDigit(activityID2.charAt(h))){
                                                   errors +="Error on line " + line + ": The ID for an"
                                                        + " activity must end in 7 digits\n";
                                                acceptable = false;
                                               }
                                            }
                                            
                                            if(!examTaskActivities[k+2].matches(regExp)){
                                                errors +="Error on line " + line + ": The weighting for an"
                                                        + " exam activity must be a double\n";
                                                acceptable = false;
                                            }

                                        }

                                    }
                                    if(!examTasks[j+3].equals("true") && 
                                            !examTasks[j+3].equals("false")){
                                        errors +="Error on line " + line + ": \""
                                        + examTasks[j+3] + "\" must be a boolean value\n";
                                        acceptable = false;
                                    }
                                    
                                    if(examTasks[j].length()!= 7){
                                                errors +="Error on line " + line + ": The ID for an"
                                                        + " exam task must be 7 characters long\n";
                                                acceptable = false;
                                    }
                                                
                                    String taskID1 = examTasks[j].substring(0, Math.min(examTasks[j].length(), 2));
                                    if(!taskID1.equals("eT")){
                                        errors +="Error on line " + line + ": The ID for an"
                                            + " exam task must start with \"eT\"\n";
                                        acceptable = false;
                                    }
                                            
                                    String taskID2 = examTasks[j].substring(2, Math.min(examTasks[j].length(), 7));
                                    for(int h=0; h<taskID2.length(); h++){
                                        if(!Character.isDigit(taskID2.charAt(h))){
                                            errors +="Error on line " + line + ": The ID for an"
                                                + " activity must end in 5 digits\n";
                                                acceptable = false;
                                        }
                                    }
                                    
                                    if(!examTasks[j+2].matches(regExp)){
                                        errors +="Error on line " + line + ": The weighting for an"
                                        + " exam task must be a double\n";
                                        acceptable = false;
                                    }
                                    j+=6;
                                }
                            }
                            for(int j=0; j<details[i+5].length(); j++){
                                if(!Character.isDigit(details[i+5].charAt(j))){
                                    errors +="Error on line " + line + ": The duration for an"
                                        + " exam task must be an integer\n";
                                        acceptable = false;
                                }
                            }
                            
                             if(details[i].length()!= 4){
                            errors +="Error on line " + line + ": The exam ID"
                                    + " must be 4 characters long\n";
                                        acceptable = false;
                            }
                            
                            if(details[i].charAt(0)!='E'){
                                errors +="Error on line " + line + ": The first character "
                                        + "for an exam ID must be an 'E'\n";
                                        acceptable = false;
                            }
                            
                            for(int j=1; j<details[i].length(); j++){
                                if(!Character.isDigit(details[i].charAt(j))){
                                    errors +="Error on line " + line + ": The last 3 characters "
                                        + "for an exam ID must be digits\n";
                                        acceptable = false;
                                }
                            }
                            
                             for(int j=0; j<details[i+1].length(); j++){
                                if(!Character.isLetter(details[i+1].charAt(j)) &&
                                        details[i+1].charAt(j) != ' ' && !Character.isDigit(details[i+1].charAt(j))
                                        && details[i+1].charAt(j) != '-'){
                                    errors +="Error on line " + line + ": The exam name "
                                            + "can only contain letters, spaces, numbers and dashes\n";
                                        acceptable = false;
                                }
                            }
                            
                            if(!details[i+2].matches(regExp)){
                                        errors +="Error on line " + line + ": The weighting for an"
                                        + " exam must be a double\n";
                                        acceptable = false;
                            }
                            
                            if(!details[i+3].matches(regExp)){
                                        errors +="Error on line " + line + ": The grade for an"
                                        + " exam must be a double\n";
                                        acceptable = false;
                            }
                            i+=9;
                            break;
                    }
            
            }
            line++;
        }
        if(errors.equals("")){
            errors = "Ok";
        }
         System.out.println(errors);
        return acceptable;
     }

     public void updateDeadlinesFromFile(File deadlineFile) throws FileNotFoundException, IOException{
        Scanner semesterFileScan = new Scanner( semesterFile );
        Scanner deadlineFileScan = new Scanner( deadlineFile );
        
        String updatedModuleString ="";
        String [] semesterFileStu = semesterFileScan.nextLine().split("/");
        String [] deadlineFileStu = deadlineFileScan.nextLine().split("/");
        
        for(int i=0; i<semesterFileStu.length; i++){
                updatedModuleString += semesterFileStu[i]+ "/";
        }
        updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
        updatedModuleString += "\n";
            
        while(semesterFileScan.hasNextLine() && deadlineFileScan.hasNextLine()){
            String [] semesterFileModule = semesterFileScan.nextLine().split("/");
            String [] deadlineFileModule = deadlineFileScan.nextLine().split("/");
            
            for(int i=7; i<semesterFileModule.length;){
                if(semesterFileModule[i].charAt(0) == 'A'){
                    semesterFileModule[i+4] = deadlineFileModule[i+4];
                    i+=10;
                }
                else if(semesterFileModule[i].charAt(0) == 'E'){
                    semesterFileModule[i+4] = deadlineFileModule[i+4];
                    i+=9;
                }
                
            }
            
            for(int i=0; i<semesterFileModule.length; i++){
                updatedModuleString += semesterFileModule[i]+ "/";
            }
            updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            updatedModuleString += "\n";
            
            
        }
        updatedModuleString = updatedModuleString.substring(0,updatedModuleString.length()-1);
            FileOutputStream fileOut = new FileOutputStream(userName + ".txt");
            fileOut.write(updatedModuleString.getBytes());
            fileOut.close();
            File file = new File(userName + ".txt");
            this.semesterFile = file;
    }
    
    
     public static void main(String[] args) throws IOException, ParseException{
        File semesterFile = new File("asp14dbu.txt");
        boolean read = checkFile(semesterFile);
        System.out.println(read);
        
        if(read){
        Student student = new Student(semesterFile);
        System.out.println(student);
       File deadlineFile = new File("asp14dbuHUB.txt");      
       student.updateDeadlinesFromFile(deadlineFile);
        }
    }
}
