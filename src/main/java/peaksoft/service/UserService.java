package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import peaksoft.dto.register.RegisterRequest;
import peaksoft.dto.register.RegisterResponse;
import peaksoft.dto.requests.UserRoleRequest;
import peaksoft.responses.SimpleResponse;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.jwt.JwtTokenUtil;
import peaksoft.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final JwtTokenUtil util;

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
        user.setRole(Role.STUDENT);
        return user;
    }

    public RegisterResponse mapToResponse(User user) {
        if (user == null) {
            return null;
        }

        String token = util.generatedToken(user);
        RegisterResponse response = new RegisterResponse();
        if (user.getId() != null) {
            response.setId(String.valueOf(user.getId()));
        }
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setRole(user.getRole());
        response.setToken(token);

        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with email not found!"));
    }

    @Transactional
    public SimpleResponse changeRole(Long userId, UserRoleRequest request) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException("User with " + userId + "not found!"));

        user.setRole(request.getRole());
        return new SimpleResponse(
                "UPDATE",
                "Role changed to instructor"
        );
    }
}


