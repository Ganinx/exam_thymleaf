package fr.hb.examen_dovene.service;

import fr.hb.examen_dovene.model.Reservation;
import fr.hb.examen_dovene.repository.ClientRepository;
import fr.hb.examen_dovene.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Create
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Read One
    public Optional<Reservation> findById(Long id) {
        return reservationRepository.findById(id);
    }

    // Read All
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    // Read All with pagination
    public Page<Reservation> findAll(Pageable pageable) {
        return reservationRepository.findAll(pageable);
    }

    // Update
    public Reservation update(Long id, Reservation reservation) {
        if (reservationRepository.existsById(id)) {
            reservation.setIdentifiant(id);
            return reservationRepository.save(reservation);
        }
        return null;
    }

    // Delete
    public void delete(Long id) {
        reservationRepository.deleteById(id);
    }

    // Count
    public long count() {
        return reservationRepository.count();
    }

    public List<Reservation> findReservationsForToday() {
        // Récupérer la date actuelle sans l'heure
        LocalDate today = LocalDate.now();

        // Créer les bornes de temps pour le jour (de 00:00 à 23:59)
        LocalDateTime startOfDay = today.atStartOfDay(); // 00:00 du jour
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX); // 23:59 du jour

        // Rechercher les réservations qui sont comprises entre le début et la fin de la journée
        List<Reservation> reservations = reservationRepository.findByDateBetween(startOfDay, endOfDay);

        // Formatter la date et l'heure pour chaque réservation
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Convertir les réservations avec leurs dates formatées
        reservations.forEach(reservation -> {
            String formattedDate = reservation.getDate().format(dateFormatter);
            String formattedTime = reservation.getDate().format(timeFormatter);

            reservation.setFormattedDate(formattedDate);  // Assurez-vous d'avoir un setter ou une propriété pour cela
            reservation.setFormattedTime(formattedTime);  // Assurez-vous d'avoir un setter ou une propriété pour cela
        });

        return reservations;
    }

}
