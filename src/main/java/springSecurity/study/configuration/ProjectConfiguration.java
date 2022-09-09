package springSecurity.study.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest().permitAll();
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest().hasAuthority("WRITE");
//    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic();
//
//        http.authorizeRequests()
//                .anyRequest().hasAnyAuthority("WRITE", "READ");
//    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic();

        http.authorizeRequests()
                .anyRequest().access(
                        "hasAuthority('WRITE') " +
                        "and !hasAuthority('DELETE')"); // WRITE를 가지고 있고 DELETE는 가지지 않은 회원만 접근 가능
        // hasAuthority, hasAnyAuthority를 사용할 수 없는 경우에만 사용하자
        // access를 사용할 경우 코드 가독성이 떨어지고 리펙토링이 어려워진다!
    }
}
