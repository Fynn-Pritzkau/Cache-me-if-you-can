package de.bbk.cachemeifyoucan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @GetMapping("/welcome")
    public String welcome() {
        return "redirect:http://localhost:5173/Welcome"; // Frontend URL
    }
}