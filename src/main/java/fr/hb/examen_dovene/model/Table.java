package fr.hb.examen_dovene.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
@jakarta.persistence.Table(name = "table_reservation")
public class Table {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifiant;

    @NotNull(message = "Le nombre de places ne peut pas être null")
    private int nbrPlace;

    @NotBlank(message = "Le statut de la table ne peut pas être vide")
    private String statut;

    @OneToMany(mappedBy = "tableReserve")
    private List<Reservation> reservations;
}
