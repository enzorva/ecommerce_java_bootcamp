package controller;

import model.ClientType;
import util.Read;

public class ClientMenu implements Menu {

    private final ClientController clientController;


    public ClientMenu(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void display() {
        while (true) {
            System.out.println("\n===== Cliente =====");
            System.out.println("1. Cadastrar PF");
            System.out.println("2. Cadastrar PJ");
            System.out.println("3. Listar");
            System.out.println("4. Atualizar");
            System.out.println("5. Buscar por Documento");
            System.out.println("0. Voltar ao menu principal");

            int option = Read.inputInteger("Escolha uma opção: ");

            switch (option) {
                case 1 -> clientController.createClient(ClientType.PF);
                case 2 -> clientController.createClient(ClientType.PJ);
                case 3 -> clientController.listAllClients();
                case 4 -> clientController.updateClient();
                case 5 -> clientController.searchByDocument();
                case 0 -> {
                    System.out.println("Retornando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }    
    }
}
