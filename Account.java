public abstract class Account {
    protected long accountNumber;
    protected String fullName;
    protected double balance;
    protected String securityPin;

    public Account(long accountNumber, String fullName, double balance, String securityPin) {
        this.accountNumber = accountNumber;
        this.fullName = fullName;
        this.balance = balance;
        this.securityPin = securityPin;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getFullName() {
        return fullName;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);

    public boolean validatePin(String pin) {
        return this.securityPin.equals(pin);
    }
}
