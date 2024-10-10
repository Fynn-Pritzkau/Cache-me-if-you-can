package de.bbk.cachemeifyoucan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CacheMeIfYouCanApplication {

    @GetMapping("/login")
    public String login() {
        return "redirect:http://localhost:5173/"; // Frontend URL
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:http://localhost:5173/"; // Frontend URL
    }

    @GetMapping("/checkAuth")
    public ResponseEntity<Void> checkAuth() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<OidcUser> getUser(@AuthenticationPrincipal OidcUser user) {
        System.out.println("getUser");
        return ResponseEntity.ok(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(CacheMeIfYouCanApplication.class, args);
    }
}