import Execution.Exe;
import Operation.Authorizer;
import Operation.DataManagement;
public class VendingMachine {
    public static void main(String[] args) throws Exception {
        // CheckBalance cb = new CheckBalance(12);
        // String[] list = cb.getMenu(2);
        // System.out.println("Item Name: " + list[0] + ", Item price" + list[1]);
        Exe ex = new Exe();
        // ex.execute();
        Authorizer az = new Authorizer();
        // az.logIn();
        DataManagement dm = new DataManagement();
        dm.changeBeverageP();
    }
}