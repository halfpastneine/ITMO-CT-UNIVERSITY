package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itmo.wp.service.UserService;

@Controller
public class UserPage extends Page{

    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"/user/{id}", "/user/"})
    public String user(@PathVariable (required = false) String id, Model model) {
        if (id == null) {
            return "UserPage";
        }
        try {
            long userId = Long.parseLong(id);
            model.addAttribute("user", userService.findById(userId));
        } catch (NumberFormatException e) {
            return "UserPage";
        }
        return "UserPage";
    }
}
