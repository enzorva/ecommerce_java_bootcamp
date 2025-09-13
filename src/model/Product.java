package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String sku;
    private String description;
    private BigDecimal price; // preço padrão do produto
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Product(String name, String sku, String description, BigDecimal price) {
        setName(name);
        setSku(sku);
        setDescription(description);
        setPrice(price);
        this.active = true;
    }


    public void assignPersistence(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String name, String sku, String description, BigDecimal price, Boolean active) {
        if (name != null) setName(name);
        if (sku != null) setSku(sku);
        if (description != null) setDescription(description);
        if (price != null) setPrice(price);
        if (active != null) this.active = active;
    }

    private void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        this.name = name.trim();
    }

    private void setSku(String sku) {
        if (sku == null || sku.isBlank()) {
            throw new IllegalArgumentException("SKU do produto é obrigatório.");
        }
        this.sku = sku.trim();
    }

    private void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    private void setPrice(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Preço do produto deve ser >= 0.");
        }
        this.price = price;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getSku() { return sku; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public boolean isActive() { return active; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public void touchUpdatedAt(LocalDateTime time) {
        this.updatedAt = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}