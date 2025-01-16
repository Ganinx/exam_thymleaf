package fr.hb.examen_dovene.repository;

import fr.hb.examen_dovene.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepository extends JpaRepository<Table,Long> {
    long countByStatut(String statut);
}
