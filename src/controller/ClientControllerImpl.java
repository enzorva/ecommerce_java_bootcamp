package controller;

import java.util.Scanner;

import model.Client;
import model.ClientPF;
import model.ClientType;
import repository.ClientRepository;
import repository.ClientValidator;
import service.CreateClientService;

public class ClientControllerImpl implements ClientController {

    private CreateClientService createClientService;

    public ClientControllerImpl() {
        ClientRepository repository = new ClientRepository();
        ClientValidator validator = new ClientValidator();
        this.createClientService = new CreateClientService(repository, validator);
    }

    @Override
    public Client createClient(ClientType type) {
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        System.out.println("=== Cadastro de Cliente ===");
        System.out.println("Digite os dados do cliente:");
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String phone = scanner.nextLine();
        if (type == ClientType.PF) {
            System.out.print("CPF (apenas números): ");
            String cpf = scanner.nextLine();        
            client = new ClientPF(name, email, phone, cpf);   
        } else if (type == ClientType.PJ) {
            System.out.print("CNPJ (apenas números): ");
            String cnpj = scanner.nextLine();        
            client = new ClientPF(name, email, phone, cnpj);
    
        } 
        scanner.close();
        return createClientService.register(client);      
    }
}
