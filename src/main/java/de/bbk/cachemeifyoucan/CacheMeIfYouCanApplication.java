package de.bbk.cachemeifyoucan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CacheMeIfYouCanApplication
{
    @RequestMapping("/login")
    public String welcome()
    {
        return "Welcome, how are you";
    }

    public static void main(String[] args)
    {
        SpringApplication.run(CacheMeIfYouCanApplication.class, args);
    }

}
