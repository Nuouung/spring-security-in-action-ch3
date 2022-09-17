package springSecurity.study.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import springSecurity.study.security.filter.AuthenticationLoggingFilter;
import springSecurity.study.security.filter.RequestValidationFilter;
import springSecurity.study.security.filter.StaticKeyAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ProjectConfiguration {

    private final StaticKeyAuthenticationFilter staticKeyAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterAt(staticKeyAuthenticationFilter, BasicAuthenticationFilter.class)
                .authorizeRequests().anyRequest().permitAll();

        return http.build();
    }
}
