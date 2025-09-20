import java.util.Scanner;

import controller.ClientControllerImpl;
import controller.ClientMenu;
import controller.Menu;
import controller.OrderControllerImpl;
import controller.OrderMenu;
import controller.ProductControllerImpl;
import controller.ProductMenu;
import repository.ClientRepository;
import repository.OrderRepository;
import repository.ProductRepository;
import util.Read;
import validation.ClientValidator;
import validation.GenericClientValidator;

public class Main {

    public static void main(String[] args) {
  
        ClientValidator clientValidator = new GenericClientValidator();
        
        ClientRepository clientRepository = new ClientRepository();
        ProductRepository productRepository = new ProductRepository();
        OrderRepository orderRepository = new OrderRepository();

        ClientControllerImpl clientController = new ClientControllerImpl(clientRepository, clientValidator);
        ProductControllerImpl productController = new ProductControllerImpl(productRepository);
        OrderControllerImpl orderController = new OrderControllerImpl(orderRepository);


        Menu clientMenu = new ClientMenu(clientController);
        Menu productMenu = new ProductMenu(productController);
        Menu orderMenu = new OrderMenu(orderController, clientRepository);

        while (true) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1. Cliente");
            System.out.println("2. Produto");
            System.out.println("3. Pedido");
            System.out.println("0. Sair");

            int option = Read.inputInteger("Escolha uma opção: ");

            switch (option) {
                case 1 -> clientMenu.display();
                case 2 -> productMenu.display();
                case 3 -> orderMenu.display();
                case 0 -> {
                    System.out.println("Logoff realizado com sucesso!");                    
                    return;
                }            
                default -> System.out.println("Opção inválida.");
            }
        }
    }
}
