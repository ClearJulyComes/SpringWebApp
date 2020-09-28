package springApp.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class LevelData {

    @Id
    private int lvlId;
    @Lob
    private String coords;

    /*@OneToMany(mappedBy = "lvl", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Coords> userInfo = new HashSet<Coords>(); */

    public LevelData() {
    }

    public LevelData(int lvlId, String coords) {
        this.lvlId = lvlId;
        this.coords = coords;
    }
}
