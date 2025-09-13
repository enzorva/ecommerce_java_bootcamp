package model;

public class ClientPJ extends Client {
    private String cnpj;

    public ClientPJ(String name, String email, String phone, String cnpj) {
        super(name, email, phone);
        this.cnpj = cnpj;
    }

    @Override
    public String getDocumentIdentification() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
