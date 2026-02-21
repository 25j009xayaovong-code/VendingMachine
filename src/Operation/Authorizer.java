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
        boolean rs1 = false;
        boolean rs2 = false;
        boolean rs3 = false;
        boolean rs4 = false;

        for (char c : password.toCharArray()) {
            if (passwordUpper.contains(String.valueOf(c))) {
                rs1 = true;
            }
            if (passwordLower.contains(String.valueOf(c))) {
                rs2 = true;

            }
            if (specialAlpha.contains(String.valueOf(c))) {
                rs3 = true;

            }
            if (numbers.contains(String.valueOf(c))) {
                rs4 = true;

            }
        }
        if (rs1 && rs2 && rs3 & rs4 && password.length() >= passwordLength) {
            return true;
        }
        return false;
    }

    public void registerAuthorizer() throws Exception {

        System.out.print("Enter your full name: ");
        String name = sc.nextLine();
        this.name = name;
        while (true) {

            System.out.print("Enter Password: ");
            String password = sc.nextLine();
            if (!checkPasswordValidation(password)) {
                System.out.println("Invalid Password");
                continue;
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
            break;
        }
    }

    public boolean logIn() {

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
