package uz.pdp.appnews.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.appnews.entity.Role;
import uz.pdp.appnews.entity.User;
import uz.pdp.appnews.entity.enums.Permissions;
import uz.pdp.appnews.repository.RoleRepository;
import uz.pdp.appnews.repository.UserRepository;
import uz.pdp.appnews.utils.AppConstants;

import java.util.Arrays;

import static uz.pdp.appnews.entity.enums.Permissions.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){
            Permissions[] permission = Permissions.values();  // hamma enumlarni qaytaradi

            Role admin= roleRepository.save(new Role(
                    AppConstants.ADMIN,
                    Arrays.asList(permission),
                    "system owner"
            ));
            Role user = roleRepository.save(new Role(
                    AppConstants.USER,
                    Arrays.asList(DELETE_MY_COMMENT, ADD_COMMENT, EDIT_COMMENT),
                    "simple user"
            ));

            userRepository.save(new User(
                    "Admin",
                    "admin",
                    passwordEncoder.encode("admin"),
                    admin,
                    true

            ));
            userRepository.save(new User(
                    "User",
                    "user",
                    passwordEncoder.encode( "user"),
                    user,
                    true

            ));
        }
    }
}
