package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.register.RegisterRequest;
import peaksoft.dto.register.RegisterResponse;
import peaksoft.dto.requests.LoginRequest;
import peaksoft.dto.responses.LoginResponse;
import peaksoft.entity.User;
import peaksoft.jwt.JwtTokenUtil;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {
    private final UserService userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginRequest login;


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {
        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(token.getName()).get();
            return ResponseEntity.ok().body(login.toLoginView(jwtTokenUtil.generatedToken(user), "Successfully", user));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(login.toLoginView("", "login_failed", null));
        }
    }

    @PostMapping("/registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {
        return userService.create(request);
    }
}







