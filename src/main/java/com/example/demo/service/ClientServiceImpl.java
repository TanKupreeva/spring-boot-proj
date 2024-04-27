package com.example.demo.service;

import com.example.demo.model.Client;
import com.example.demo.repository.ClientRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Resource
    private ClientRepository clientRepository;

    @Override
    public String addClient(Client client) {
        return clientRepository.save(client) != null ? "Заказчик добавлен успешно" : "Заказчик не добавлен!";
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }





    @Override
    public Client findClientById(Integer id) {
//        List<Client> clients = getAllClients();
//        for (Client c : clients) {
//            if (c.getId().equals(id))
//                return c;
//        }
//        return null;
        return clientRepository.findClientById(id);
    }
    @Override
    public String  updateClient(Integer id, Client client) {
        Client c = findClientById(id);
        clientRepository.save(client);
        return c != null ? "Заказчик изменен успешно" : "Заказчик не изменен!";
    }

    @Override
    public String deleteClient(Integer id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            clientRepository.deleteById(id);
            return "Заказчик удален успешно!";
        }
        return "Заказчик не удален!";
    }

}
