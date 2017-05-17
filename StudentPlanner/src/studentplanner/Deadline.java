package studentplanner;

import java.util.*;

/**
 *
 * @author Michael Meyne
 */
public class Deadline {
    
   
   private Date dateTime;
   
   public Deadline(Date newTime){
       this.dateTime = newTime;
   }
   
   Date getTime(){
       return dateTime;
   }
   
   void setDate(Date date){
       this.dateTime = date;
   }
   
   @Override
    public String toString(){
        return dateTime.toString();
    }   
}
