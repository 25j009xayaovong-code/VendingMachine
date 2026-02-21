package Operation;

public interface  PasswordPrinciple {
    int passwordLength = 8;
    String passwordUpper = "QWERTYUIOPASDFGHJKLZXCVBNM";  
    String passwordLower = "qwertyuiopasdfghjklzxcvbnm";
    String specialAlpha = "!@#$%^&*()_-+=<,>./?";
    String numbers = "1234567890";
    public boolean checkPasswordValidation(String password);
    
}
