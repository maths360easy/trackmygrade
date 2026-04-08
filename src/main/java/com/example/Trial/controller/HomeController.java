package com.example.Trial.controller;

  

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";  // loads home.html from templates folder
    }


   

}