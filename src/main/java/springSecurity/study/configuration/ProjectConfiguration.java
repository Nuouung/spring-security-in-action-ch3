package springSecurity.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ProjectConfiguration {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .mvcMatchers("/hello").hasRole("ADMIN") // csrf 방어 정책 때문에 GET만 들여보내고 나머지는 전부 Forbidden 처리한다.
//                .mvcMatchers("/ciao").hasRole("MANAGER")
////                .anyRequest().permitAll()
////                .anyRequest().denyAll();
//                .anyRequest().permitAll(); // 선언 순서는 구체적인 것을 위로 추상적인 것을 아래로
//
//
//        return http.build();
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
