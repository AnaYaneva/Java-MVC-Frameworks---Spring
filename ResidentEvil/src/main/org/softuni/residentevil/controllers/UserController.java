package org.softuni.residentevil.controllers;

import org.softuni.residentevil.entities.User;
import org.softuni.residentevil.models.EditUserBindingModel;
import org.softuni.residentevil.models.LoginUserBindingModel;
import org.softuni.residentevil.models.RegisterUserBindingModel;
import org.softuni.residentevil.services.RoleService;
import org.softuni.residentevil.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView register(ModelAndView modelAndView, @ModelAttribute("user") RegisterUserBindingModel bindingModel) {
        modelAndView.setViewName("user/register");
        return modelAndView;
    }

    @PostMapping("/register")
    @PreAuthorize("isAnonymous()")
    public ModelAndView registerConfirm(ModelAndView modelAndView, @Valid @ModelAttribute("user") RegisterUserBindingModel bindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/register");

            return modelAndView;
        }

        if (!bindingModel.getPassword().equals(bindingModel.getConfirmPassword())) {
            modelAndView.setViewName("user/register");
            modelAndView.addObject("passwordsDoNotMatch", true);

            return modelAndView;
        }

        if (this.userService.userWithUsernameExists(bindingModel.getUsername())) {
            modelAndView.setViewName("user/register");
            modelAndView.addObject("usernameTaken", true);

            return modelAndView;
        }

        if (this.userService.userWithEmailExists(bindingModel.getEmail())) {
            modelAndView.setViewName("user/register");
            modelAndView.addObject("emailTaken", true);

            return modelAndView;
        }

        this.userService.register(bindingModel);
        modelAndView.setViewName("redirect:/login");

        return modelAndView;
    }

    @GetMapping("/login")
    @PreAuthorize("isAnonymous()")
    public ModelAndView login(ModelAndView modelAndView, @ModelAttribute("user") LoginUserBindingModel bindingModel, @RequestParam(required = false, name = "error") String error) {
        if (error != null) {
            modelAndView.addObject("error", error);
        }

        modelAndView.setViewName("user/login");
        return modelAndView;
    }

    @GetMapping("/users/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView all(ModelAndView modelAndView) {
        List<User> allUsers = this.userService.getAllUsers();

        modelAndView.addObject("users", allUsers);
        modelAndView.setViewName("user/all");
        return modelAndView;
    }

    @GetMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView edit(ModelAndView modelAndView, @PathVariable long id, @ModelAttribute("user") EditUserBindingModel bindingModel) {
        User user = this.userService.getUserById(id);

        if (user == null) {
            modelAndView.setViewName("redirect:/users/all");
            return modelAndView;
        }

        bindingModel.setUsername(user.getUsername());
        bindingModel.setEmail(user.getEmail());
        bindingModel.setRoles(new ArrayList<>());

        user.getAuthorities()
                .forEach(a -> bindingModel.getRoles().add(a.getAuthority()));

        modelAndView.setViewName("user/edit");
        modelAndView.addObject("id", id);
        modelAndView.addObject("roles", this.roleService.getAllRoles());
        return modelAndView;
    }

    @PostMapping("/users/edit/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView editConfirm(ModelAndView modelAndView, @PathVariable long id, @Valid @ModelAttribute("user") EditUserBindingModel bindingModel, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("user/edit");
            modelAndView.addObject("roles", this.roleService.getAllRoles());

            return modelAndView;
        }

        this.userService.edit(id, bindingModel);
        modelAndView.setViewName("redirect:/users/all");

        return modelAndView;
    }
}