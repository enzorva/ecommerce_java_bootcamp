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






















    //    public Item(String itemName, int quantity, double unitPrice) {
//        if (quantity <= 0) throw new IllegalArgumentException("Quantidade deve ser maior do que zero");
//        if (unitPrice < 0) throw new IllegalArgumentException("Preco unitario nao pode ser negativo");
//
//        this.itemName = itemName;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//    }
//
//    public double getTotalPrice() {
//        return this.unitPrice * this.quantity;
//    }
//
//    public void changeQuantity(int quantityUpdated) {
//        if (quantityUpdated <= 0) throw new IllegalArgumentException("Quantidade invalida");
//        this.quantity = quantityUpdated;
//    }
}
