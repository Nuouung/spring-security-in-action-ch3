package springSecurity.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("helloMvc")
    public String helloMvc() {
        return "hello mvc matchers!!";
    }

    @GetMapping("helloAnt")
    public String helloAnt() {
        return "hello ant matchers!!";
    }
}
