package service;

import model.Client;
import repository.ClientRepository;
import repository.ClientValidator;

public class CreateClientService {
    private final ClientRepository repository;
    private final ClientValidator validator;

    public CreateClientService(ClientRepository repository, ClientValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Client register(Client client) {
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido! Verifique os dados obrigatórios.");
        }
        return repository.save(client);
    }    
}
