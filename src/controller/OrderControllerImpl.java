package controller;

import model.Client;
import model.Item;
import model.Order;

import repository.OrderRepository;
import service.OrderService;
import java.math.BigDecimal;

public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    public OrderControllerImpl(OrderRepository repository) {

        this.orderService = new OrderService(repository);
    }

    @Override
    public Order createOrder(Client client) {
        Order order = new Order(client);
        return orderService.create(order);
    }

    @Override
    public void addItem(Item item, Order order) {
        orderService.addItem(order, item);
        System.out.println("Item adicionado com sucesso!");
    }

    @Override
    public void updateItem(Item oldItem, Item newItem, Order order) {
        // Remove o antigo e adiciona o novo
        orderService.removeItem(order, oldItem.getId());
        orderService.addItem(order, newItem);
        System.out.println("Item atualizado com sucesso!");
    }

    @Override
    public void deleteItem(Item item, Order order) {
        orderService.removeItem(order, item.getId());
        System.out.println("Item removido com sucesso!");
    }

    @Override
    public Order closeOrder(Order order) {
        Order closed = orderService.closeOrder(order);
        System.out.println("Pedido fechado. Status: " + closed.getOrderStatus() + " | Pagamento: " + closed.getPaymentStatus());
        return closed;
    }

    @Override
    public void startPayment(Order order) {
        orderService.confirmPayment(order);
        System.out.println("Pagamento iniciado.");
    }

    @Override
    public void endPayment(Order order) {
        orderService.confirmPayment(order);
        System.out.println("Pagamento finalizado e pedido atualizado!");
    }

    @Override
    public BigDecimal getTotalPrice(Order order) {
        BigDecimal total = orderService.calculateTotal(order);
        System.out.println("Valor total do pedido: R$ " + total);
        return total;
    }

    @Override
    public void delivery(Order order) {
        orderService.deliver(order);
        System.out.println("Pedido entregue ao cliente.");
    }

    @Override
    public void listAllOrders() {
        // TODO Auto-generated method stub

    }
}
