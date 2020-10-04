package springApp.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserLevels {
    @Id
    private long userLvlId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "login", nullable = false)
    private UserLogin loginLvl;
    private Integer userLevels;
}
