package repository;

import model.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemRepository implements Repository<Item, String>{
    
    private final Map<String, Item> storage = new HashMap<>();

    @Override
    public Item save(Item item) {
        storage.put(item.getId(), item);
        return item;
    }

    @Override
    public Item update(Item item) {
        if (!storage.containsKey(item.getId())) {
            throw new IllegalArgumentException("Item nao foi encontrado");
        }
        // O que estou fazendo aqui
        storage.put(item.getId(), item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Item findById(String id) {
        // Pq aqui fica diferente do OrderRepository
        return storage.get(id);
    }

    public void delete(String id) {
        storage.remove(id);
    }
}
