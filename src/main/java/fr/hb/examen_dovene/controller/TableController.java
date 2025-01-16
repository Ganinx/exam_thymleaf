package fr.hb.examen_dovene.controller;

import fr.hb.examen_dovene.model.Table;
import fr.hb.examen_dovene.service.TableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private TableService tableService;

    // Afficher la liste des tables
    @GetMapping
    public String listTables(Model model) {
        model.addAttribute("tables", tableService.findAll());
        return "table/list";
    }

    // Afficher le formulaire pour créer une nouvelle table
    @GetMapping("/add")
    public String showCreateForm(Model model) {
        model.addAttribute("table", new Table());
        return "table/form";
    }

    // Créer une nouvelle table
    @PostMapping
    public String createTable(@Valid @ModelAttribute("table") Table table, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "table/form"; // Renvoyer à la vue avec les erreurs
        }
        tableService.save(table);
        redirectAttributes.addFlashAttribute("message", "Table créée avec succès !");
        return "redirect:/tables";
    }

    // Afficher le formulaire pour modifier une table existante
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Table table = tableService.findById(id).orElse(null);
        if (table == null) {
            redirectAttributes.addFlashAttribute("error", "Table introuvable !");
            return "redirect:/tables";
        }
        model.addAttribute("table", table);
        return "table/form";
    }

    // Mettre à jour une table existante
    @PostMapping("/{id}")
    public String updateTable(@PathVariable Long id, @Valid @ModelAttribute("table") Table table, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "table/form"; // Renvoyer à la vue avec les erreurs
        }
        tableService.update(id, table);
        redirectAttributes.addFlashAttribute("message", "Table mise à jour avec succès !");
        return "redirect:/tables";
    }

    // Supprimer une table
    @GetMapping("/delete/{id}")
    public String deleteTable(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        tableService.delete(id);
        redirectAttributes.addFlashAttribute("message", "Table supprimée avec succès !");
        return "redirect:/tables";
    }
}
