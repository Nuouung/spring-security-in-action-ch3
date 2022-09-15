package springSecurity.study.security.filter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class AuthenticationLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String requestId = httpRequest.getHeader("Request-Id");

        log.info("인증 성공 요청 ID: {}", requestId);
        httpRequest.setAttribute("loggingResult", true);

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
