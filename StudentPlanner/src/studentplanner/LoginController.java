/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentplanner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author natha
 */
public class LoginController {
    
    public static boolean checkCredentials(String username, String password){
        String file = "users.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(";");
                String u = lineSplit[0];
                String p = lineSplit[1];
                if (username.equals(u)){
                    if (password.equals(p)){
                        return true;
                    }
                }
            }
            return false;
        }
        catch(IOException e){
            return false;
        }
    }  
}
