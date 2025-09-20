package controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

import model.Product;
import repository.ProductRepository;
import service.ProductService;

public class ProductControllerImpl implements ProductController {
    private final ProductService productService;
    private final Scanner scanner;

    public ProductControllerImpl(ProductRepository repository) {
        this.productService = new ProductService(repository);
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Product createProduct() {
        System.out.println("===== Cadastrar Produto =====");
        System.out.print("Digite o nome do produto: ");
        String name = scanner.nextLine();
        System.out.print("Digite o SKU do produto: ");
        String sku = scanner.nextLine();
        System.out.print("Digite a descrição do produto: ");
        String description = scanner.nextLine();
        System.out.print("Digite o preço do produto: ");
        String priceInput = scanner.nextLine();
        try {
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceInput));
            Product product = new Product(name, sku, description, price);
            System.out.println("Produto cadastrado com sucesso!");
            return productService.create(product);
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido. Cadastro cancelado.");
            return null;
        }    
    }

    @Override
    public void listAllProducts() {
        System.out.println("===== Lista de Produtos =====");
        for (Product product : productService.listAll()) {
            System.out.println(product);
        }
    }

    @Override
    public void updateProduct() {
        System.err.println("===== Atualizar Produto =====");
        System.out.print("Digite o código de referência do produto (SKU): ");
        String sku = scanner.nextLine();
        try {
            Product product = productService.getBySku(sku);
            System.out.print("Digite o novo nome: ");
            String name = scanner.nextLine();   
            System.out.print("Digite a a nova descrição: ");
            String description = scanner.nextLine();
            System.out.print("Digite o novo preço: ");
            String priceInput = scanner.nextLine();
            BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceInput));
            System.out.print("Ativo? (s/n, deixe vazio para manter '" + (product.isStatus() ? "s" : "n") + "'): ");
            String statusInput = scanner.nextLine();
            Boolean status = null;
            if (statusInput.equalsIgnoreCase("s")) {
                status = true;
            } else if (statusInput.equalsIgnoreCase("n")) {
                status = false;
            }
            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setStatus(status != null ? status : product.isStatus());
            product.setUpdatedAt(LocalDateTime.now());
            productService.update(product);
            System.out.println("Produto atualizado com sucesso!");
        } catch (NumberFormatException ex) {
            System.out.println("Preço inválido. Atualização cancelada.");
        }
    }

    @Override
    public void searchBySku() {
        System.out.println("=== Buscar Produto ===");
        System.out.print("Digite o código de referência (SKU): ");
        String sku= scanner.nextLine();
        Product product = productService.getBySku(sku);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Produto não encontrado.");
        } 
    }
}