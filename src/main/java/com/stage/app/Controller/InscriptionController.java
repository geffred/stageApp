package com.stage.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.stage.app.Entity.Inscription;
import com.stage.app.Repository.EnfantRepository;
import com.stage.app.Repository.InscriptionRepository;
import com.stage.app.Repository.StageRepository;

import jakarta.validation.Valid;

@Controller
public class InscriptionController {

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private EnfantRepository enfantRepository;
    @Autowired
    private InscriptionRepository inscriptionRepository;

    @GetMapping("/inscriptions")
    public String getInscription() {
        return "inscription/inscriptionPage";
    }

    @GetMapping("/inscriptionRegistry")
    public String getInscriptionRegistry(Model model) {
        model.addAttribute("enfants", enfantRepository.findAll());
        model.addAttribute("stages", stageRepository.findAll());
        model.addAttribute("inscription", new Inscription());
        return "inscription/inscriptionRegistryForm";
    }

    @PostMapping("/inscriptionRegistryForm")
    public String postRegistryForm(@Valid Inscription inscription) {

        inscriptionRepository.save(inscription);
        return "redirect:/";
    }
}
