package springApp.Models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Slf4j
@Entity
@Data
public class UserLogin implements UserDetails {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    @Column(name = "lvl")
    private int lvl = 1;
    @Column(name = "isAdmin")
    private boolean isAdmin = false;

    public UserLogin(){

    }
    public UserLogin(String login, String password) {
        this.username = login;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (isAdmin){
            log.warn("isAdmin");
            return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
