package repository;

import model.Client;

public class ClientValidator {

    public boolean validate(Client client) {
        if (client.getDocumentoIdentificacao() == null || client.getDocumentoIdentificacao().isBlank()) {
            return false;
        }
        return true;
    }
}