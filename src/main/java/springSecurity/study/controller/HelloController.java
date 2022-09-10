package springSecurity.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/a")
    public String getEndpointA() {
        return "Works!";
    }

    @GetMapping("/a/{more}")
    public String getEndpointA(@PathVariable String more) {
        return more;
    }

    @PostMapping("/a")
    public String postEndpointA() {
        return "Works!";
    }

    @GetMapping("/b")
    public String getEndpointB() {
        return "Works!";
    }

    @PostMapping("/b")
    public String postEndpointB() {
        return "Works!";
    }
}
