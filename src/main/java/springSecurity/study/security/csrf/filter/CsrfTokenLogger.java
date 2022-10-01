package springSecurity.study.security.csrf.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.web.csrf.CsrfToken;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class CsrfTokenLogger implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        CsrfToken token = (CsrfToken) servletRequest.getAttribute("_csrf");

        log.info("CSRF token " + token.getToken());

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
