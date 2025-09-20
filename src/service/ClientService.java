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

    public Client create(Client client) {
        Client c = repository.findByDocument(client.getDocumentIdentification());
        if (c != null) {
            throw new IllegalArgumentException("Cliente com este documento já existe!");
        }
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido!");
        }
        return repository.save(client);
    }

    public List<Client> listAll() {
        return repository.findAll();
    }

    public Client update(Client client) {
        Client c = repository.findByDocument(client.getDocumentIdentification());
        if (c == null) {
            throw new RuntimeException("Cliente não encontrado!");
        }
        if (!validator.validate(client)) {
            throw new IllegalArgumentException("Cliente inválido!");
        }
        return repository.update(client);
    }

    public Client findById(String id) {
        Client c = repository.findById(id);
        if (c == null) {
            throw new RuntimeException("Cliente não encontrado!");
        }
        return c;
    }

    public Client findByDocument(String document) {
        Client c = repository.findByDocument(document);
        if (c == null) {
            throw new RuntimeException("Cliente não encontrado!");
        }   
        return c;
    }

    
}