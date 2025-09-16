package controller;

import java.util.Scanner;

import model.Client;
import model.ClientPF;
import model.ClientPJ;
import model.ClientType;
import repository.ClientRepository;
import validation.ClientValidator;
import service.ClientService;

public class ClientControllerImpl implements ClientController {

    private final ClientService clientService;
    private final Scanner scanner;

    public ClientControllerImpl(ClientRepository repository, ClientValidator validator) {
        this.clientService = new ClientService(repository, validator);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Client createClient(ClientType type) {
        System.out.println("=== Cadastro de Cliente ===");
        System.out.print("Nome: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Telefone: ");
        String phone = scanner.nextLine();

        Client client = null;

        if (type == ClientType.PF) {
            System.out.print("CPF (apenas números): ");
            String cpf = scanner.nextLine();        
            client = new ClientPF(name, email, phone, cpf);   
        } else if (type == ClientType.PJ) {
            System.out.print("CNPJ (apenas números): ");
            String cnpj = scanner.nextLine();        
            client = new ClientPJ(name, email, phone, cnpj);
    
        } 
        return clientService.register(client);      
    }

    @Override
    public void listAllClients() {
        System.out.println("=== Lista de Clientes ===");
        for (Client client : clientService.listAll()) {
            System.out.println(client);
        }
    }

    @Override
    public void updateClient() {
        System.out.println("=== Atualizar Cliente ===");
        System.out.print("Digite o documento (CPF/CNPJ) do cliente: ");
        String document = scanner.nextLine();
        Client client = clientService.findByDocument(document);

        System.out.print("Novo nome: ");
        client.setName(scanner.nextLine());
        System.out.print("Novo email: ");
        client.setEmail(scanner.nextLine());
        System.out.print("Novo telefone: ");
        client.setPhone(scanner.nextLine());

        clientService.update(client);

        System.out.println("Cliente atualizado com sucesso!");
    }

    @Override
    public void searchByDocument() {
        System.out.println("=== Buscar Cliente ===");
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String document = scanner.nextLine();
        Client client = clientService.findByDocument(document);
        System.out.println(client);
    }

    
}
