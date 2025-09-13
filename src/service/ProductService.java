
package service;

import model.Product;
import repository.ProductRepository;
import repository.InMemoryProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(InMemoryProductRepository repository) {
        this.repository = repository;
    }

    public Product create(String name, String sku, String description, BigDecimal price) {
        if (repository.findBySku(sku).isPresent()) {
            throw new IllegalArgumentException("Já existe produto com o SKU informado.");
        }
        Product p = new Product(name, sku, description, price);
        return repository.save(p);
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Produto não encontrado."));
    }

    public Product update(UUID id, String name, String sku, String description, BigDecimal price, Boolean active) {
        Product existing = getById(id);
        if (sku != null && repository.existsBySkuDifferentId(sku, id)) {
            throw new IllegalArgumentException("SKU já utilizado por outro produto.");
        }
        existing.update(name, sku, description, price, active);
        return repository.update(existing);
    }
}