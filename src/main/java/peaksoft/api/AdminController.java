package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.UserRoleRequest;
import peaksoft.responses.SimpleResponse;
import peaksoft.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {

    private final UserService userService;

    @PostMapping("/change/role/{userId}")
    public SimpleResponse changeRole(@PathVariable Long userId,
                                     @RequestBody UserRoleRequest request) {
        return userService.changeRole(userId, request);

    }
}
