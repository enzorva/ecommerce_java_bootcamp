package service;

import java.util.List;

import model.Client;
import repository.ClientRepository;
import validation.ClientValidator;

public class ClientService {
    private final ClientRepository repository;
    private final ClientValidator validator;

    public ClientService(ClientRepository repository, ClientValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public Client register(Client client) {
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido!");
        }
        return repository.save(client);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

    public Client update(Client client) {
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido!");
        }
        return repository.update(client);
    }

    public Client findById(String id) {
        Client client = repository.findById(id);
        if (client == null) {
            throw new RuntimeException("Cliente não encontrado!");
        }
        return client;
    }

    public Client findByDocument(String document) {
        Client client = repository.findByDocument(document);
        if (client == null) {
            throw new RuntimeException("Cliente não encontrado!");
        }   
        return client;
    }
}