package springSecurity.study.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
public class HelloController {

    @GetMapping("/")
    public String getHello() {
        return "main";
    }

    @PostMapping("/test")
    @ResponseBody
    public String test() {
        log.info("테스트 메소드 호출");
        return "테스트 성공";
    }
}
