package bookworm.bookworm.service;

import bookworm.bookworm.DTO.user.UserRegistrationDTO;
import bookworm.bookworm.DTO.user.UserUpdateDTO;
import bookworm.bookworm.model.User;
import bookworm.bookworm.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final String USER_SUCESS = "Successful action";


    @Transactional
    public ResponseEntity<String> registerNewUser(UserRegistrationDTO userRegistrationDTO) {
        if (userRepository.existsByEmail(userRegistrationDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already in use");
        }

        User newUser = mapDtoToUser(userRegistrationDTO);
        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(USER_SUCESS);
    }

    @Transactional
    public ResponseEntity<List<User>> listAllUsers() {
        var allUsers = userRepository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @Transactional
    public ResponseEntity findUserByEmail(String email){
        var user = userRepository.findByEmail(email);

        if (user.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User email not found");
        }

        return ResponseEntity.ok(user.get());
    }

    @Transactional
    public ResponseEntity<String> updateUser(String email, UserUpdateDTO userUpdateDTO){
        var userData = userRepository.findByEmail(email);

        if (userData.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User email not found");
        }

        User user = userData.get();

        user.setUsername(userUpdateDTO.getUsername());

        if (userUpdateDTO.getPassword() != null && !userUpdateDTO.getPassword().isBlank()) {
            user.setPassword(userUpdateDTO.getPassword());
        }

        user.setImage(userUpdateDTO.getImage());

        return ResponseEntity.ok().body(USER_SUCESS);
    }

    @Transactional
    public ResponseEntity<String> deleteUser(String email) {
        var userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        userRepository.delete(userOptional.get());
        return ResponseEntity.ok(USER_SUCESS);
    }

    private User mapDtoToUser(UserRegistrationDTO userRegistrationDTO) {
        User newUser = new User();
        newUser.setUsername(userRegistrationDTO.getUsername());
        newUser.setEmail(userRegistrationDTO.getEmail());
        newUser.setPassword(userRegistrationDTO.getPassword());
        newUser.setImage(userRegistrationDTO.getImage());
        return newUser;
    }


}
