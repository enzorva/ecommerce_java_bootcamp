package service;

import java.util.List;

import model.Client;
import repository.ClientRepository;
import repository.ClientValidator;

public class ClientService {
    private final ClientRepository repository;
    private final ClientValidator validator;

    public ClientService(ClientRepository repository, ClientValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Client register(Client client) {
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido! Verifique os dados obrigatórios.");
        }
        return repository.save(client);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

    public Client update(Client client) {
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido! Verifique os dados obrigatórios.");
        }
        return repository.update(client);
    }
}