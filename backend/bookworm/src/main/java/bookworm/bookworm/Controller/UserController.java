package bookworm.bookworm.Controller;

import bookworm.bookworm.DTO.user.UserRegistrationDTO;
import bookworm.bookworm.repository.UserRepository;
import bookworm.bookworm.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid UserRegistrationDTO userRegistrationDTO){
            var newUser = userService.registerNewUser(userRegistrationDTO);
            return ResponseEntity.status(201).body(newUser);
    }

    @GetMapping("all")
    public ResponseEntity findAll(){
        return userService.listAllUsers();

    }

}
