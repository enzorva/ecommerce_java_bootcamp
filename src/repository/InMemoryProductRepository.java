package repository;

import model.Product;

import java.time.LocalDateTime;
import java.util.*;

public class InMemoryProductRepository implements ProductRepository {
    private final Map<UUID, Product> byId = new HashMap<>();
    private final Map<String, UUID> skuIndex = new HashMap<>();

    @Override
    public synchronized Product save(Product product) {
        UUID id = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        product.assignPersistence(id, now, now);
        byId.put(id, product);
        skuIndex.put(product.getSku().toLowerCase(), id);
        return product;
    }

    @Override
    public synchronized Optional<Product> findById(UUID id) {
        return Optional.ofNullable(byId.get(id));
    }

    @Override
    public synchronized Optional<Product> findBySku(String sku) {
        if (sku == null) return Optional.empty();
        UUID id = skuIndex.get(sku.toLowerCase());
        return id == null ? Optional.empty() : Optional.ofNullable(byId.get(id));
    }

    @Override
    public synchronized List<Product> findAll() {
        return new ArrayList<>(byId.values());
    }

    @Override
    public synchronized Product update(Product product) {
        if (product.getId() == null || !byId.containsKey(product.getId())) {
            throw new NoSuchElementException("Produto não encontrado para atualização.");
        }
        product.touchUpdatedAt(LocalDateTime.now());
        byId.put(product.getId(), product);
        skuIndex.put(product.getSku().toLowerCase(), product.getId());
        return product;
    }

    @Override
    public synchronized boolean existsBySkuDifferentId(String sku, UUID differentId) {
        if (sku == null) return false;
        UUID found = skuIndex.get(sku.toLowerCase());
        return found != null && !found.equals(differentId);
    }
}