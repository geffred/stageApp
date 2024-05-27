package com.stage.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.stage.app.Entity.Inscription;
import com.stage.app.Entity.Stage;
import com.stage.app.Repository.InscriptionRepository;
import com.stage.app.Repository.StageRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StageController {

    @Autowired
    private StageRepository stageRepository;
    @Autowired
    private InscriptionRepository inscriptionRepository;
    private String errorMessage = "";

    @GetMapping("/stages")
    public String getStage() {
        return "stage/stagePage";
    }

    @GetMapping("/stageRegistry")
    public String getStageRegistryFom(Model model) {
        model.addAttribute("stage", new Stage());
        return "stage/stageRegistryForm";
    }

    @GetMapping("/stageList")
    public String getStageList(Model model) {
        model.addAttribute("stageList", stageRepository.findAll());
        model.addAttribute("errorMessage", errorMessage);
        errorMessage = "";
        return "stage/stageList";
    }

    @GetMapping("/stageDelete/{id}")
    public String getStageDelete(@PathVariable Integer id) {

        List<Inscription> inscription = inscriptionRepository.findByStageId(id);

        if (!inscription.isEmpty()) {
            errorMessage = "Ce Stage appartient déjà à une inscription !";
        } else {
            stageRepository.deleteById(id);
            errorMessage = "";
        }
        return "redirect:/stageList";
    }

    @GetMapping("/stageEdit/{id}")
    public String getStageEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("stage", stageRepository.findById(id));
        return "stage/stageRegistryForm";
    }

    @PostMapping("/stageRegistryForm")
    public String postStageRegistryForm(@Valid Stage stage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "stage/stageRegistryForm";
        }

        if (stage.getAgeMin() > stage.getAgeMax()) {
            model.addAttribute("ageError", "L'age max doit être supérieur à l'age min !");
            return "stage/stageRegistryForm";
        }

        if (stage.getDateDeb().isAfter(stage.getDateFin())) {

            model.addAttribute("dateError", "la date de début doit être avant la date de fin !");
            return "stage/stageRegistryForm";
        }

        model.addAttribute("ageError", "");
        model.addAttribute("dateError", "");
        stageRepository.save(stage);
        return "redirect:/stageList";
    }

}
