package fr.hb.examen_dovene.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long identifiant;

    @NotBlank(message = "Le nom ne peut pas être vide")
    private String nom;

    @NotBlank(message = "Le prénom ne peut pas être vide")
    private String prenom;

    @NotBlank(message = "L'email ne peut pas être vide")
    private String email;

    @NotBlank(message = "Le téléphone ne peut pas être vide")
    private String telephone;

    @NotBlank(message = "Le genre ne peut pas être vide")
    private String genre;

    @NotNull(message = "Le statut VIP doit être défini")
    private boolean vip;

    @OneToMany(mappedBy = "client")
    private List<Reservation> reservations;

}
