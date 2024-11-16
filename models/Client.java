package models;

public class Client {
    private String name;
    private String cpf;
    private String password;
    private boolean isLogged = false;

    private Client(String name, String cpf, String password) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }

    public static Client createClient(String name, String cpf, String password) {
        return new Client(name, cpf, password);
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }
}
