package com.stage.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnfantController {

    @GetMapping("/enfants")
    public String getEnfant() {
        return "enfant/enfantPage";
    }

}
