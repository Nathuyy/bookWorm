package bookworm.bookworm.service;

import bookworm.bookworm.model.User;
import bookworm.bookworm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
        return user;
    }

    public boolean checkUsernameForRegistration(String username){
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean checkEmailForRegistration(String email){
        return userRepository.findByEmail(email).isPresent();
    }

}
