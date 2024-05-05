package com.stage.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InscriptionController {

    @GetMapping("/inscriptions")
    public String getInscription() {
        return "inscription/inscriptionPage";
    }
}
