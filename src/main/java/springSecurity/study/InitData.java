package springSecurity.study;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitData {

    private final UserDetailsService userDetailsService;

    @PostConstruct
    public void saveUsers() {

        // role 정의 방법 1
//        UserDetails userA = User.withUsername("jinseok")
//                .password("1234")
//                .authorities("ROLE_ADMIN")
//                .build();
//
//        UserDetails userB = User.withUsername("suchon")
//                .password("1234")
//                .authorities("ROLE_MANAGER")
//                .build();

        UserDetails userA = User.withUsername("jinseok")
                .password("1234")
                .roles("ADMIN")
                .build();

        UserDetails userB = User.withUsername("suchon")
                .password("1234")
                .roles("MANAGER")
                .build();

        UserDetailsManager userDetailsService = (UserDetailsManager) this.userDetailsService;

        userDetailsService.createUser(userA);
        userDetailsService.createUser(userB);
    }
}
