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
    private String name;
    private String email;
    
    public Admin(String name, String email){
        this.name = name;
        this.email = email;
        this.modules = new ArrayList<>();
    }
    
    public String getName(){
        return name;
    }
    
    public String getEmail(){
        return email;
    }
    
    public ArrayList<Module> getModules(){
        return modules;
    }
    
    public void setDeadline(Assessment assessment, Deadline deadline){
        assessment.setDeadline(deadline);
    }
    
    @Override
    public String toString(){
        StringBuilder admin = new StringBuilder();
        admin.append(name).append("\n").append(email).append("\n");
        return admin.toString();
}
