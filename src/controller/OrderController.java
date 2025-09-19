package controller;

import model.Client;
import model.Item;
import model.Order;

import java.math.BigDecimal;

public interface OrderController {

    Order createOrder(Client client);

    void addItem(Item item, Order order);

    void updateItem(Item oldItem, Item newItem, Order order);

    void deleteItem(Item item, Order order);

    Order closeOrder(Order order);

    void startPayment(Order order);

    void endPayment(Order order);

    BigDecimal getTotalPrice(Order order);

    void delivery(Order order);
}

