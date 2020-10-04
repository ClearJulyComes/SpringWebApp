package springApp.Models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class LevelData {

    @Id
    private int lvlId;
    @Lob
    private String coords;

    public LevelData() {
    }

    public LevelData(int lvlId, String coords) {
        this.lvlId = lvlId;
        this.coords = coords;
    }
}
