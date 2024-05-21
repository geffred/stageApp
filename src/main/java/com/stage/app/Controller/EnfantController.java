package com.stage.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.stage.app.Entity.Enfant;
import com.stage.app.Entity.Inscription;
import com.stage.app.Repository.EnfantRepository;
import com.stage.app.Repository.InscriptionRepository;

import jakarta.validation.Valid;

@Controller
public class EnfantController {

    @Autowired
    EnfantRepository enfantRepository;
    @Autowired
    InscriptionRepository inscriptionRepository;
    private String errorMessage = "";

    @GetMapping("/enfants")
    public String getEnfant() {
        return "enfant/enfantPage";
    }

    @GetMapping("/enfantRegistry")
    public String getEnfantRegisty(Model Model) {

        Model.addAttribute("enfant", new Enfant());

        return "enfant/enfantRegistryForm";
    }

    @GetMapping("/enfantList")
    public String getEnfantList(Model model) {

        model.addAttribute("enfantList", enfantRepository.findAll());
        model.addAttribute("errorMessage", errorMessage);
        errorMessage = "";
        return "enfant/enfantList";
    }

    @GetMapping("/enfantDelete/{id}")
    public String getEnfantDelete(@PathVariable Integer id) {

        List<Inscription> inscription = inscriptionRepository.findByEnfantId(id);

        if (!inscription.isEmpty()) {
            errorMessage = "Cet enfant appartient déjà à une inscription !";
        } else {
            enfantRepository.deleteById(id);
        }
        return "redirect:/enfantList";
    }

    @GetMapping("/enfantEdit/{id}")
    public String getEnfantEdit(@PathVariable Integer id, Model model) {

        model.addAttribute("enfant", enfantRepository.findById(id));
        return "enfant/enfantRegistryForm";
    }

    @PostMapping("/enfantRegistryForm")
    public String getEnfantRegistyForm(@Valid Enfant enfant, BindingResult result) {

        if (result.hasErrors()) {

            return "enfant/enfantRegistryForm";
        }
        enfantRepository.save(enfant);
        return "redirect:/enfantList";
    }

}
