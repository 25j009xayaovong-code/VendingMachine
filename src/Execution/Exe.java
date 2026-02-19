package Execution;

import Operation.CheckBalance;
import java.util.*;

public class Exe {
    public static Scanner sc = new Scanner(System.in);

    public Exe() {

    }

    public void execute() {
        System.out.println("Insert Money: ");
        int money = sc.nextInt();
        System.out.println("Insert id: ");
        int id = sc.nextInt();
        CheckBalance cb = new CheckBalance(money);
        String[] list = cb.getMenu(2);
        System.out.println("Item Name: " + list[0] + ", Item price" + list[1]);
        if (cb.checkMoney(id)) {
            System.out.println("You can buy it");
        } else {
            System.out.println("Money is not enough");

        }
    }
}
