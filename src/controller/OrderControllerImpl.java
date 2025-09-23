package controller;

import model.Client;
import model.Item;
import model.Order;

import repository.OrderRepository;
import service.NotificationService;
import service.OrderService;
import java.math.BigDecimal;
import java.util.List;

public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;
    private final NotificationService notificationService;

    public OrderControllerImpl(OrderRepository repository) {
        this.orderService = new OrderService(repository);
        this.notificationService = new NotificationService();
    }

    @Override
    public Order createOrder(Client client) {
        Order order = new Order(client);
        Order created = orderService.create(order);
        System.out.println("Pedido criado: id=" + created.getId() + " | Cliente: " + client.getName());
        return created;
    }

    @Override
    public void addItem(Item item, Order order) {
        orderService.addItem(order, item);
        System.out.println("Item adicionado com sucesso!");
    }

    @Override
    public void updateItem(Item oldItem, Item newItem, Order order) {
        // Remove o antigo e adiciona o novo
        orderService.removeItem(order, oldItem.getProduct().getId());
        orderService.addItem(order, newItem);
        System.out.println("Item atualizado com sucesso!");
    }

    @Override
    public void deleteItem(Item item, Order order) {
        orderService.removeItem(order, item.getProduct().getId());
        System.out.println("Item removido com sucesso!");
    }

    @Override
    public Order closeOrder(Order order) {
        Order closed = orderService.closeOrder(order);
        notificationService.notifyOrderAwaitingPayment(order.getClient(), closed.getId());
        System.out.println("Pedido fechado. Status: " + closed.getOrderStatus() + " | Pagamento: " + closed.getPaymentStatus());
        return closed;
    }

    @Override
    public void startPayment(Order order) {
        orderService.confirmPayment(order);
        System.out.println("Iniciando processo de pagamento para pedido: " + order.getId());
    }

    @Override
    public void endPayment(Order order) {
        orderService.confirmPayment(order);
        notificationService.notifyPaymentConfirmed(order.getClient(), order.getId());
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
        notificationService.notifyDelivery(order.getClient(), order.getId());
        System.out.println("Pedido entregue ao cliente.");
    }

    @Override
    public void listAllOrders() {
        List<Order> all = orderService.listAll();
        System.out.println("===== Lista de Pedidos =====");
        for (Order o : all) {
            System.out.println("ID: " + o.getId()
                + " | Cliente: " + o.getClient().getName()
                + " | Status Pedido: " + o.getOrderStatus()
                + " | Status Pagamento: " + o.getPaymentStatus()
                + " | Criado em: " + o.getCreatedAt()
                + " | Total: " + orderService.calculateTotal(o));
        }
    }

    @Override
    public Order getById(String id) {
        return orderService.getById(id);
    }

    @Override
    public void changeItemQuantity(Order order, String productId, int newQuantity) {
        orderService.changeItemQuantity(order, productId, newQuantity);
        System.out.println("Quantidade do item atualizada com sucesso!");
    }

    @Override
    public List<Order> listAll() {
        return orderService.listAll();
    }
}
