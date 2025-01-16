package fr.hb.examen_dovene.service;

import fr.hb.examen_dovene.model.Table;
import fr.hb.examen_dovene.repository.ClientRepository;
import fr.hb.examen_dovene.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService {

    @Autowired
    private TableRepository tableRepository;

    // Create
    public Table save(Table table) {
        return tableRepository.save(table);
    }

    // Read One
    public Optional<Table> findById(Long id) {
        return tableRepository.findById(id);
    }

    // Read All
    public List<Table> findAll() {
        return tableRepository.findAll();
    }

    // Read All with pagination
    public Page<Table> findAll(Pageable pageable) {
        return tableRepository.findAll(pageable);
    }

    // Update
    public Table update(Long id, Table table) {
        if (tableRepository.existsById(id)) {
            table.setIdentifiant(id);
            return tableRepository.save(table);
        }
        return null;
    }

    // Delete
    public void delete(Long id) {
        tableRepository.deleteById(id);
    }

    // Count
    public long count(String statut) {
        return tableRepository.countByStatut(statut);
    }


}
