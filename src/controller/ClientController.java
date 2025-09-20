package controller;

import model.Client;
import model.ClientType;

public interface ClientController {

    public Client createClient(ClientType type);

    public void listAllClients();

    public void updateClient();

    public void searchByDocument();

}
