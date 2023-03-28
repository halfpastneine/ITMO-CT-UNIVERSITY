package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.DisablesUserCredentials;
import ru.itmo.wp.service.UserService;

import javax.validation.Valid;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model) {
        model.addAttribute("users", userService.findAll());
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String setAccess(@Valid DisablesUserCredentials disablesUserCredentials) {
        if (userService.findById(disablesUserCredentials.getUserId()) == null) {
            return "redirect:/users/all";
        }
        if (!disablesUserCredentials.getAccess().equals("Enable") && !disablesUserCredentials.getAccess().equals("Disable"))
            return "redirect:/users/all";
        userService.changeAccess(disablesUserCredentials.getAccess().equals("Enable"), disablesUserCredentials.getUserId());
        return "redirect:/users/all";
    }
}
