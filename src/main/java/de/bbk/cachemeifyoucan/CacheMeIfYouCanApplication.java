    package de.bbk.cachemeifyoucan;

    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import org.springframework.stereotype.Controller;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @SpringBootApplication
    @Controller
    @RequestMapping("/api")
    public class CacheMeIfYouCanApplication
    {
        @GetMapping("/login")
        public String login() {
            return "login"; // Gibt die login.html Seite zurück
        }

        @GetMapping("/logout")
        public String logout() {
            return "logout"; // Gibt die logout.html Seite zurück
        }

        public static void main(String[] args)
        {
            SpringApplication.run(CacheMeIfYouCanApplication.class, args);
        }

    }
