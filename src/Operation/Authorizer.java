package Operation;

import java.io.*;
import java.util.*;

public class Authorizer implements PasswordPrinciple {
    private static String name;
    private static String password;
    public static Scanner sc = new Scanner(System.in);
    public Authorizer(){}
    public Authorizer(String name, String password) {
        this.name = name;
        this.password = password;
    }

    // setter
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // setter
    @Override
    public boolean checkPasswordValidation(String password) {
        boolean rs = false;
        for (char c : password.toCharArray()) {
            if (passwordUpper.contains(String.valueOf(c)) ||
                    passwordLower.contains(String.valueOf(c)) ||
                    specialAlpha.contains(String.valueOf(c)) ||
                    numbers.contains(String.valueOf(c))) {
                rs = true;
                continue;
            }
        rs = false;
        break;
        }
        // 
        if(rs && password.length() >= passwordLength ){
            return true;
        }
        return false;
    }

    public void registerAuthorizer() {
        if(this.name == null){
            System.out.print("Enter your full name: ");
             String name = sc.nextLine();
             this.name = name;
        }
       
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        if (!checkPasswordValidation(password)) {
            registerAuthorizer();
        }
        String backupText = "";
        int id = 0;
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/Operation/CSV file/Authorizer.csv"));) {
            String line;
            while ((line = br.readLine()) != null) {
                backupText += line + "\n";
                id++;
            }
            backupText += ++id + ", " + name + ", " + password;
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Operation/CSV file/Authorizer.csv"));
            bw.write(backupText);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
