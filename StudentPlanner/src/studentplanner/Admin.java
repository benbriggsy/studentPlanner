package studentplanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    
    public static void main(String[] args) throws IOException, ParseException{
        File semesterFile = new File("semester.txt");
        Admin HUB = new Admin("HUB", "hub@uea.ac.uk");   
    }
}
