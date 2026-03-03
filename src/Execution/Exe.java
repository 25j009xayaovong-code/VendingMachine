package Execution;

import Operation.Authorizer;
import Operation.CheckBalance;
import Operation.DataManagement;
import java.io.BufferedReader;
import java.io.FileReader;
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
                money = Integer.parseInt(sc.nextLine());
                cb.insertMoreMoney(money);
            }
            if (this.fixId == 0) {
                System.out.printf("""
                        <<Enter the Number to Choose your drink>>
                        %s
                           """, getMenuName());
                System.out.print("Insert id: ");
                id = Integer.parseInt(sc.nextLine());
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
            System.out.println("Item Name: " + list[0] + " is out of stock");
            this.fixMoney = cb.getMoney();
            executeService();
        }
        System.out.println("Item Name: " + list[0] + ", Item price:" + list[1]);
        if (cb.checkMoney(Integer.parseInt(list[1].trim()))) {
            cb.deductMoney(Integer.parseInt(list[1].trim()));
            cb.deductBeverageQ(this.fixId);  // Deduct quantity only after successful purchase
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
            System.out.println("Money is not enough\nPlease insert more money");
            money = Integer.parseInt(sc.nextLine());
            cb.insertMoreMoney(money);
            this.fixMoney = cb.getMoney();
            executeService();

        }
    }

    public static String getMenuName() {
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
        int failTime = 1;
        
        // First, handle login before entering the menu loop
        while (lock && timeCount < 3) {
            if (logIn()) {
                lock = false;
                break;
            }
            timeCount++;
            if (timeCount == 3) {
                waitinTime(5 * failTime);
                failTime++;
                timeCount = 0;
            }
            if (lock) {
                System.out.println("invalid password or name");
                continue;
            }
        }
        
        // Only show admin menu if login was successful
        if (!lock) {
            while (true) {
                System.out.println("""
                        1. Regist a new Administrator
                        2. Manage Beverage Data
                        0. exit
                    """);
                String ans = sc.nextLine();
                switch (ans) {
                    case "1" -> registerAuthorizer();
                    case "2" -> {
                        while (true) {
                            System.out.println("""
                                    1. Add Beverage
                                    2. Add Beverage Quantity
                                    3. Change Beverage price
                                    0. return
                                """);

                            ans = sc.nextLine();
                            switch (ans) {
                                case "1" -> DataManagement.addBeverage();
                                case "2" -> DataManagement.addBeverageQ();
                                case "3" -> DataManagement.changeBeverageP();
                                case "0" -> {
                                    return;
                                }
                                default -> System.out.println("1, 2 and 3 only");
                            }
                        }
                    }
                    case "0" -> {
                        return;
                    }
                    default -> System.out.println("1 or 2 only");
                }
            }
        }
    }

    public void execute() throws Exception {
        while (true) {
            System.out.println("""
                    ------Welcom to CX Beverage Drink------
                        1. Purchase Beverage
                        2. Administrator
                        0. Exit
                    """);
            String ans = sc.nextLine();
            switch (ans) {
                case "1" -> executeService();
                case "2" -> executeAdmistration();
                case "0" -> {
                    break;
                }
                default -> {
                    System.out.println("1 or 2 only");

                }
            }
            if (ans.equalsIgnoreCase("0"))
                break;
        }
    }
}

