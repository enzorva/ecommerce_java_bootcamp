package repository;

import model.Client;

public class ClientValidator {

    public boolean validate(Client client) {
        if (client.getDocumentIdentification() == null || client.getDocumentIdentification().isBlank()) {
            return false;
        }
        return true;
    }
}