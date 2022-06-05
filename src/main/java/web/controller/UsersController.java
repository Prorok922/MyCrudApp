package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserService;

import java.util.Optional;

@Controller
public class UsersController {
    private UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping(value = "/")
    public String printUsers(@RequestParam(value = "count", required = false) Optional<Integer> count, Model model) {
        model.addAttribute("messages", userService.listUsers());
        return "users";
    }

//    @GetMapping("/")
//    public String getListUser(Model model){
//        //получим всех людей из DAO и передадим их на представление
//        model.addAttribute("users", userService.listUsers());
//        return "users";
//    }

//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable("id") int id, Model model){
//        //получим одного человека по id
//        model.addAttribute("user", userService.getUserById(id));
//        return "user";
//    }
}
