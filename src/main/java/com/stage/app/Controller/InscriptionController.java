package com.stage.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/inscriptionList")
    public String getInscriptionList(Model model) {
        model.addAttribute("inscriptionList", inscriptionRepository.findAll());
        return "inscription/inscriptionList";
    }

    @GetMapping("/inscriptionDelete/{id}")
    public String getInscriptionDelete(@PathVariable Integer id) {
        inscriptionRepository.deleteById(id);
        return "redirect:/inscriptionList";
    }

    @GetMapping("/inscriptionEdit/{id}")
    public String getInscriptionEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("enfants", enfantRepository.findAll());
        model.addAttribute("stages", stageRepository.findAll());
        model.addAttribute("inscription", inscriptionRepository.findById(id));
        return "inscription/inscriptionRegistryForm";
    }

    @GetMapping("/inscriptionFilter")
    public String getInscriptionFilter(@RequestParam(name = "denom", defaultValue = "") String denom,
            @RequestParam(name = "paye", defaultValue = "") String paye, Model model) {

        model.addAttribute("denom", denom);
        model.addAttribute("paye", paye);
        if (denom != "") {
            model.addAttribute("inscriptionList", inscriptionRepository.findByStageDenom(denom));

            if (paye.equals("true") || paye.equals("false")) {
                model.addAttribute("inscriptionList",
                        inscriptionRepository.findByInscription(denom, Boolean.parseBoolean(paye)));
                return "inscription/inscriptionList";
            }
        }

        return "inscription/inscriptionList";

    }

    @PostMapping("/inscriptionRegistryForm")
    public String postRegistryForm(@Valid Inscription inscription) {

        inscriptionRepository.save(inscription);
        return "redirect:/";
    }
}
