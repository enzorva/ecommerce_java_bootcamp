package service;

import model.Client;

public class NotificationService {

    public void notifyOrderAwaitingPayment(Client client, String orderId) {
        System.out.println("[Email] Para: " + client.getEmail() 
        + " | Assunto: Pedido " + orderId + " aguardando pagamento."
        + " | Conteúdo: Olá " + client.getName() + " seu pedido está aguardando pagamento. Obrigado.");
    }

    public void notifyPaymentConfirmed(Client client, String orderId) {
        System.out.println("[Email] Para: " + client.getEmail()
            + " | Assunto: Pagamento recebido - Pedido " + orderId
            + " Conteúdo: Olá " + client.getName() + " recebemos seu pagamento. Em breve enviaremos o pedido.");
    }

    public void notifyDelivery(Client client, String orderId) {
        System.out.println("[Email] Para: " + client.getEmail()
            + " | Assunto: Pedido entregue - " + orderId
            + " Conteúdo: Olá " + client.getName() + "o seu pedido foi entregue. Obrigado pela compra!");
    }     
}
