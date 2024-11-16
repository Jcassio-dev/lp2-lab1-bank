package controllers;
import models.Client;
import database.DatabaseInMemory;
import java.util.Scanner;

public class ClientController {
    private DatabaseInMemory database;
    Scanner scanner = new Scanner(System.in);

    public ClientController(DatabaseInMemory database) {
        this.database = database;
    }

    public void createClient() {
        System.out.print("Nome: ");
        String name = scanner.next();
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Senha: ");
        String password = scanner.next();
        Client client = Client.createClient(name, cpf, password);
        database.addClient(client);
    }


}
