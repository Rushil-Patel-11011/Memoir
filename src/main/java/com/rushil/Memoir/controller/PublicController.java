package com.rushil.Memoir.controller;

import com.rushil.Memoir.entity.User;
import com.rushil.Memoir.service.UserDetailServiceImpl;
import com.rushil.Memoir.service.UserService;
import com.rushil.Memoir.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@Slf4j
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;


    @GetMapping("/h-chcek")
    public String healthCheck()
    {
        return "ok it's working";
    }
    @PostMapping("/signup")
    public void signUp(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user)
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));
            UserDetails userDetails = userDetailService.loadUserByUsername(user.getUserName());
            String jwt=jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e) {
            log.error("Exception occurred: ", e);
            return new ResponseEntity<>("Invalid username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
