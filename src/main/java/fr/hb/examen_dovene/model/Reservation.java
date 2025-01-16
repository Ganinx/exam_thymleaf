package fr.hb.examen_dovene.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifiant;

    @ManyToOne
    private Table tableReserve;

    @ManyToOne
    private Client client;

    @NotNull(message = "La date de réservation ne peut pas être null")
    private LocalDateTime date;


    private boolean validCapacity() {
        return tableReserve == null || nbrCouvert <= tableReserve.getNbrPlace();
    }


    @Min(value = 1, message = "Le nombre de couverts doit être au moins 1")
    private int nbrCouvert;

    private String formattedDate;
    private String formattedTime;

}

