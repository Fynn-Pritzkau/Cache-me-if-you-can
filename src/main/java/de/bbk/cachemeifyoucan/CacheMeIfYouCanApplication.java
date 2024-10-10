package de.bbk.cachemeifyoucan;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class CacheMeIfYouCanApplication
{
    @GetMapping("/logout")
    public RedirectView logout(HttpServletRequest request, HttpServletResponse response)
    {
        new SecurityContextLogoutHandler()
                .logout(request, response, SecurityContextHolder.getContext().getAuthentication());

        return new RedirectView("https://login.microsoftonline.com/61ac6a94-31d3-4709-b7b9-a8a96ad74648/oauth2/v2.0/logout?post_logout_redirect_uri=http://localhost:5173/");
    }

    @GetMapping("/user")
    public ResponseEntity<OidcUser> getUser(@AuthenticationPrincipal OidcUser user)
    {
        return ResponseEntity.ok(user);
    }

    public static void main(String[] args)
    {
        SpringApplication.run(CacheMeIfYouCanApplication.class, args);
    }
}