package controller;

import java.math.BigDecimal;

import model.Client;
import model.Item;
import model.Order;
import model.Product;
import repository.ClientRepository;
import repository.ProductRepository;
import util.Read;

public class OrderMenu implements Menu {

    private final OrderController orderController;
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public OrderMenu(OrderController orderController, ClientRepository clientRepository, ProductRepository productRepository) {
        this.orderController = orderController;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void display() {
        while (true) {
            System.out.println("\n===== Pedido =====");
            System.out.println("1. Cadastrar novo pedido para cliente");
            System.out.println("2. Selecionar pedido existente por ID");
            System.out.println("3. Listar pedidos");
            System.out.println("0. Voltar ao menu principal");
            int option = Read.inputInteger("Escolha uma opção: ");
            switch (option) {
                case 1 -> createNewOrderFlow();
                case 2 -> manageExistingOrderFlow();
                case 3 -> orderController.listAllOrders();
                case 0 -> {
                    System.out.println("Retornando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void createNewOrderFlow() {
        String document = Read.inputString("Digite o documento do cliente: ");
        Client client = clientRepository.findByDocument(document);
        if (client == null) {
            System.out.println("Cliente não encontrado. Cadastre o cliente primeiro.");
            return;
        }
        Order order = orderController.createOrder(client);
        System.out.println("Pedido criado. ID: " + order.getId());
        // abrir o fluxo de gerenciamento do pedido recém-criado
        manageOrderLoop(order);
    }

    private void manageExistingOrderFlow() {
        String id = Read.inputString("Digite o ID do pedido: ");
        try {
            Order order = orderController.getById(id);
            if (order == null) {
                System.out.println("Pedido não encontrado.");
                return;
            }
            manageOrderLoop(order);
        } catch (Exception e) {
            System.out.println("Erro ao abrir pedido: " + e.getMessage());
        }
    }


    private void manageOrderLoop(Order order) {
        while (true) {
            System.out.println("\nGerenciando pedido: " + order.getId() + " | Cliente: " + order.getClient().getName());
            System.out.println("1. Adicionar item");
            System.out.println("2. Remover item (por SKU)");
            System.out.println("3. Alterar quantidade (por SKU)");
            System.out.println("4. Listar itens do pedido");
            System.out.println("5. Calcular total");
            System.out.println("6. Fechar pedido (aguardando pagamento)");
            System.out.println("7. Pagar pedido");
            System.out.println("8. Entregar pedido");
            System.out.println("0. Voltar");
            int opt = Read.inputInteger("Escolha: ");
            switch (opt) {
                case 1 -> addItemFlow(order);
                case 2 -> removeItemFlow(order);
                case 3 -> changeQuantityFlow(order);
                case 4 -> listItems(order);
                case 5 -> orderController.getTotalPrice(order);
                case 6 -> {
                    try {
                        order = orderController.closeOrder(order);
                    } catch (Exception e) {
                        System.out.println("Não foi possível fechar pedido: " + e.getMessage());
                    }
                }
                case 7 -> {
                    try {
                        orderController.endPayment(order);
                    } catch (Exception e) {
                        System.out.println("Pagamento falhou: " + e.getMessage());
                    }
                }
                case 8 -> {
                    try {
                        orderController.delivery(order);
                    } catch (Exception e) {
                        System.out.println("Não foi possível entregar: " + e.getMessage());
                    }
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void addItemFlow(Order order) {
        String sku = Read.inputString("Digite o SKU do produto: ");
        Product product = productRepository.findBySku(sku);
        if (product == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        int qty = Read.inputInteger("Quantidade: ");
        String priceStr = Read.inputString("Preço unitário de venda (ex: 10.50): ");
        try {
            BigDecimal unitPrice = BigDecimal.valueOf(Double.parseDouble(priceStr));
            Item item = new Item(product, qty, unitPrice);
            orderController.addItem(item, order);
        } catch (NumberFormatException ex) {
            System.out.println("Preço inválido.");
        }
    }

    private void removeItemFlow(Order order) {
        String sku = Read.inputString("Digite o SKU do produto a remover: ");
        Product product = productRepository.findBySku(sku);
        if (product == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        // construir um Item com product para passar ao controller.deleteItem
        Item item = new Item(product, 0, BigDecimal.ZERO); // quantidade e preço não importam aqui
        orderController.deleteItem(item, order);
    }

    private void changeQuantityFlow(Order order) {
        String sku = Read.inputString("Digite o SKU do produto: ");
        Product product = productRepository.findBySku(sku);
        if (product == null) {
            System.out.println("Produto não encontrado.");
            return;
        }
        int qty = Read.inputInteger("Nova quantidade: ");
        try {
            orderController.changeItemQuantity(order, product.getId(), qty);
            System.out.println("Quantidade atualizada.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void listItems(Order order) {
        System.out.println("Itens do pedido:");
        order.getItens().values().forEach(i -> System.out.println(i));
    }    
}
