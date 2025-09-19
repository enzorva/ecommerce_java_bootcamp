package service;

import model.Item;
import repository.ItemRepository;

import java.util.UUID;

public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public Item createItem() {
        Item item = new Item();
        item.setItemId(UUID.randomUUID().toString());
        item.setItemName();
        item.setQuantity(1);
        item.setUnitPrice();
        return itemRepository.save(item);
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
