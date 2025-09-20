package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Order {
    private final String id;
    private final Client client;
    private final Map<String, Item> itens;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private PaymentStatus paymentStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(Client client) {
        this.id = UUID.randomUUID().toString();
        this.client = client;
        this.itens = new HashMap<>();
        this.totalAmount = BigDecimal.ZERO;
        this.orderStatus = OrderStatus.ABERTO;
        this.paymentStatus = PaymentStatus.PENDENTE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() { 
        return id; 
    }

    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }

    public OrderStatus getOrderStatus() { 
        return orderStatus; 
    }

    public PaymentStatus getPaymentStatus() { 
        return paymentStatus; 
    }

    public Client getClient() { 
        return client; 
    }

    public Map<String, Item> getItens() { 
        return itens; 
    }

    public BigDecimal getTotalAmount() { 
        return totalAmount; 
    }

    public LocalDateTime getUpdatedAt() { 
        return updatedAt; 
    }

    public void setOrderStatus(OrderStatus status) { 
        this.orderStatus = status; 
    }

    public void setPaymentStatus(PaymentStatus status) { 
        this.paymentStatus = status; 
    }
}
