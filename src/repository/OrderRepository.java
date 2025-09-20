package repository;

import model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderRepository implements Repository<Order, String> {
    private final Map<String, Order> storage = new HashMap<>();

    @Override
    public Order save(Order order) {
        storage.put(order.getId(), order);
        return order;
    }

    @Override
    public Order update(Order order) {
        if (!storage.containsKey(order.getId())) {
            throw new IllegalArgumentException("Pedido nao encontrado para atualizacao");
        }
        storage.put(order.getId(), order);
        return order;
    }

    @Override
    public List<Order> findAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public Order findById(String id) {
        return storage.get(id);
    }
}
