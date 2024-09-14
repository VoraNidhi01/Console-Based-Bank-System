import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BankingSystem {
    private Map<Long, Account> accountMap = new HashMap<>();
    private long nextAccountNumber = 10000100;
    private AccountManager accountManager;
    private User user;

    public BankingSystem() {
        accountManager = new AccountManager(accountMap);
        user = new User();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem();

        while (true) {
            System.out.println("*** WELCOME TO BANKING SYSTEM ***");
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice1 = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice1) {
                case 1:
                    bankingSystem.register(scanner);
                    break;
                case 2:
                    String fullName = bankingSystem.login(scanner);
                    if (fullName != null) {
                        long accountNumber = bankingSystem.getAccountNumber(fullName);
                        if (accountNumber == -1) {
                            System.out.println("Account found. Opening a new account.");
                            accountNumber = bankingSystem.openAccount(fullName, scanner);
                            System.out.println("Account Number: " + accountNumber);
                        }
                        bankingSystem.accountOperations(accountNumber, scanner);
                    } else {
                        System.out.println("Incorrect Email or Password!");
                    }
                    break;
                case 3:
                    System.out.println("THANK YOU FOR USING BANKING SYSTEM!!!");
                    //System.out.println("Exiting System!");
                    return;
                default:
                    System.out.println("Enter Valid Choice");
                    break;
            }
        }
    }

    private void register(Scanner scanner) {
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        user.register(email, password, fullName);

        // Check if user is successfully registered and create an account if needed
        long accountNumber = getAccountNumber(fullName);
        if (accountNumber == -1) {
            System.out.println("Registration Successful! Opening a new account.");
            accountNumber = openAccount(fullName, scanner);
            System.out.println("Account Number: " + accountNumber);
        } else {
            System.out.println("User already has an account. Account Number: " + accountNumber);
        }
    }

    private String login(Scanner scanner) {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        return user.login(email, password);
    }

    private long getAccountNumber(String fullName) {
        for (Account account : accountMap.values()) {
            if (account.getFullName().equals(fullName)) {
                return account.getAccountNumber();
            }
        }
        return -1;
    }

    private long openAccount(String fullName, Scanner scanner) {
        System.out.print("Enter Initial Amount: ");
        double initialAmount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter Security Pin: ");
        String securityPin = scanner.nextLine();

        long accountNumber = nextAccountNumber++;
        Account newAccount = new SavingsAccount(accountNumber, fullName, initialAmount, securityPin);
        accountMap.put(accountNumber, newAccount);
        return accountNumber;
    }

    private void accountOperations(long accountNumber, Scanner scanner) {
        while (true) {
            System.out.println();
            System.out.println("1. Debit Money");
            System.out.println("2. Credit Money");
            System.out.println("3. Transfer Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.print("Enter Amount: ");
                    double debitAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    accountManager.debitMoney(accountNumber, debitAmount);
                    break;
                case 2:
                    System.out.print("Enter Amount: ");
                    double creditAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    accountManager.creditMoney(accountNumber, creditAmount);
                    break;
                case 3:
                    System.out.print("Enter Receiver Account Number: ");
                    long receiverAccountNumber = scanner.nextLong();
                    System.out.print("Enter Amount: ");
                    double transferAmount = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline
                    accountManager.transferMoney(accountNumber, receiverAccountNumber, transferAmount);
                    break;
                case 4:
                    accountManager.checkBalance(accountNumber);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Enter Valid Choice!");
                    break;
            }
        }
    }
}
