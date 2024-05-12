package com.daw.swapp.controller.users;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.daw.swapp.model.users.User;
import com.daw.swapp.service.users.UsersService;
import com.daw.swapp.service.usersJPA.UsersJPAService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsersController {
    private final UsersService usersService;
    private final UsersJPAService usersJPAService;

    public UsersController(UsersService usersService, UsersJPAService usersJPAService) {
        this.usersJPAService = usersJPAService;
        this.usersService = usersService;
    }

    // use no ORM accerss
    @GetMapping("/users")
    public String showUsers(Model model) {
        List<User> users = usersService.listUsers();
        model.addAttribute("users", users);
        return "users/home";
    }

    // use JPA ORM access
    @GetMapping("/usersjpa")
    public String showUsersJPA(Model model) {
        List<User> users = usersJPAService.listUsers();
        model.addAttribute("users", users);
        return "users/home";
    }

    @PostMapping("/user")
    public String addUser(@ModelAttribute User user, HttpServletRequest request) {
        usersService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        boolean deleted = usersService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
