package peaksoft.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.requests.LoginRequest;
import peaksoft.responses.JwtResponse;

import peaksoft.entity.User;
import peaksoft.exceptions.BadRequestException;
import peaksoft.exceptions.NotFoundException;
import peaksoft.jwt.JwtTokenUtil;
import peaksoft.repository.UserRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginService {

    private final JwtTokenUtil jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public JwtResponse authenticate(LoginRequest loginRequest) {

        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new NotFoundException(
                        "user with email: " + loginRequest.getEmail() + " not found!"
                ));

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new BadRequestException(
                    "invalid password"
            );
        }

        String token = jwtUtils.generatedToken(user);
        return new JwtResponse(
                user.getId(),
                user.getEmail(),
                token,
                user.getRole()
        );
    }

}