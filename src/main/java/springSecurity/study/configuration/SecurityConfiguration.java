package springSecurity.study.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import springSecurity.study.security.authentication.CustomAuthenticationFailureHandler;
import springSecurity.study.security.authentication.CustomAuthenticationSuccessHandler;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomAuthenticationSuccessHandler authenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. 이렇게 사용할 일은 없다.
//        FormLoginConfigurer<HttpSecurity> httpSecurityFormLoginConfigurer = http.formLogin();
//        httpSecurityFormLoginConfigurer.defaultSuccessUrl("/home", true);
//
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry expressionInterceptUrlRegistry = http.authorizeRequests();
//        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.AuthorizedUrl authorizedUrl = expressionInterceptUrlRegistry.anyRequest();
//        authorizedUrl.authenticated();

        // 2. 거의 기본 포맷
//        http.formLogin()
//                .defaultSuccessUrl("/home", true);
//
//        http.authorizeRequests()
//                .anyRequest().authenticated();

        // 3. 만약 로그인 성공과 실패시 커스텀된 로직을 추가하고 싶다면
        http.formLogin()
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);

        http.authorizeRequests()
                .anyRequest().authenticated();
    }
}
