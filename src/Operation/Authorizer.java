package Operation;

import java.io.*;
import java.util.*;

public class Authorizer implements PasswordPrinciple {
    private static String name;
    private static String password;
    public static Scanner sc = new Scanner(System.in);

    public Authorizer() {
    }

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
        if (rs && password.length() >= passwordLength) {
            return true;
        }
        return false;
    }

    public void registerAuthorizer() throws Exception {
        if (this.name == null) {
            System.out.print("Enter your full name: ");
            String name = sc.nextLine();
            this.name = name;
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        if (!checkPasswordValidation(password)) {
            System.out.println("Invalid Password");
            this.registerAuthorizer();
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

    public boolean logIn() {

        int timeLogin = 3;
        while (timeLogin-- > 0) {

            try {
                System.out.print("Enter Your Full Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Your Password: ");
                String password = sc.nextLine();
                try (BufferedReader br = new BufferedReader(new FileReader("src/Operation/CSV file/Authorizer.csv"))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] lineSplit = line.split(",");
                        if (name.equalsIgnoreCase(lineSplit[1].trim()) && password.equals(lineSplit[2].trim())) {
                            return true;
                        }
                    }

                } catch (IOException e) {
                }

            } catch (InputMismatchException e) {

            }
        }
        waitinTime(5);
        return false;
    }

    public void waitinTime(int s) {

        while (s > 0) {
            try {
                System.out.print(s-- + " Seconds");
                Thread.sleep(s * 1000);

            } catch (Exception e) {
            }
            System.out.print("\r");

        }

    }
}
