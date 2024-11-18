package com.rushil.Memoir.controller;

import com.rushil.Memoir.entity.User;
import com.rushil.Memoir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @GetMapping("/h-chcek")
    public String healthCheck()
    {
        return "ok it's working";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user)
    {
        userService.saveEntry(user);
    }
}
