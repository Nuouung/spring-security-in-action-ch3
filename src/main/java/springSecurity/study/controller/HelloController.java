package springSecurity.study.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(HttpServletRequest request) {

        String requestId = request.getHeader("Request-Id");
        String loggingResult = (Boolean) request.getAttribute("loggingResult") ?
                "인증 로그 기록 완료" : "인증 로그 기록 실패";

        return requestId + " (" + loggingResult + ")";
    }
}
