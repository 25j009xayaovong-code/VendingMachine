package Execution;

import Operation.CheckBalance;
import Operation.Authorizer;
import Operation.DataManagement;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Exe extends Authorizer {
    public static Scanner sc = new Scanner(System.in);
    static int fixMoney = 0;
    static int fixId = 0;

    public void executeService() {
        int id = this.fixId;
        int money = this.fixMoney;
        if (id == 0 && money == 0)
            System.out.println("------Welcom to CX Beverage Drink Machine------");
        CheckBalance cb = new CheckBalance(money);
        String[] list = {};
        try {
            if (cb.getMoney() <= 0) {
                System.out.print("Insert Money: ");
                money = sc.nextInt();
                cb.insertMoreMoney(money);
            }
            if (this.fixId == 0) {
                System.out.printf("""
                        <<Enter the Number to Choose your drink>>
                        %s
                           """, getMenuName());
                System.out.print("Insert id: ");
                id = sc.nextInt();
                this.fixId = id;
            }
            list = cb.getMenu(this.fixId);

        } catch (Exception e) {
            System.out.println("Invalid input");
            sc.nextLine();
            executeService();
        }

        if (cb.checkBeverageQ(this.fixId)) {
            this.fixId = 0;
            System.out.println("Item Name: " + list[0] + "is out of stock");
            this.fixMoney = cb.getMoney();
            executeService();
        }
        System.out.println("Item Name: " + list[0] + ", Item price:" + list[1]);
        if (cb.checkMoney(Integer.parseInt(list[1].trim()))) {
            cb.deductMoney(Integer.parseInt(list[1].trim()));
            this.fixId = 0;
            System.out.println("Purchase Completed");
            System.err.println("current Balance: " + cb.getMoney());
            System.out.println("\nWant to buy more? Y or yes / N or No");
            sc.nextLine(); // consume leftover newline
            String ans = sc.nextLine();
            if (ans.toLowerCase().contains("y") || ans.toLowerCase().contains("yes")) {
                this.fixMoney = cb.getMoney();
                executeService();
            }
            System.out.println("Thank You for always using CX vending Machine serice, your change:  " + cb.getMoney());

        } else {
            System.out.println("Money is not enough\nPLease insert more money");
            money = sc.nextInt();
            cb.insertMoreMoney(money);
            this.fixMoney = cb.getMoney();
            executeService();

        }
    }

    public String getMenuName() {
        String text = "";
        try (
                BufferedReader br = new BufferedReader(new FileReader("src/Operation/CSV file/Beverage.csv"));) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(",");
                text += String.format("\t%s. %s\n", lineSplit[0].trim(), lineSplit[1].trim());
            }
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void executeAdmistration() throws Exception {
        boolean lock = true;
        System.out.println("------Welcom to CX Beverage Drink System------");
        int timeCount = 0;
        while (timeCount++ < 3) {
            if (lock) {
                if (logIn()) {
                    lock = false;
                    timeCount = 0;
                }
                continue;
            }

            System.out.println("""
                        1. Regist a new Administrator
                        2. Manage Beverage Data
                    """);
            int ans = sc.nextInt();
            switch (ans) {
                case 1 -> registerAuthorizer();
                case 2 -> {
                    System.out.println("""
                                1. Add Beverage
                                2. Add Beverage Quantity
                                3. Change Baverage price
                                0. return
                            """);
                    while (true) {
                        ans = sc.nextInt();
                        switch (ans) {
                            case 1 -> DataManagement.addBeverage();
                            case 2 -> DataManagement.addBeverageQ();
                            case 3 -> DataManagement.changeBeverageP();
                            case 0 -> {
                                break;
                            }

                            default -> System.out.println("1 , 2 and 3 only");
                        }
                        if (ans == 0) {
                            break;
                        }
                    }
                }
                default -> System.out.println("1 or 2 only");

            }

        }
    }
}