package repository;

import model.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    Optional<Product> findBySku(String sku);
    List<Product> findAll();
    Product update(Product product);
    boolean existsBySkuDifferentId(String sku, UUID differentId);
}