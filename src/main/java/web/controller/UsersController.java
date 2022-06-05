package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/")
    public String printUsers(ModelMap model) {
        model.addAttribute(userService.getAllUsers());
        return "user";
    }

    @PostMapping
    public String add(@ModelAttribute("user") User user,
                      @RequestParam("name") String name,
                      @RequestParam("lastName") String lastName,
                      @RequestParam("email") String email) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") Long id) {
        model.addAttribute("user", userService.getUserById(id));
        return "editUser";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") Long id) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
