package com.adm.usersmanager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adm.usersmanager.model.User;
import com.adm.usersmanager.repository.UserRepository;

@Controller
@RequestMapping("/")
public class UsersController<ModeAndView> {

    // @DateTimeFormat(iso = ISO.DATE) for date format
    // Use also LocalDate type

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ModelAndView listUsers() {
        ModelAndView modelAndView = new ModelAndView("users.html");

        List<User> users = userRepository.findAll();
        modelAndView.addObject("users", users);

        return modelAndView;
    }    
    
    @GetMapping("/{id}")
    public ModelAndView detail(@PathVariable Long id) {
        ModelAndView modelAndView= new ModelAndView("user.html");

        Optional<User> user = userRepository.findById(id);
        modelAndView.addObject("user", user);

        return modelAndView;
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable Long id) {
        ModelAndView modelAndView= new ModelAndView("redirect: users.html");

        userRepository.deleteById(id);

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("user/register");

        modelAndView.addObject("user", new User());
        
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(User user) {
        ModelAndView modelAndView = new ModelAndView("redirect: /user");

        userRepository.save(user);

        return modelAndView;
    }
}