package controller;
import model.Product;
import service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.Objects;

public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = Objects.requireNonNull(service, "service não pode ser nulo");
    }


    public Product create(String name, String sku, String description, BigDecimal price) {
        return service.create(name, sku, description, price);
    }

    public List<Product> list() {
        return service.listAll();
    }

    public Product findById(UUID id) {
        Objects.requireNonNull(id, "id não pode ser nulo");
        return service.getById(id);
    }

    public Product update(UUID id, String name, String sku, String description, BigDecimal price, Boolean active) {
        Objects.requireNonNull(id, "id não pode ser nulo");
        return service.update(id, name, sku, description, price, active);
    }

    // ... existing code ...
    @Deprecated(since = "1.1")
    public Product createProduct(String name, String sku, String description, BigDecimal price) {
        return create(name, sku, description, price);
    }

    @Deprecated(since = "1.1")
    public List<Product> listProducts() {
        return list();
    }

    @Deprecated(since = "1.1")
    public Product getProduct(UUID id) {
        return findById(id);
    }

    @Deprecated(since = "1.1")
    public Product updateProduct(UUID id, String name, String sku, String description, BigDecimal price, Boolean active) {
        return update(id, name, sku, description, price, active);
    }
    // ... existing code ...
}