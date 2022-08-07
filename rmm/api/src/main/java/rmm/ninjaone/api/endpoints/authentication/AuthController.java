package rmm.ninjaone.api.endpoints.authentication;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import rmm.ninjaone.api.services.AuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "${apiPrefix}")
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<UserRegisteredResponse> signup(@Valid @RequestBody RegisterUserRequest request) {
        var userId = authenticationService.register(request.getFullName(), request.getEmail(), request.getPassword());

        var response = new UserRegisteredResponse();
        response.setUserEmail(request.getEmail());
        response.setUserId(userId);

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<UserLoggedResponse> login(@Valid @RequestBody LoginUserRequest request) {
        var token = authenticationService.login(request.getEmail(), request.getPassword());

        var response = new UserLoggedResponse();
        response.setAccessToken(token.getAccessToken());
        response.setExpiresAt(token.getExpiresAt());

        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/test")
    public ResponseEntity<String> test() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("Hello " + authentication.getPrincipal());
    }
}
