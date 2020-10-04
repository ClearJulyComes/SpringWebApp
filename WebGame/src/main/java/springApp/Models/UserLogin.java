package springApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class UserLogin {
    @Id
    private String login;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private UserInfo userInfoLogin;

    @OneToMany(mappedBy = "loginLvl", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<UserLevels> userInfo = new HashSet<UserLevels>();
}
