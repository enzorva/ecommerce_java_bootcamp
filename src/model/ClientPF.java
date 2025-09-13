package model;

public class ClientPF extends Client {
    private String cpf;

    public ClientPF(String name, String email, String phone, String cpf) {
        super(name, email, phone);
        this.cpf = cpf;
    }

    @Override
    public String getDocumentIdentification() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
