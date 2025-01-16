package fr.hb.examen_dovene.service;

import fr.hb.examen_dovene.model.Client;
import fr.hb.examen_dovene.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    // Create
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    // Read One
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    // Read All
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // Read All with pagination
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    // Update
    public Client update(Long id, Client client) {
        if (clientRepository.existsById(id)) {
            client.setIdentifiant(id);
            return clientRepository.save(client);
        }
        return null;
    }

    // Delete
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    // Count
    public long count() {
        return clientRepository.count();
    }

}
