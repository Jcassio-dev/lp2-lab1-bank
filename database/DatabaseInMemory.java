package database;

import models.BankAccount;
import models.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DatabaseInMemory {
    private List<Client> clients;
    private List<BankAccount> bankAccounts;
    private Client clientLogged = null;

    public DatabaseInMemory() {
        this.clients = new ArrayList<>();
        this.bankAccounts = new ArrayList<>();
    }

    // User methods
    public void addClient(Client client) {
        clients.add(client);
        System.out.println("Usuário adicionado: " + client.getName());
    }

    public Optional<Client> findUserByCpf(String cpf) {
        return clients.stream()
                .filter(client -> client.getCpf().equals(cpf))
                .findFirst();
    }

    public List<Client> listClients() {
        return new ArrayList<>(clients);
    }

    public void addAccount(BankAccount account) {
        bankAccounts.add(account);
        System.out.println("Conta bancária adicionada: " + account.getAccountNumber());
    }

    public Optional<BankAccount> findBankAccountByNumber(int bankAccountNumber) {
        return bankAccounts.stream()
                .filter(account -> account.getAccountNumber() == bankAccountNumber)
                .findFirst();
    }

    public Optional<BankAccount> listBankAccountByCpf(String cpf) {
        return bankAccounts.stream()
                .filter(account -> account.getCpfClient().equals(cpf))
                .findFirst();
    }

    public List<BankAccount> listBankAccount() {
        return new ArrayList<>(bankAccounts);
    }

    public Client getClientLogged() {
        return clientLogged;
    }

    public void setClientLogged(Client clientLogged) {
        this.clientLogged = clientLogged;
    }
}
