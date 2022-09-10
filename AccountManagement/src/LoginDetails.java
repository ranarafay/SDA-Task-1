import java.io.Serializable;

public class LoginDetails implements Serializable {
    private int accountNumber;
    private int password;

//    parameterized constructor
    public LoginDetails(int accountNumber, int password) {
        this.accountNumber = accountNumber;
        this.password = password;
    }

//    getters
    public int getAccountNumber() {
        return accountNumber;
    }
    public int getPassword() {
        return password;
    }
}
