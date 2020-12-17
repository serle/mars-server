package com.lme.marsexplorer;

import com.lme.marsexplorer.core.InputPacket;
import com.lme.marsexplorer.core.OutputPacket;
import com.lme.marsexplorer.core.Planet;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanetaryController {

    @RequestMapping("/mars")
    public String execute(@RequestParam(value = "command") String command) {
        try {

            //InputPacket input = new InputPacket(command);
            //OutputPacket output = Planet.execute(input);
            return "MySerle";
            //return output.toString();
        }
        catch(Exception e) {
            return e.toString();
        }
    }
}