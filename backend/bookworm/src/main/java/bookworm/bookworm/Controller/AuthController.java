package bookworm.bookworm.Controller;

import bookworm.bookworm.DTO.Roles;
import bookworm.bookworm.DTO.user.UserLoginDTO;
import bookworm.bookworm.DTO.user.UserRegistrationDTO;
import bookworm.bookworm.model.User;
import bookworm.bookworm.repository.UserRepository;
import bookworm.bookworm.security.TokenService;
import bookworm.bookworm.service.AuthorizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO){
        var usernamePassword = new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateUserToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO){
        if (this.userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        String securePassword = new BCryptPasswordEncoder().encode(userRegistrationDTO.getPassword());

        User newUser = new User(userRegistrationDTO.getEmail(), userRegistrationDTO.getUsername(), userRegistrationDTO.getImage(), securePassword);
        newUser.setRole(Roles.ROLE_USER);

        userRepository.save(newUser);

        return ResponseEntity.status(201).body("Successfully registered");
    }

    @PostMapping("/check-username")
    public ResponseEntity<Void> checkUsername(@RequestParam String username){
        if (authorizationService.checkUsernameForRegistration(username)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/check-email")
    public ResponseEntity<Void> checkEmail(@RequestParam String email) {
        if (authorizationService.checkEmailForRegistration(email)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }
}
