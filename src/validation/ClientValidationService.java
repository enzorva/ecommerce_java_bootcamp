package validation;

import model.Client;

public class ClientValidationService {

    private final ClientValidator validator;

    public ClientValidationService(ClientValidator validator) {
        this.validator = validator;
    }

    public boolean validate(Client client) {
        return validator.validate(client);
    }
}
    
