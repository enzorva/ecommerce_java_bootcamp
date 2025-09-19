package service;

import model.*;
import repository.OrderRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Client client) {
        Order order = new Order();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderStatus(OrderStatus.ABERTO);
        order.setClientId(client.getId());
        order.setPaymentStatus(PaymentStatus.NAO_INICIADO);
        order.setCreationDate(LocalDateTime.now());
        order.setItemList(new ArrayList<>());
        System.out.println("Pedido criado com sucesso!");
        return orderRepository.save(order);
    }

    public void addItem(Item item, Order order) {
        if (order.getOrderStatus() != OrderStatus.ABERTO) {
            throw new IllegalStateException("Nao e permitido adicionar itens neste estado");
        }
        order.getItemList().add(item);
    }

    // E melhor separar closeOrder de startPayment ou mante-los juntos

    public Order closeOrder(Order order) {
        order.setOrderStatus(OrderStatus.AGUARDANDO_PAGAMENTO);
        return orderRepository.save(order);
    }

    public void startPayment(Order order) {
        if (order.getItemList().isEmpty() || getTotalOrderPrice(order).compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalStateException("Pedido invalido para finalizar");
        }
        order.setPaymentStatus(PaymentStatus.AGUARDANDO_PAGAMENTO);
    }

    public BigDecimal getTotalOrderPrice(Order order) {
        return order.getItemList().stream()
                .map(item -> BigDecimal.valueOf(item.getUnitPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void endPayment(Order order) {
        if (order.getPaymentStatus() != PaymentStatus.AGUARDANDO_PAGAMENTO) {
            throw new IllegalStateException("Aguardando pagamento");
        }
        order.setOrderStatus(OrderStatus.FINALIZADO);
        order.setPaymentStatus(PaymentStatus.PAGO);
    }

    public void delivery(Order order) {
        if (order.getPaymentStatus() != PaymentStatus.PAGO) {
            throw new IllegalStateException("Entrega nao permitida sem pagamento");
        }
    }
}
