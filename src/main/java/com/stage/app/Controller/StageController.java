package com.stage.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

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

    @PostMapping("/stageRegistryForm")
    public String postStageRegistryForm(@Valid Stage stage, BindingResult result) {
        if (result.hasErrors()) {
            return "stage/stageRegistryForm";
        }
        stageRepository.save(stage);
        return "redirect:/";
    }

}
