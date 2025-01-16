package fr.hb.examen_dovene.controller;

import fr.hb.examen_dovene.model.Client;
import fr.hb.examen_dovene.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Afficher la liste des clients
    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("clients", clientService.findAll());
        return "client/list";
    }

    // Afficher le formulaire pour créer une nouvelle client
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("client", new Client());
        return "client/form";
    }

    // Créer une nouvelle client
    @PostMapping
    public String createClient(@Valid @ModelAttribute("client") Client client, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "client/form"; // Renvoyer à la vue avec les erreurs
        }
        clientService.save(client);
        redirectAttributes.addFlashAttribute("message", "Client créée avec succès !");
        return "redirect:/clients";
    }

    // Afficher le formulaire pour modifier une client existante
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Client client = clientService.findById(id).orElse(null);
        if (client == null) {
            redirectAttributes.addFlashAttribute("error", "Client introuvable !");
            return "redirect:/clients";
        }
        model.addAttribute("client", client);
        return "client/form";
    }

    // Mettre à jour une client existante
    @PostMapping("/{id}")
    public String updateClient(@PathVariable Long id, @Valid @ModelAttribute("client") Client client, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "client/form"; // Renvoyer à la vue avec les erreurs
        }
        clientService.update(id, client);
        redirectAttributes.addFlashAttribute("message", "Client mise à jour avec succès !");
        return "redirect:/clients";
    }

    // Supprimer une client
    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        clientService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Client supprimée avec succès !");
        return "redirect:/clients";
    }
}
