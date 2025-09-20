package controller;

import util.Read;

public class ProductMenu implements Menu {

    private final ProductController productController;

    public ProductMenu(ProductController productController) {
        this.productController = productController;
    }

    @Override
    public void display() {
        while(true) {
            System.out.println("\n===== Produto =====");
            System.out.println("1. Cadastrar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Buscar por Código de referência");
            System.out.println("0. Voltar ao menu principal");

            int option = Read.inputInteger("Escolha uma opção: ");

            switch (option) {
                case 1 -> productController.createProduct();
                case 2 -> productController.listAllProducts();
                case 3 -> productController.updateProduct();
                case 4 -> productController.searchBySku();
                case 0 -> {
                    System.out.println("Retornando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }  
    }    
}
