package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserDetailService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;


@Controller
@RequestMapping("user")
public class UserController {
    private final UserDetailService userDetailService;
    private final UserServiceImpl userService;

    public UserController(UserDetailService userDetailService, UserServiceImpl userService) {
        this.userDetailService = userDetailService;
        this.userService = userService;
    }

    @GetMapping
    public String showUser(Model model, Principal principal){
        model.addAttribute("user",userService.findUserByName(principal.getName()));
        return "user";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getId(id));
        return "user";
    }


//    @GetMapping("/")
//    public String showAll(Model model) {
//        model.addAttribute("user-list", userService.getUsers());
//        System.out.println(userService.getUsers());
//        return "user-list";
//    }
//
//    @GetMapping("/add")
//    public String getUser() {
//        return "/add";
//    }

//    @PostMapping("/add")
//    public String add(@ModelAttribute("addUser") User user) {
//        userService.saveUser(user);
//        return "redirect:/";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edit(@PathVariable("id") int id, Model model) {
//        model.addAttribute("edit", userService.getUserById(id));
//        return "/edit";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String update(@ModelAttribute("edit") User user) {
//        userService.updateUser(user);
//        return "redirect:/";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String delete(@PathVariable("id") int id) {
//        User user = userService.getUserById(id);
//        userService.deleteUser(user);
//        return "redirect:/";
//    }
}
