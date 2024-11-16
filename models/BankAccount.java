package models;

import java.util.Optional;

public class BankAccount {
    private static final int INITIAL_ACCOUNT_NUMBER = 01;
    private static int counter = INITIAL_ACCOUNT_NUMBER;
    private int accountNumber;
    private double balance;
    private String accountType;
    private String cpfClient;

    private BankAccount(String accountType, Optional<Double> initialBalance, String cpfClient) {
        this.accountNumber = counter++;
        this.accountType = accountType;
        this.balance = initialBalance.orElse(0.0);
        this.cpfClient = cpfClient;
    }

    public static BankAccount createAccount(String accountType, String cpfClient) {
        return new BankAccount(accountType, Optional.empty(), cpfClient);
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        } else {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount destinationAccount, double amount) {
        if (this.withdraw(amount)) {
            destinationAccount.deposit(amount);
            return true;
        }
        return false;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getCpfClient() {
        return cpfClient;
    }
}
