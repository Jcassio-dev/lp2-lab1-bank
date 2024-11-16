package controllers;

import database.DatabaseInMemory;
import models.BankAccount;
import models.Client;

import java.util.Scanner;

public class BankAccountController {
    DatabaseInMemory database;
    Scanner scanner = new Scanner(System.in);

    public BankAccountController(DatabaseInMemory database) {
        this.database = database;
    }

    public void addAccount() {
        System.out.print("Tipo da conta (corrente/poupanca): : ");
        String accountType = scanner.next();
        Client client = database.getClientLogged();

        if (client == null) {
            System.out.println("Usuário não autenticado");
            return;
        }

        BankAccount account = BankAccount.createAccount(accountType, client.getCpf());
        database.addAccount(account);
    }

    public void checkBalance() {
        System.out.print("Número da conta: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = database.findBankAccountByNumber(accountNumber).orElse(null);

        if (account == null) {
            System.out.println("Conta não encontrada");
        }

        System.out.println("Saldo: " + account.getBalance());
    }

    public void deposit() {
        System.out.print("Número da conta: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = database.findBankAccountByNumber(accountNumber).orElse(null);

        if (account == null) {
            System.out.println("Conta não encontrada");
            return;
        }

        System.out.print("Valor para depositar: ");
        double depositValue = scanner.nextDouble();
        account.deposit(depositValue);
    }

    public void withdraw() {
        System.out.print("Número da conta: ");
        int accountNumber = scanner.nextInt();
        BankAccount account = database.findBankAccountByNumber(accountNumber).orElse(null);

        if (account == null) {
            System.out.println("Conta não encontrada");
            return;
        }

        System.out.print("Valor para sacar: ");
        double withdrawValue = scanner.nextDouble();

        if (account.getBalance() < withdrawValue) {
            System.out.println("Saldo insuficiente");
            return;
        }

        account.withdraw(withdrawValue);
    }

    public void transfer() {
        System.out.print("Número da conta origem: ");
        int accountNumberOrigin = scanner.nextInt();
        BankAccount accountOrigin = database.findBankAccountByNumber(accountNumberOrigin).orElse(null);

        if (accountOrigin == null) {
            System.out.println("Conta origem não encontrada");
            return;
        }

        System.out.print("Número da conta destino: ");
        int accountNumberDestiny = scanner.nextInt();
        BankAccount accountDestiny = database.findBankAccountByNumber(accountNumberDestiny).orElse(null);

        if (accountDestiny == null) {
            System.out.println("Conta destino não encontrada");
            return;
        }

        System.out.print("Valor para transferir: ");
        double transferValue = scanner.nextDouble();

        if (accountOrigin.getBalance() < transferValue) {
            System.out.println("Saldo insuficiente");
            return;
        }

        accountOrigin.withdraw(transferValue);
        accountDestiny.deposit(transferValue);
    }

    public void listAccounts() {
        if (database.getClientLogged() == null) {
            System.out.println("Usuário não autenticado");
            return;
        }
        database.listBankAccount().forEach(account -> {
            System.out.println("Número da conta: " + account.getAccountNumber());
            System.out.println("Saldo: " + account.getBalance());
            System.out.println("Tipo da conta: " + account.getAccountType());
            System.out.println("CPF do cliente: " + account.getCpfClient());
        });
    }
}
