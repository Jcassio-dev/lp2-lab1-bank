package controllers;
import database.DatabaseInMemory;
import models.Client;

import java.util.Optional;
import java.util.Scanner;

public class Auth {

    private DatabaseInMemory database;
    Scanner scanner = new Scanner(System.in);

    public Auth(DatabaseInMemory database) {
        this.database = database;
    }

    public void login() {
        System.out.print("CPF: ");
        String cpf = scanner.next();
        System.out.print("Senha: ");
        String senha = scanner.next();

        Optional<Client> client = database.findUserByCpf(cpf);
        if (client == null) {
            System.out.println("Usuário não encontrado");
            return;
        }

        client.ifPresent(c -> {
            if (c.validatePassword(senha)) {
                database.setClientLogged(c);
                System.out.println("Usuário autenticado: " + c.getName());
            } else {
                System.out.println("Senha inválida");
            }
        });
    }

    public void logout() {
        database.setClientLogged(null);
        System.out.println("Usuário deslogado");
    }
}
