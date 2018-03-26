package org.softuni.residentevil.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public ModelAndView home(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping("/unauthorized")
    public ModelAndView unauthorized(ModelAndView modelAndView) {
        modelAndView.setViewName("unauthorized");
        return modelAndView;
    }
}