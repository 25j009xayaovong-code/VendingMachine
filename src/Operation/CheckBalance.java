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

    public void setMoney(int money) {
        this.money = money;
    }

    public void insertMoreMoney(int money) {
        this.money += money;
    }

    public void deductMoney(int money) {
        this.money -= money;
    }

    public boolean checkMoney(int itemCost) {
        if (this.money >= itemCost)
            return true;
        return false;
    }

    public String[] getMenu(int itemId) {
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

    public boolean checkBeverageQ(int itemId) {
        String newData = "";
        try (BufferedReader br = new BufferedReader(new FileReader("src/Operation/BeverageQuantity.csv"));) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] spliteField = line.split(",");

                if (Integer.parseInt(spliteField[0]) == itemId) {
                    if (spliteField[1].equals("0")) {
                        return true;
                    }
                    int newQ = Integer.parseInt(spliteField[1]) - 1;
                    spliteField[1] = Integer.toString(newQ);
                }
                newData += String.format("%s,%s\n", spliteField[0], spliteField[1]);
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Operation/BeverageQuantity.csv"));
            bw.write(newData);
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
