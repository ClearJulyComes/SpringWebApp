package springApp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springApp.Models.UserLogin;
import springApp.Repositories.UserRepository;

@Slf4j
@Service
public class UserRepositoryUserDetailsService
        implements UserDetailsService {
    private UserRepository userRepo;
    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserLogin user = userRepo.findByUsername(username);
        if (user != null) {
            log.warn("It's not null");
            return user;
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found");
    }
}