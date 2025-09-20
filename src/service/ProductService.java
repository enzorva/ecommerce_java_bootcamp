
package service;

import model.Product;
import repository.ProductRepository;
import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {      
        return repository.save(product);
    }

    public List<Product> listAll() {
        return repository.findAll();
    }

    public Product getById(String id) {
        Product p = repository.findById(id);
        if (p == null) {
            throw new RuntimeException("Produto não encontrado.");
        }
        return p;
    }

    public Product update(Product product) {
        Product p = repository.findBySku(product.getSku());
        if (p == null) {
            throw new IllegalArgumentException("Produto inválido!");
        }
        return repository.update(product);
    }

    public Product getBySku(String sku) {
        Product p = repository.findBySku(sku);
        if (p == null) {
            throw new RuntimeException("Produto não encontrado.");
        }
        return p;
    }
}