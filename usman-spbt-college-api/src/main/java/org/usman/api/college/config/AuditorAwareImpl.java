package org.usman.api.college.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class AuditorAwareImpl implements AuditorAware<String> {

//    @Override
//    public Optional<String> getCurrentAuditor() {
//        return Optional.of("system"); //default username
//    }

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated()){
            return Optional.of("system"); //Optional.empty();
        }else {
            //return Optional.of(authentication.getName()); //returned --> "anonymousUser" if using without Authentication from Swagger
            return Optional.of("system"); //returned logged in user-name
        }
    }
}
