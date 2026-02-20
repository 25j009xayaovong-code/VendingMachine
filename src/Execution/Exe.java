package Execution;

import Operation.CheckBalance;
import java.util.*;

public class Exe {
    public static Scanner sc = new Scanner(System.in);

    public Exe() {

    }

    public void execute() {
        int id = 0;
        int money = 0;
        CheckBalance cb = new CheckBalance(money);

        try {
            if (cb.getMoney() > 0) {
                System.out.println("Insert Money: ");
                money = sc.nextInt();
            }

            System.out.println("Insert id: ");
            id = sc.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
        }

        String[] list = cb.getMenu(id);
        System.out.println("Item Name: " + list[0] + ", Item price:" + list[1]);
        if (cb.checkMoney(id)) {
            cb.deductMoney(Integer.parseInt(list[1].trim()));
            System.out.println("Purchase Completed");
            System.err.println("currend Balance: " + cb.getMoney());

        } else {
            System.out.println("Money is not enough\nPLease insert more money");
            money = sc.nextInt();
            cb.insertMoreMoney(money);
            execute();

        }
    }

}
