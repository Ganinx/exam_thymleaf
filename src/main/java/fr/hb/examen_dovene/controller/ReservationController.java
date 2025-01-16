package fr.hb.examen_dovene.controller;

import fr.hb.examen_dovene.model.Client;
import fr.hb.examen_dovene.model.Reservation;
import fr.hb.examen_dovene.model.Reservation;
import fr.hb.examen_dovene.model.Table;
import fr.hb.examen_dovene.service.ClientService;
import fr.hb.examen_dovene.service.ReservationService;
import fr.hb.examen_dovene.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private TableService tableService;

    // Afficher la liste des reservations
    @GetMapping
    public String listClients(Model model) {
        List<Reservation> reservations = reservationService.findAll();
        model.addAttribute("reservations", reservations);
        return "reservation/list"; // Vue Thymeleaf pour afficher la liste
    }

    // Afficher le formulaire pour créer une nouvelle reservation
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        Reservation reservation = new Reservation();
        reservation.setClient(new Client());  // Initialiser le client
        reservation.setTableReserve(new Table());  // Initialiser la table

        List<Client> clients = clientService.findAll();
        List<Table> tables = tableService.findAll();

        model.addAttribute("reservation", reservation);
        model.addAttribute("clients", clients);
        model.addAttribute("tables", tables);
        return "reservation/form";
    }

    // Créer une nouvelle reservation
    @PostMapping
    public String createClient(@Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            List<Client> clients = clientService.findAll();
            List<Table> tables = tableService.findAll();
            model.addAttribute("clients", clients);
            model.addAttribute("tables", tables);
            return "reservation/form";
        }

        reservationService.save(reservation);
        redirectAttributes.addFlashAttribute("message", "Reservation créée avec succès !");
        return "redirect:/reservations";
    }

    // Afficher le formulaire pour modifier une reservation existante
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.findById(id).orElse(null);
        List<Client> clients = clientService.findAll();
        List<Table> tables = tableService.findAll();
        if (reservation == null) {
            redirectAttributes.addFlashAttribute("error", "Reservation introuvable !");
            return "redirect:/reservations";
        }
        model.addAttribute("clients", clients);
        model.addAttribute("tables", tables);
        model.addAttribute("reservation", reservation);
        return "reservation/form"; // Réutilisation de la vue du formulaire
    }

    // Mettre à jour une reservation existante
    @PostMapping("/{id}")
    public String updateClient(@PathVariable Long id, @Valid @ModelAttribute("reservation") Reservation reservation, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        if (bindingResult.hasErrors()) {
            // Si des erreurs de validation, recharger les clients et tables
            List<Client> clients = clientService.findAll();
            List<Table> tables = tableService.findAll();
            model.addAttribute("clients", clients);
            model.addAttribute("tables", tables);
            return "reservation/form"; // Revenir à la vue du formulaire
        }

        Reservation updatedReservation = reservationService.update(id, reservation);
        if (updatedReservation == null) {
            redirectAttributes.addFlashAttribute("error", "Erreur lors de la mise à jour de la reservation !");
            return "redirect:/reservations";
        }
        redirectAttributes.addFlashAttribute("message", "Reservation mise à jour avec succès !");
        return "redirect:/reservations";
    }

    // Supprimer une reservation
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        reservationService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Reservation supprimée avec succès !");
        return "redirect:/reservations";
    }
}
