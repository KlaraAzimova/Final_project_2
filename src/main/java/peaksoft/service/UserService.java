package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.dto.register.RegisterRequest;
import peaksoft.dto.register.RegisterResponse;
import peaksoft.entity.User;
import peaksoft.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    //    private final PasswordEncoder passwordEncoder;
    public RegisterResponse create(RegisterRequest registerRequest) {
        User user = mapToEntity(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        userRepository.save(user);
        return mapToResponse(user);
    }

    public User mapToEntity(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setFirstName(registerRequest.getFirstName());
        user.setPassword(registerRequest.getPassword());
        return user;
    }

    public RegisterResponse mapToResponse(User user) {
        if (user == null) {
            return null;
        }
        RegisterResponse response = new RegisterResponse();
        if (user.getId() != null) {
            response.setId(String.valueOf(user.getId()));
        }
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found!"));
    }
}


