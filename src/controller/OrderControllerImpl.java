package controller;

import model.Client;
import model.Item;
import model.Order;
import service.OrderService;

import java.math.BigDecimal;

public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    public OrderControllerImpl(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public Order createOrder(Client client) {
        return orderService.createOrder(client);
    }

    @Override
    public void addItem(Item item, Order order) {
        orderService.addItem(item, order);
    }

    @Override
    public void updateItem(Item oldItem, Item newItem, Order order) {
        if (order.getItemList().contains(oldItem)) {
            int index = order.getItemList().indexOf(oldItem);
            order.getItemList().set(index, newItem);
        } else {
            throw new IllegalArgumentException("Item não encontrado no pedido");
        }
    }

    @Override
    public void deleteItem(Item item, Order order) {
        if (!order.getItemList().remove(item)) {
            throw new IllegalArgumentException("Item não encontrado no pedido");
        }
    }

    @Override
    public Order closeOrder(Order order) {
        return orderService.closeOrder(order);
    }

    @Override
    public void startPayment(Order order) {
        orderService.startPayment(order);
    }

    @Override
    public void endPayment(Order order) {
        orderService.endPayment(order);
    }

    @Override
    public BigDecimal getTotalPrice(Order order) {
        return orderService.getTotalOrderPrice(order);
    }

    @Override
    public void delivery(Order order) {
        orderService.delivery(order);
    }

}
