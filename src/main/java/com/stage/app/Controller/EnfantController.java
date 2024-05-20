package com.stage.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.stage.app.Entity.Enfant;
import com.stage.app.Repository.EnfantRepository;

import jakarta.validation.Valid;

@Controller
public class EnfantController {

    @Autowired
    EnfantRepository enfantRepository;

    @GetMapping("/enfants")
    public String getEnfant() {
        return "enfant/enfantPage";
    }

    @GetMapping("/enfantRegistry")
    public String getEnfantRegisty(Model Model) {

        Model.addAttribute("enfant", new Enfant());

        return "enfant/enfantRegistryForm";
    }

    @PostMapping("/enfantRegistryForm")
    public String getEnfantRegistyForm(@Valid Enfant enfant, BindingResult result) {

        if (result.hasErrors()) {

            return "enfant/enfantRegistryForm";
        }
        enfantRepository.save(enfant);
        return "redirect:/";
    }

}
