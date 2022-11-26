package springSecurity.study.security.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import springSecurity.study.security.common.FormAuthenticationDetailsSource;
import springSecurity.study.security.handler.CustomAccessDeniedHandler;
import springSecurity.study.security.handler.CustomAuthenticationFailureHandler;
import springSecurity.study.security.handler.CustomAuthenticationHandler;
import springSecurity.study.security.provider.CustomAuthenticationProvider;

import java.security.cert.Extension;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Order(1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final FormAuthenticationDetailsSource formAuthenticationDetailsSource;
    private final AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        String password = passwordEncoder().encode("1234");

        auth
                .inMemoryAuthentication()
                .withUser("user")
                .password(password)
                .roles("USER");

        auth
                .inMemoryAuthentication()
                .withUser("manager")
                .password(password)
                .roles("MANAGER", "USER");

        auth
                .inMemoryAuthentication()
                .withUser("admin")
                .password(password)
                .roles("ADMIN", "USER", "MANAGER");
    }

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories
                .createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

        // httpSecurity.antMatchers("/css/**").permitAll() 과의 차이는
        // httpSecurity는 보안 필터에 들어간 후 필터를 통과하는 것이고
        // webSecurity는 보안 필터에 들어가지도 않는다는 것
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/users", "/login*").permitAll()
                .antMatchers("/mypage").hasRole("USER")
                .antMatchers("/messages").hasRole("MANAGER")
                .antMatchers("/configuration").hasRole("ADMIN")
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login_proc")
                .authenticationDetailsSource(formAuthenticationDetailsSource)
                .successHandler(new CustomAuthenticationHandler())
                .failureHandler(new CustomAuthenticationFailureHandler())
                .defaultSuccessUrl("/")
                .permitAll()

                .and()
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler("/denied"));
    }
}
