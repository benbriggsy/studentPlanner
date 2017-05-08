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
   
   public Deadline(Date newTime){
       this.dateTime = newTime;
   }
   
   Date getTime(){
       return dateTime;
   }
   
   void setDate(Date date){
       this.dateTime = date;
   }
    
}
