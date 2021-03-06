package com.hacker.news.controller;

import com.hacker.news.model.User;
import com.hacker.news.security.UserPrincipal;
import com.hacker.news.service.SecurityService;
import com.hacker.news.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping(value = "/login", method = GET)
    public String loginOrRegister(){
        return "login";
    }

    @RequestMapping(value = "/user", method = POST)
    public String createUser(@RequestParam("username")String username,
                             @RequestParam("password")String password,
                             @RequestParam("email")String email) {
        User user = new User(username,password,email);
        userService.createUser(user);
        return "redirect:/";
    }

    @RequestMapping(value = "/users", method = GET)
    public String getUsersList(Model model){
        boolean isLoggedIn =  userService.isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            UserPrincipal currentUser = userService.currentUser();
            model.addAttribute("username", currentUser.getUsername());
        }
        model.addAttribute("users",userService.getAllUsers());
        return "user-list";
    }

    @RequestMapping(value = "/user/{user}", method = GET)
    public String getUserDetails(@PathVariable("user") String user, Model model){
        isLoggedIn(model);
        model.addAttribute("user",userService.getUserByUserName(user));
        return "user";
    }

    @RequestMapping(value = "/user/self",method = GET)
    public String getSelfDetails(Model model){
        isLoggedIn(model);
        model.addAttribute("user",userService.getUserByUserName(userService.currentUser().getUsername()));
        return "user";
    }

    void isLoggedIn(Model model){
        boolean isLoggedIn =  userService.isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            UserPrincipal currentUser = userService.currentUser();
            model.addAttribute("username", currentUser.getUsername());
        }
    }
}
