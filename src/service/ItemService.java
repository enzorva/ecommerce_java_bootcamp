package service;

import model.Item;
import repository.ItemRepository;

import java.util.List;

public class ItemService {
    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item create(Item item) {
        return repository.save(item);
    }

    public List<Item> listAll() {
        return repository.findAll();
    }

    public Item findById(String id) {
        Item item = repository.findById(id);
        if (item == null) {
            throw new RuntimeException("Item não encontrado!");
        }
        return item;
    }

    public void delete(String id) {
        Item item = repository.findById(id);
        if (item == null) {
            throw new RuntimeException("Item não encontrado!");
        }
        repository.delete(id);
    }
}
