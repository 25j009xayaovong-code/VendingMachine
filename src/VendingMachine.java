import Execution.Exe;
import Operation.Authorizer;
import Operation.DataManagement;
public class VendingMachine {
    public static void main(String[] args) throws Exception {
       
        Exe ex = new Exe();
        ex.executeAdmistration();
        Authorizer az = new Authorizer();
        // az.registerAuthorizer();
        // System.out.println(az.checkPasswordValidation
    }
}