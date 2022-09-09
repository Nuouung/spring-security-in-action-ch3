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
        UserDetails userA = User.withUsername("jinseok")
                .password("1234")
                .authorities("WRITE", "READ", "UPDATE")
                .build();

        UserDetails userB = User.withUsername("suchon")
                .password("1234")
                .authorities("READ")
                .build();

        UserDetailsManager userDetailsService = (UserDetailsManager) this.userDetailsService;

        userDetailsService.createUser(userA);
        userDetailsService.createUser(userB);
    }
}
