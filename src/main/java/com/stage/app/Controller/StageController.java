package com.stage.app.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StageController {
    @GetMapping("/stages")
    public String getStage() {
        return "stage/stagePage";
    }
}
