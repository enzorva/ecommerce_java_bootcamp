package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Product {
    private String id;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price; // preço padrão do produto
    private boolean status; // ativo/inativo
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String name, String sku, String description, BigDecimal price) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.sku = sku;
        this.description = description;
        this.price = price;
        this.status = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        this.name = name.trim();
    }

    public void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU do produto é obrigatório.");
        }
        this.sku = sku.trim();
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    public void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço do produto deve ser >= 0.");
        }
        this.price = price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }   

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nNome: " + name + "\nSKU: " + sku +
               "\nDescrição: " + description + "\nPreço: " + price;
    }
}
