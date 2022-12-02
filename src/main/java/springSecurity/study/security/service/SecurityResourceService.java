package springSecurity.study.security.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import springSecurity.study.domain.Resource;
import springSecurity.study.repository.ResourceRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class SecurityResourceService {

    private final ResourceRepository resourceRepository;

    public SecurityResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceMap() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> resourceMap = new LinkedHashMap<>();

        List<Resource> resourceList = resourceRepository.findAll();
        resourceList.forEach(r -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            // r.getRoleSet().forEach(role -> {
            //  configAttributeList.add(new SecurityConfig(role.getRoleName()));
            //  resourceMap.put(new AntPathRequestMatcher(r.getResourceName()), configAttributeList);
            // });
        });

        return resourceMap;
    }
}
