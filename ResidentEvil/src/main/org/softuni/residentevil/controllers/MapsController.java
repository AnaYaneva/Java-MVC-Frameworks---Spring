package org.softuni.residentevil.controllers;

import org.softuni.residentevil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MapsController {
    private VirusService virusService;

    public MapsController(VirusService virusService) {
        this.virusService = virusService;
    }

    @GetMapping("/map")
    public ModelAndView map(ModelAndView modelAndView) {
        String geoJson = this.virusService.getMap();

        modelAndView.setViewName("map");
        modelAndView.addObject("geoJson", geoJson);

        return modelAndView;
    }
}
