/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.*;

/**
 *
 * @author Michael Meyne
 */
public class Deadline {
    
   
   private Date dateTime;
   private Assignment assignment;
   private boolean isAssessment;
   
   public Deadline(Date newTime, Assignment newAssignment){
       this.dateTime = newTime;
       this.assignment = newAssignment;
   }
   
   Date getTime(){
       return dateTime;
   }
   
   Assignment getAssignment(){
       return assignment;
   }
   
   boolean getIsAssessment(){
       return isAssessment;
   }
   
   void setDate(Date date){
       this.dateTime = date;
   }
    
}
