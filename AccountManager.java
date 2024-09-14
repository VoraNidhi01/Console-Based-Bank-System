import java.util.Map;

public class AccountManager {
    private Map<Long, Account> accounts;

    public AccountManager(Map<Long, Account> accounts) {
        this.accounts = accounts;
    }

    public void debitMoney(long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            try {
                account.withdraw(amount);
                System.out.println("Rs." + amount + " debited successfully from account " + accountNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void creditMoney(long accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            try {
                account.deposit(amount);
                System.out.println("Rs." + amount + " credited successfully to account " + accountNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void transferMoney(long fromAccountNumber, long toAccountNumber, double amount) {
        Account fromAccount = accounts.get(fromAccountNumber);
        Account toAccount = accounts.get(toAccountNumber);

        if (fromAccount != null && toAccount != null) {
            try {
                fromAccount.withdraw(amount);
                toAccount.deposit(amount);
                System.out.println("Rs." + amount + " transferred successfully from account " + fromAccountNumber + " to account " + toAccountNumber);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    public void checkBalance(long accountNumber) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.println("Account Balance for account " + accountNumber + ": Rs." + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }
}
