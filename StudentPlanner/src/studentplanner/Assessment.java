/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.*;

/**
 *
 * @author natha
 */
public abstract class Assessment {
    
    //Added these so I could properly build the assignment constructor
    
    String assessmentCode;
    String assessmentTitle;
    Double weighting;
    Double grade;
    Deadline deadline;
    ArrayList<Task> taskList;
    String notes;
}
