import controller.ProductController;
import model.Product;
import repository.InMemoryProductRepository;
import service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final String MENU_LINE = "===============================";
    private static final String MENU_TITLE = "     ADA COMMERCE - PRODUTOS   ";

    public static void main(String[] args) {
        var repository = new InMemoryProductRepository();
        var service = new ProductService(repository);
        var controller = new ProductController(service);
        try (Scanner scanner = new Scanner(System.in)) {
            runMenu(controller, scanner);
        }
>>>>>>> fa48e85ecab5b48459b64856827429f3389de3df
    }
    private static void runMenu(ProductController controller, Scanner scanner) {
        while (true) {
            printMenu();
            String option = prompt(scanner, "Escolha uma opção: ").trim();
            try {
                switch (option) {
                    case "1":
                        listProductsFlow(controller);
                        break;
                    case "2":
                        createProductFlow(controller, scanner);
                        break;
                    case "3":
                        updateProductFlow(controller, scanner);
                        break;
                    case "0":
                        System.out.println("Encerrando...");
                        return;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void listProductsFlow(ProductController controller) {
        List<Product> products = controller.list();
        if (products.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        System.out.println("\nID | SKU | NOME | PREÇO | ATIVO");
        for (Product p : products) {
            System.out.println(formatProduct(p));
        }
    }

    private static void createProductFlow(ProductController controller, Scanner scanner) {
        String name = prompt(scanner, "Nome: ");
        String sku = prompt(scanner, "SKU: ");
        String desc = prompt(scanner, "Descrição: ");
        BigDecimal price = promptPrice(scanner, "Preço (ex: 79,90 ou 79.90): ");
        Product p = controller.create(name, sku, desc, price);
        System.out.println("Produto criado com sucesso! ID: " + p.getId());
    }

    private static void updateProductFlow(ProductController controller, Scanner scanner) {
        String idStr = prompt(scanner, "Informe o ID do produto: ");
        UUID id = UUID.fromString(idStr.trim());
        Product current = controller.findById(id);
        System.out.println(String.format(
                "Produto atual: %s | SKU: %s | Preço: %s | Ativo: %s",
                current.getName(), current.getSku(), current.getPrice(), current.isActive() ? "sim" : "não"
        ));
        String name = promptOptional(scanner, "Novo nome (enter para manter): ");
        String sku = promptOptional(scanner, "Novo SKU (enter para manter): ");
        String desc = promptOptional(scanner, "Nova descrição (enter para manter): ");

        String priceStr = prompt(scanner, "Novo preço (enter para manter): ").trim();
        BigDecimal price = priceStr.isBlank() ? null : parsePrice(priceStr);

        String activeStr = prompt(scanner, "Ativo? (s/n, enter para manter): ").trim();
        Boolean active = parseYesNoOptional(activeStr);

        Product updated = controller.update(id, name, sku, desc, price, active);
        System.out.println(String.format(
                "Atualizado: %s | SKU: %s | Preço: %s | Ativo: %s",
                updated.getName(), updated.getSku(), updated.getPrice(), updated.isActive() ? "sim" : "não"
        ));
    }

    private static String emptyToNull(String s) {
        return s == null || s.isBlank() ? null : s;
    }

    private static void printMenu() {
        System.out.println("\n" + MENU_LINE);
        System.out.println(MENU_TITLE);
        System.out.println(MENU_LINE);
        System.out.println("1) Listar produtos");
        System.out.println("2) Cadastrar produto");
        System.out.println("3) Atualizar produto");
        System.out.println("0) Sair");
    }
    private static String prompt(Scanner scanner, String label) {
        System.out.print(label);
        return scanner.nextLine();
    }
    private static String promptOptional(Scanner scanner, String label) {
        return emptyToNull(prompt(scanner, label));
    }
    private static BigDecimal promptPrice(Scanner scanner, String label) {
        String raw = prompt(scanner, label);
        return parsePrice(raw);
    }
    private static BigDecimal parsePrice(String raw) {
        String normalized = raw.trim().replace(",", ".");
        return new BigDecimal(normalized);
    }
    private static Boolean parseYesNoOptional(String raw) {
        if (raw == null || raw.isBlank()) return null;
        String v = raw.trim().toLowerCase();
        if (v.startsWith("s")) return true;
        if (v.startsWith("n")) return false;
        throw new IllegalArgumentException("Valor inválido para campo Ativo (use s/n ou deixe em branco).");
    }
    private static String formatProduct(Product p) {
        return String.format("%s | %s | %s | R$ %s | %s",
                p.getId(), p.getSku(), p.getName(), p.getPrice(), (p.isActive() ? "sim" : "não"));
    }
}