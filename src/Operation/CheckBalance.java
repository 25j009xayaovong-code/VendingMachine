package Operation;

import java.io.*;
public class CheckBalance {
    private static int money;

    public CheckBalance(int money) {
        this.money = money;
    }

    public int getMoney() {
        return this.money;
    }

    public void insertMoreMoney(int money) {
        this.money += money;
    }
    public void deductMoney(int money){
        this.money -= money;
    }

    public boolean checkMoney(int itemCost) {
        if (this.money > itemCost)
            return true;
        return false;
    }

    public static String[] getMenu(int itemId) {
        String[] returnValue = new String[2];
        try (
                BufferedReader reader = new BufferedReader(new FileReader("src/Operation/Beverage.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] spliteField = line.split(",");
                if (Integer.parseInt(spliteField[0]) == itemId) {
                    returnValue[0] = spliteField[1];
                    returnValue[1] = spliteField[2];

                    return returnValue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return returnValue;
    }
}
