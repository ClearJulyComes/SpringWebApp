package springApp.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserInfo {
    @Id
    private String user_id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login", nullable = false)
    private UserLogin login;
    private String password;
}
