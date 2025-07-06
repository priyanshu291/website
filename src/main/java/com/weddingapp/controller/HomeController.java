package com.weddingapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // This maps the root URL ("/") to index.html
    @GetMapping("/")
    public String index() {
        return "index"; // Loads index.html from templates folder
    }
}
