/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.util.ArrayList;

/**
 *
 * @author phillipperks
 */
public class AdminController {
    private ModuleController module;
    
    public String getName(){
        return module.getModule().getModuleOrganiser().getName();
    }
    
    public String getEmail(){
        return module.getModule().getModuleOrganiser().getEmail();
    }
    
    public ArrayList<Module> getModules(){
        return module.getModule().getModuleOrganiser().getModules();
    }
    
    public void setDeadline(Assessment assessment, Deadline deadline){
        assessment.setDeadline(deadline);
    }
}
