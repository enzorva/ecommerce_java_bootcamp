import controller.ClientController;
import controller.ClientControllerImpl;
import model.ClientType;
import repository.ClientRepository;
import validation.ClientValidator;
import validation.GenericClientValidator;

public class Main {
    public static void main(String[] args) {

        ClientRepository repository = new ClientRepository();
        ClientValidator validator = new GenericClientValidator();
        
        ClientController controller = new ClientControllerImpl(repository, validator);

        controller.createClient(ClientType.PF);
        controller.listAllClients();
        controller.updateClient();
        controller.searchByDocument();
        
        

    }
}
