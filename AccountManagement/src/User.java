import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String phoneNumber;
    private String address;
    private float interestRate;
    private String transactionDetails;
    private String deductionDetails;
    private int transactionCount;
    private LoginDetails loginDetails; // Association
    private BankAccount account; // Association

    //    parameterized constructor
    public User(int accountNumber, String name, String PhoneNo, String address, int password, char accType) {
        this.name = name;
        this.phoneNumber = PhoneNo;
        this.address = address;
        this.transactionDetails = "";
        this.deductionDetails = "";
        this.transactionCount = 0;
//        initially we are assuming interest rate as 5%
        this.interestRate = 5;
//        initializing account
//        implementing polymorphism
        if (accType == 's'){
            this.account = new SavingAccount();
            this.account.setAccType(accType);
        }else if (accType == 'c'){
            this.account = new CheckingAccount();
            this.account.setAccType(accType);
        }
//        initializing login details
        this.loginDetails = new LoginDetails(accountNumber, password);

    }

    //    setters
    public void setInterestRate(float interest) { interestRate = interest; }
    public void addTransactionDetail(String details) { this.transactionDetails += details; }
    public void addDeductionDetail(String details) { this.deductionDetails += details; }
    public void addTransactionCount() { this.transactionCount += 1; }

//    getters
    public String getName() {
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getAddress() {
        return address;
    }
    public BankAccount getAccount() {
        return account;
    }
    public float getInterestRate(){ return interestRate;}
    public String getTransactionDetails() { return transactionDetails; }
    public String getDeductionDetails() { return deductionDetails; }
    public int getTransactionCount() { return transactionCount; }
    public LoginDetails getLoginDetails(){ return loginDetails;}
}