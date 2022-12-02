package springSecurity.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springSecurity.study.repository.ResourceRepository;
import springSecurity.study.security.service.SecurityResourceService;

@Configuration
@RequiredArgsConstructor
public class AppConfiguration {

    private final ResourceRepository resourceRepository;

    @Bean
    public SecurityResourceService securityResourceService() {
        return new SecurityResourceService(resourceRepository);
    }
}
