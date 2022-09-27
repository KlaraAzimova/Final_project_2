package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.register.RegisterRequest;
import peaksoft.dto.register.RegisterResponse;

import peaksoft.dto.requests.LoginRequest;
import peaksoft.responses.JwtResponse;
import peaksoft.service.LoginService;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
public class AuthController {
    private final UserService userService;

    private final LoginService loginService;


    @PostMapping("/registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {
        return userService.create(request);
    }

    @PostMapping("/authentication")
    public JwtResponse perFormLogin(@RequestBody LoginRequest login) {
        return loginService.authenticate(login);

    }

}







