/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;
import java.util.*;
/**
 *
 * @author phillipperks
 */
public class Admin {
    private ArrayList<Module> modules;
    
    public Admin(){
        this.modules = new ArrayList<>();
    }
    
    public ArrayList<Module> getModules(){
        return modules;
    }
}
