package com.lme.marsexplorer;

import com.lme.marsexplorer.core.Planet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetaryController {

    @RequestMapping("/mars")
    public String execute(@RequestParam(value = "command") String command) {
        try {
            return Planet.execute(command);
        }
        catch(Exception e) {
            return e.toString();
        }
    }
}