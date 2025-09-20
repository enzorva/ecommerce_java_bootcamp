package model;

import java.math.BigDecimal;
import java.util.UUID;

public class Item {
    private String id;
    private Product product; // Referência ao produto
    private int quantity;
    private double unitPrice; // preço de venda definido no pedido

    public Item(Product product, int quantity, double unitPrice) {
        this.id = UUID.randomUUID().toString();
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public void setId(String id) {
        this.id = id;
    }    
    
    public String getId() {
        return id;
    }


    public Product getProduct() {
        return product;
    }       

    public void setProduct(Product product) {
        this.product = product;
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

    @Override
    public String toString() {
        return "ID: " + id + "\nDescrição: " + product.getDescription() + "\nQuantidade: " + quantity + "\nPreço Unitário: " + unitPrice;
    }

    public BigDecimal total() {
        return BigDecimal.valueOf(unitPrice * quantity);
    }
}
