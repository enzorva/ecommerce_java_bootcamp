package repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Client;

public class ClientRepository implements Repository<Client, String> {

    private final Map<String, Client> clientes = new HashMap<>();

    @Override
    public Client save(Client client) {
        clientes.put(client.getId(), client);
        return client;
    }

    @Override
    public Client update(Client client) {
        if (!clientes.containsKey(client.getId())) {
            throw new RuntimeException("Cliente não encontrado para atualização!");
        }
        clientes.put(client.getId(), client);
        return client;
    }

    @Override
    public List<Client> findAll() {
        return new ArrayList<>(clientes.values());
    }

    @Override
    public Client findById(String id) {
        return clientes.get(id);
    }
}