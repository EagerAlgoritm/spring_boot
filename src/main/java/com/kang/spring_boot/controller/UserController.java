package com.kang.spring_boot.controller;

import com.kang.spring_boot.model.User;
import com.kang.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String welcomePage() {
        return "Welcome";
    }

    @RequestMapping("/access_denied")
    public String accessPage() {
        return "Denied";
    }

    @GetMapping(value = "/user")
    public String getUserPage(Model model) {
        UserDetails user = (UserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        model.addAttribute("user", userService.getUserByName(user.getUsername()));
        return "User";
    }


    @GetMapping("/admin/getAll")
    public String showAllUsers(Model model) {
        List<User> userList = userService.GetAllUsers();
        model.addAttribute("userList", userList);

        return "allUsers";
    }

    @GetMapping("/admin/addNewUser")
    public String addNewUser(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "userForm";
    }

    @PostMapping(value = "/admin/saveUser")
    public String saveUser(@ModelAttribute("User") User user) {

        userService.saveUser(user);

        return "redirect:/admin/getAll";
    }

    @GetMapping("/admin/update")
    public String updateUser(@RequestParam("userId") int id, Model model) {

        User user = userService.getUser(id);
        model.addAttribute("user", user);

        return "userForm";
    }

    @RequestMapping("/admin/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {

        userService.deleteUser(id);

        return "redirect:/admin/getAll";
    }
}
