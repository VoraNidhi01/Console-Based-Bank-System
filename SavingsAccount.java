public class SavingsAccount extends Account {

    public SavingsAccount(long accountNumber, String fullName, double balance, String securityPin) {
        super(accountNumber, fullName, balance, securityPin);
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient balance or invalid amount");
        }
    }
}
