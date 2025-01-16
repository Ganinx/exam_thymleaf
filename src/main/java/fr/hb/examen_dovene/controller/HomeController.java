package fr.hb.examen_dovene.controller;

import fr.hb.examen_dovene.model.Reservation;
import fr.hb.examen_dovene.service.ReservationService;
import fr.hb.examen_dovene.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private TableService tableService;

    @GetMapping
    public String home(Model model) {
        // Récupérer les réservations du jour
        LocalDateTime today = LocalDateTime.now();
        List<Reservation> todayReservations = reservationService.findReservationsForToday();

        // Récupérer le nombre total de tables
        long totalTables = tableService.count("disponible");

        // Calculer le nombre de tables disponibles
        long tablesReserved = todayReservations.size();

        // Ajouter les données au modèle
        model.addAttribute("reservations", todayReservations);
        model.addAttribute("availableTables", totalTables);;

        return "index"; // Vue Thymeleaf pour la page d'accueil
    }

}
