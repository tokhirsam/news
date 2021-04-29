package uz.pdp.appnews.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.pdp.appnews.entity.User;


import java.util.Optional;

public class KimYozganiBilishUchun implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication!=null &&
                authentication.isAuthenticated() &&
                !authentication.getPrincipal().equals("anonymousUser")){
            User user = (User) authentication.getPrincipal();
            return Optional.of(user);
        }
            return Optional.empty();

    }

}
