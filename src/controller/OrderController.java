package controller;

import model.Client;
import model.Item;
import model.Order;

import java.math.BigDecimal;

public interface OrderController {

    public Order createOrder(Client client);

    public void listAllOrders();

    public void addItem(Item item, Order order);

    public void updateItem(Item oldItem, Item newItem, Order order);

    public void deleteItem(Item item, Order order);

    public Order closeOrder(Order order);

    public void startPayment(Order order);

    public void endPayment(Order order);

    public BigDecimal getTotalPrice(Order order);

    public void delivery(Order order);
}

