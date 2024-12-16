package com.rushil.Memoir.controller;

import com.rushil.Memoir.cache.AppCache;
import com.rushil.Memoir.entity.User;
import com.rushil.Memoir.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUser() {
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody User user)
    {
        userService.saveNewAdmin(user);
    }

    @GetMapping("/clear-app-cache")
    public void clearAppCache()
    {
        appCache.init();
    }
}
