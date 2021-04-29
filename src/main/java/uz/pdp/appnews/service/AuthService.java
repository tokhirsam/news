package uz.pdp.appnews.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.appnews.entity.Role;
import uz.pdp.appnews.entity.User;
import uz.pdp.appnews.exception.ResourceNotFoundException;
import uz.pdp.appnews.payload.ApiResponse;
import uz.pdp.appnews.payload.RegisterDto;
import uz.pdp.appnews.repository.RoleRepository;
import uz.pdp.appnews.repository.UserRepository;
import uz.pdp.appnews.utils.AppConstants;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(RegisterDto dto) {
        if (!dto.getPrePassword().equals(dto.getPassword()))
            return new ApiResponse("Password does not match", false);
        if (userRepository.existsByUsername(dto.getUsername())) {
            return new ApiResponse("Username already registered", false);
        }
        User user = new User(
                dto.getFullName(),
                dto.getUsername(),
                passwordEncoder.encode(dto.getPassword()),
                roleRepository.findByName(AppConstants.USER).orElseThrow(() -> new ResourceNotFoundException("role", "name",AppConstants.USER)),
                true
        );
        userRepository.save(user);
        return new ApiResponse("Registration completed successfully", true);



    }

    public UserDetails loadUserByUsername(String username) {
       return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
