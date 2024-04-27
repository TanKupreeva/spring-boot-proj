package com.example.demo.service;

import com.example.demo.model.Client;

import java.util.List;

public interface ClientService {


    String addClient(Client client);

    List<Client> getAllClients();

    String updateClient(Integer clientId, Client client);

    String deleteClient(Integer clientId);
    Client findClientById(Integer id);

}
