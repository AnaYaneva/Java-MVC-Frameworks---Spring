package org.softuni.residentevil.controllers;

import org.softuni.residentevil.entities.Capital;
import org.softuni.residentevil.entities.Virus;
import org.softuni.residentevil.models.AddVirusBindingModel;
import org.softuni.residentevil.models.EditVirusBindingModel;
import org.softuni.residentevil.services.CapitalService;
import org.softuni.residentevil.services.VirusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class VirusController {
    private VirusService virusService;
    private CapitalService capitalService;

    @Autowired
    public VirusController(CapitalService capitalService, VirusService virusService) {
        this.capitalService = capitalService;
        this.virusService = virusService;
    }

    @GetMapping("/viruses/add")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ModelAndView addVirus(ModelAndView modelAndView, @ModelAttribute("virus") AddVirusBindingModel bindingModel) {
        modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
        modelAndView.setViewName("viruses/add");

        return modelAndView;
    }

    @PostMapping("/viruses/add")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ModelAndView addVirusConfirm(ModelAndView modelAndView, @Valid @ModelAttribute("virus") AddVirusBindingModel bindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
            modelAndView.setViewName("viruses/add");

            return modelAndView;
        }

        this.virusService.save(bindingModel);
        modelAndView.setViewName("redirect:/viruses/show");

        return modelAndView;
    }

    @GetMapping("/viruses/show")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ModelAndView showAllViruses(ModelAndView modelAndView) {
        modelAndView.addObject("viruses", this.virusService.findAllViruses());
        modelAndView.setViewName("viruses/show");

        return modelAndView;
    }

    @GetMapping("/viruses/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ModelAndView deleteVirus(ModelAndView modelAndView, @PathVariable String id) {
        this.virusService.deleteById(id);
        modelAndView.setViewName("redirect:/viruses/show");

        return modelAndView;
    }

    @GetMapping("/viruses/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ModelAndView editVirus(ModelAndView modelAndView, @PathVariable String id, @ModelAttribute("virus") EditVirusBindingModel bindingModel) {
        Virus virus = this.virusService.findVirusById(id);

        if (virus == null) {
            modelAndView.setViewName("redirect:/viruses/show");
            return modelAndView;
        }

        bindingModel.setName(virus.getName());
        bindingModel.setDescription(virus.getDescription());
        bindingModel.setSideEffect(virus.getSideEffects());
        bindingModel.setCreator(virus.getCreator());

        if (virus.isDeadly()) {
            bindingModel.setIsDeadly("on");
        }

        if (virus.isCurable()) {
            bindingModel.setIsCurable("on");
        }

        bindingModel.setMutation(virus.getMutation().name());
        bindingModel.setTurnoverRate(virus.getTurnoverRate());
        bindingModel.setHoursUntilTurn(virus.getHoursUntilTurn());
        bindingModel.setMagnitude(virus.getMagnitude().name().substring(0, 1) + virus.getMagnitude().name().toLowerCase().substring(1));
        bindingModel.setAffectedCapitals(new ArrayList<>());

        for (Capital capital : virus.getCapitals()) {
            bindingModel.getAffectedCapitals().add(capital.getName());
        }

        modelAndView.addObject("id", id);
        modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
        modelAndView.setViewName("viruses/edit");
        return modelAndView;
    }

    @PostMapping("/viruses/edit/{id}")
    @PreAuthorize("hasAnyRole('ROLE_MODERATOR', 'ROLE_ADMIN')")
    public ModelAndView editVirusConfirm(ModelAndView modelAndView, @Valid @ModelAttribute("virus") EditVirusBindingModel bindingModel, BindingResult bindingResult, @PathVariable String id) {
        if (bindingResult.hasErrors()) {
            modelAndView.addObject("capitals", this.capitalService.getAllCapitals());
            modelAndView.setViewName("viruses/edit");

            return modelAndView;
        }

        this.virusService.edit(id, bindingModel);
        modelAndView.setViewName("redirect:/viruses/show");

        return modelAndView;
    }
}