package springSecurity.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfFilter;
import springSecurity.study.security.filter.CsrfTokenLogger;

@Configuration
public class ProjectConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterAfter(new CsrfTokenLogger(), CsrfFilter.class)
                .authorizeRequests()
                .anyRequest().permitAll();

        return http.build();

    }
}
