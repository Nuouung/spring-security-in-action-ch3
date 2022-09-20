package springSecurity.study.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import springSecurity.study.security.csrf.repository.CustomCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ProjectConfiguration {

    private final CustomCsrfTokenRepository customCsrfTokenRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(httpSecurityCsrfConfigurer -> {
            httpSecurityCsrfConfigurer
                    .csrfTokenRepository(customCsrfTokenRepository);
            httpSecurityCsrfConfigurer.ignoringAntMatchers("/ciao"); // /ciao 경로는 csrf 필터 적용을 무시하라
        });

        http.authorizeRequests().anyRequest().permitAll();

        return http.build();

    }
}
