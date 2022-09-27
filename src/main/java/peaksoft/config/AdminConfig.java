package peaksoft.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.UserRepository;

@Configuration
public class AdminConfig {

    private final PasswordEncoder passwordEncoder;

    public AdminConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            String password = "admin";
            User user = new User();
            user.setFirstName("Dinara Bakirova");
            user.setEmail("admin@gmail.com");
            user.setPassword(passwordEncoder.encode(password));
            user.setRole(Role.ADMIN);

            userRepository.save(user);
        };
    }
}
