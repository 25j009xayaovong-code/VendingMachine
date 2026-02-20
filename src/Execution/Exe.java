package Execution;

import Operation.CheckBalance;
import java.util.*;

public class Exe {
    public static Scanner sc = new Scanner(System.in);
    static int fixMoney = 0;
    static int fixId = 0;

    public void execute() {
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
                System.out.print("""
                        <<Enter the Number to Choose your drink>>
                            1. beer
                            2. coak
                            3. pepsi
                            4. alcohol
                                            """);
                System.out.print("Insert id: ");
                id = sc.nextInt();
                this.fixId = id;
            }
            list = cb.getMenu(this.fixId);

        } catch (Exception e) {
            System.out.println("Invalid input");
            sc.nextLine();
            execute();
        }

        if (cb.checkBeverageQ(this.fixId)) {
            this.fixId = 0;
            System.out.println("Item Name: " + list[0] + "is out of stock");
            this.fixMoney = cb.getMoney();
            execute();
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
                execute();
            }
            System.out.println("Thank You for always using CX vending Machine serice, your change:  " + cb.getMoney());

        } else {
            System.out.println("Money is not enough\nPLease insert more money");
            money = sc.nextInt();
            cb.insertMoreMoney(money);
            this.fixMoney = cb.getMoney();
            execute();

        }
    }
}