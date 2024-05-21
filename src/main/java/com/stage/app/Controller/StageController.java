package com.stage.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.stage.app.Entity.Stage;
import com.stage.app.Repository.StageRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StageController {

    @Autowired
    private StageRepository stageRepository;

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
        return "stage/stageList";
    }

    @GetMapping("/stageDelete/{id}")
    public String getStageDelete(@PathVariable Integer id) {
        stageRepository.deleteById(id);
        return "redirect:/stageList";
    }

    @GetMapping("/stageEdit/{id}")
    public String getStageEdit(@PathVariable Integer id, Model model) {
        model.addAttribute("stage", stageRepository.findById(id));
        return "stage/stageRegistryForm";
    }

    @PostMapping("/stageRegistryForm")
    public String postStageRegistryForm(@Valid Stage stage, BindingResult result) {
        if (result.hasErrors()) {
            return "stage/stageRegistryForm";
        }
        stageRepository.save(stage);
        return "redirect:/";
    }

}
