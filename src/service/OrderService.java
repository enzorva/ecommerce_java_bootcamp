package service;

import repository.OrderRepository;

import java.math.BigDecimal;
import java.util.NoSuchElementException;

import model.Item;
import model.Order;
import model.OrderStatus;
import model.PaymentStatus;
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    /** Cria um novo pedido */
    public Order create(Order order) {
        return repository.save(order);
    }

    /** Adiciona um item ao pedido */
    public void addItem(Order order, Item item) {
        if (order.getOrderStatus() != OrderStatus.ABERTO)
            throw new IllegalStateException("Items can only be added when order is OPEN");

        Item existent = order.getItens().get(item.getId());
        if (existent != null) {
            existent.setQuantity(existent.getQuantity() + item.getQuantity());
        } else {
            order.getItens().put(item.getId(), item);
        }
        repository.update(order);
    }

    /** Remove item do pedido */
    public void removeItem(Order order, String productId) {
        if (order.getOrderStatus() != OrderStatus.ABERTO)
            throw new IllegalStateException("Items can only be removed when order is OPEN");
        order.getItens().remove(productId);
        repository.update(order);
    }

    /** Altera quantidade de item */
    public void changeItemQuantity(Order order, String productId, int newQuantity) {
        if (order.getOrderStatus() != OrderStatus.ABERTO)
            throw new IllegalStateException("Quantities can only be changed when order is OPEN");
        Item ip = order.getItens().get(productId);
        if (ip == null) throw new NoSuchElementException("Item does not exist in order");
        ip.setQuantity(newQuantity);
        repository.update(order);
    }

    /** Calcula o valor total do pedido */
    public BigDecimal calculateTotal(Order order) {
        return order.getItens()
            .values()
            .stream()
            .map(Item::total)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /** Fecha o pedido e marca aguardando pagamento */
    public Order closeOrder(Order order) {
        if (order.getItens().isEmpty() || calculateTotal(order).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Order must have at least one item and total > 0");
        }
        order.setPaymentStatus(PaymentStatus.AGUARDANDO_PAGAMENTO);
        order.setOrderStatus(OrderStatus.ABERTO); // Mantém ABERTO até efetivar pagamento
        return repository.update(order);
    }

    /** Confirma pagamento */
    public void confirmPayment(Order order) {
        if (order.getPaymentStatus() != PaymentStatus.AGUARDANDO_PAGAMENTO)
            throw new IllegalStateException("Payment can only be confirmed if status is AWAITING_PAYMENT");
        order.setPaymentStatus(PaymentStatus.PAGO);
        repository.update(order);
    }

    /** Entrega o pedido */
    public void deliver(Order order) {
        if (order.getPaymentStatus() != PaymentStatus.PAGO)
            throw new IllegalStateException("Order can only be delivered after payment");
        order.setOrderStatus(OrderStatus.FINALIZADO);
        repository.update(order);
    }
}
