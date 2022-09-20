package springSecurity.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String getHello() {
        return "GET /hello";
    }

    @PostMapping("/hello")
    public String postHello() {
        return "POST /hello";
    }

    @PostMapping("/ciao")
    public String postCiao() {
        return "POST /ciao";
    }
}
