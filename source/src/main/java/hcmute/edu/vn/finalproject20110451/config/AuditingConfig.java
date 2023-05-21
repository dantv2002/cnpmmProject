package hcmute.edu.vn.finalproject20110451.config;

import hcmute.edu.vn.finalproject20110451.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@Configuration
@EnableMongoAuditing
public class AuditingConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        if (authentication.getPrincipal() instanceof UserEntity)
            return Optional.ofNullable(((UserDetails) authentication.getPrincipal()).getUsername());
        return Optional.ofNullable(authentication.getName());
    }
}