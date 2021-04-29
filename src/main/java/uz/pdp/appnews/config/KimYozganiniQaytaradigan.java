package uz.pdp.appnews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.pdp.appnews.entity.User;

import java.util.UUID;

@Configuration
@EnableJpaAuditing
public class KimYozganiniQaytaradigan {

    @Bean
    AuditorAware<User> auditorAware(){
        return new KimYozganiBilishUchun();
    }
}
