import controllers.Auth;

import controllers.BankAccountController;
import database.DatabaseInMemory;
import controllers.ClientController;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DatabaseInMemory database = new DatabaseInMemory();
        ClientController clientController = new ClientController(database);
        BankAccountController bankAccountController = new BankAccountController(database);
        Auth auth = new Auth(database);
        boolean running = true;

        while (running) {
            System.out.println("1. Cadastro de usuário");
            System.out.println("2. Login");
            System.out.println("3. Logout");
            System.out.println("4. Criar conta");
            System.out.println("5. Consultar saldo");
            System.out.println("6. Depositar");
            System.out.println("7. Sacar");
            System.out.println("8. Transferir");
            System.out.println("9. Listar contas");
            System.out.println("10. Sair");
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1: { clientController.createClient(); break; }
                case 2: { auth.login(); break; }
                case 3: { auth.logout(); break; }
                case 4: { bankAccountController.addAccount(); break; }
                case 5: { bankAccountController.checkBalance(); break; }
                case 6: { bankAccountController.deposit(); break; }
                case 7: { bankAccountController.withdraw(); break; }
                case 8: { bankAccountController.transfer(); break; }
                case 9: { bankAccountController.listAccounts(); break; }
                case 10: { running = false; break; }
                default: { System.out.println("Opção inválida."); }
            }
        }
        scanner.close();
    }
}
