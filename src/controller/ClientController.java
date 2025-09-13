package controller;

import model.Client;
import model.ClientType;

public interface ClientController {

    Client createClient(ClientType type);

}