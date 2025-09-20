package controller;

import model.Client;
import repository.ClientRepository;
import util.Read;

public class OrderMenu implements Menu {

    private final OrderController orderController;
    private final ClientRepository clientRepository;

    public OrderMenu(OrderController orderController, ClientRepository clientRepository) {
        this.orderController = orderController;
        this.clientRepository = clientRepository;
    }

    @Override
    public void display() {
        while (true) {
            System.out.println("\n===== Pedido =====");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("0. Voltar ao menu principal");
            int option = Read.inputInteger("Escolha uma opção: ");

            switch (option) {
                case 1 -> {
                    String document = Read.inputString("Digite o documento do cliente: ");
                    Client client = clientRepository.findByDocument(document);
                    if (client == null) {
                        System.out.println("Cliente não encontrado. Por favor, cadastre o cliente primeiro.");
                        return;
                    }
                    orderController.createOrder(client);
                }
                case 2 -> orderController.listAllOrders();
                case 0 -> {
                    System.out.println("Retornando ao menu principal...");    
                    return;     
                }   
                default -> System.out.println("Opção inválida.");
            }
        }
    }    
}
