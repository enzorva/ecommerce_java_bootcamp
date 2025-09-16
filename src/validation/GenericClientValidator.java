package validation;

import java.util.HashMap;
import java.util.Map;

import model.Client;
import model.ClientPF;
import model.ClientPJ;

public class GenericClientValidator implements ClientValidator {

    private final Map<Class<? extends Client>, ClientValidator> validators = new HashMap<>();

    public GenericClientValidator() {
        validators.put(ClientPF.class, new ClientPFValidator());
        validators.put(ClientPJ.class, new ClientPJValidator());
    }

    @Override
    public boolean validate(Client client) {
        for (Map.Entry<Class<? extends Client>, ClientValidator> entry : validators.entrySet()) {
            if (entry.getKey().isAssignableFrom(client.getClass())) {
                return entry.getValue().validate(client);
            }
        }
        throw new IllegalArgumentException("Validador não encontrado para a classe: " + client.getClass());
    }
}
