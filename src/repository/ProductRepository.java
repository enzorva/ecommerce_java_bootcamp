package repository;

import model.Product;

import java.util.*;

public class ProductRepository implements Repository<Product, String> {

        private final Map<String, Product> products = new HashMap<>();

    @Override
    public Product save(Product product) {
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public Product update(Product product) {
        if (!products.containsKey(product.getId())) {
            throw new RuntimeException("Produto não encontrado para atualização!");
        }
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product findById(String id) {
        return products.get(id);
    }

    public Product findBySku(String sku) {
        return products.values().stream()
            .filter(c -> c.getSku().equals(sku))
            .findFirst()
            .orElse(null);
    }
}