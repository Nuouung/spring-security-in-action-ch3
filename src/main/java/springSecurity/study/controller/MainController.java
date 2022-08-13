package springSecurity.study.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {

    @GetMapping("/main")
    public String getMain(Authentication authentication) {
        // 이렇게 꺼내 쓸 수도 있음.
//        SecurityContext securityContext = SecurityContextHolder.getContext();
//        Authentication authentication = securityContext.getAuthentication();

        String password = (authentication.getCredentials() == null) ?
                "successfully deleted in this application presentation layer for security!" :
                authentication.getCredentials().toString() + "!";

        return "hello " + authentication.getName() + "!<br>" +
                "your password is " + password;
    }
}
