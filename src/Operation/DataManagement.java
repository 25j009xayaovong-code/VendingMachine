package Operation;

import java.io.*;
import java.util.*;

public class DataManagement {
    public static Scanner sc = new Scanner(System.in);

    public DataManagement() {

    }

    public static void addBeverage() {
        String addText = "";
        int id = 0;
        System.out.print("Enter new Beverage Name: ");
        String beverageName = sc.nextLine();
        System.out.print("Enter new Beverage Price: ");
        String price = sc.nextLine();
        System.out.print("Enter new Beverage Amount to add: ");
        String amount = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Operation/CSV file/Beverage.csv"));
                BufferedReader br2 = new BufferedReader(
                        new FileReader("src/Operation/CSV file/BeverageQuantity.csv"));) {
            String line;
            while ((line = br.readLine()) != null) {
                id++;
                String[] spliteField = line.split(",");
                addText += String.format("%s,%s,%s\n", spliteField[0], spliteField[1], spliteField[2]);
            }
            addText += String.format("%d,%s,%s\n", ++id, beverageName, price);
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Operation/CSV file/Beverage.csv"));
            bw.write(addText);
            bw.close();
            addText = "";
            id = 0;
            while ((line = br2.readLine()) != null) {
                id++;
                String[] spliteField = line.split(",");
                addText += String.format("%s,%s\n", spliteField[0], spliteField[1]);
            }
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/Operation/CSV file/BeverageQuantity.csv"));
            addText += String.format("%d,%s\n", ++id, amount);
            bw2.write(addText);
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addBeverageQ() {
        String addText = "";
        System.out.print("Enter Beverage Id: ");
        String id = sc.nextLine();
        System.out.print("Enter Beverage Amount to add: ");
        int amount = sc.nextInt();

        try (
                BufferedReader br2 = new BufferedReader(
                        new FileReader("src/Operation/CSV file/BeverageQuantity.csv"));) {
            String line;
            while ((line = br2.readLine()) != null) {

                String[] spliteField = line.split(",");
                if (spliteField[0].trim().equals(id)) {
                    addText += String.format("%s,%d\n", spliteField[0], Integer.parseInt(spliteField[1]) + amount);
                    continue;
                }
                addText += String.format("%s,%s\n", spliteField[0], spliteField[1]);
            }
            BufferedWriter bw2 = new BufferedWriter(new FileWriter("src/Operation/CSV file/BeverageQuantity.csv"));
            bw2.write(addText);
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void changeBeverageP() {
        String addText = "";
        System.out.print("Enter new Beverage id: ");
        String id = sc.nextLine();
        System.out.print("Enter new Beverage Price you want to change to: ");
        String price = sc.nextLine();

        try (BufferedReader br = new BufferedReader(new FileReader("src/Operation/CSV file/Beverage.csv"));) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] spliteField = line.split(",");
                if (spliteField[0].trim().equals(id)) {
                    addText += String.format("%s,%s,%s\n", spliteField[0], spliteField[1], price);
                    continue;
                }

                addText += String.format("%s,%s,%s\n", spliteField[0], spliteField[1], spliteField[2]);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Operation/CSV file/Beverage.csv"));
            bw.write(addText);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}