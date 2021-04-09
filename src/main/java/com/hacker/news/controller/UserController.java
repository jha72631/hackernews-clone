package com.hacker.news.controller;

import com.hacker.news.model.User;
import com.hacker.news.service.SecurityService;
import com.hacker.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    private final UserService userService;
    private final SecurityService securityService;

    @Autowired
    public UserController(UserService userService,
                          SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @RequestMapping(value = "/user", method = GET)
    public String loginOrRegister(){
        return "login";
    }

    @RequestMapping(value = "/user", method = POST)
    public String createUser(@RequestParam("username")String username,
                             @RequestParam("password")String password,
                             @RequestParam("email")String email) {
        System.out.println("inside controller");
        User user = new User(username,password,email);
        userService.createUser(user);
        return "redirect:/";
    }
}
