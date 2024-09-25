package com.rushil.Memoir.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/h-chcek")
    public String healthCheck()
    {
        return "ok it's working";
    }
}
