package com.lme.marsexplorer;

import com.lme.marsexplorer.core.Planet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetaryController {

    public PlanetaryController() {
        Planet.addInstructions();
    }

    @GetMapping("/mars")
    public String instructions() {
        try {
            return Planet.getInstructionSet();
        }
        catch(Exception e) {
            return e.toString();
        }
    }


    @PostMapping("/mars")
    public String execute(@RequestParam(value = "command") String command) {
        try {
            return Planet.execute(command);
        }
        catch(Exception e) {
            return e.toString();
        }
    }
}