package model;

import java.util.UUID;

public class Item {
    private String itemId;
    private String itemName;
    private int quantity;
    private double unitPrice;

    // Porque tirar a inicializacao do itemId do construtor e jogar para o ItemService assim como eu fiz no Order
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName() {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
