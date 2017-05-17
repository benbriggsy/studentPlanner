package studentplanner;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author natha
 */
public class Exam extends Assessment {
    private String examRoom;
    private int examDuration;
    
    public Exam(String examRoom, int examDuration, String assessmentCode, String assessmentTitle, double weighting,
        double grade, Deadline deadline, ArrayList<Task> taskList, 
        String notes, String ex){
        super(assessmentCode, assessmentTitle, weighting, grade, deadline, 
                taskList, notes, ex);
        this.examRoom = examRoom;
        this.examDuration = examDuration;
    }
    
    public String getExamRoom(){
        return examRoom;
    }
    
    public int getExamDuration(){
        return examDuration;
    }
    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder(super.toString());
        str.append("Room:\t\t").append(examRoom).append("\n");
        str.append("Duration:\t").append(examDuration).append(" minutes\n");
        return str.toString();
    }
    
    public String examToFile(){
        Format formatter = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");
        String exam="";
        exam += super.getAssessmentCode() + "/";
        exam += super.getAssessmentTitle()+ "/";
        exam += super.getWeighting()+ "/";
        exam += super.getGrade() + "/";
        exam += formatter.format(super.getDeadline().getTime()) + "/";
        exam += examDuration + "/";
        exam += examRoom + "/";
        for(Task task: super.getTasks()){
            exam += task.taskToFile() + "#";
        }
        exam = exam.substring(0,exam.length()-1);
        exam+="/";
        exam += super.getNotes();
        return exam;
    }
}
