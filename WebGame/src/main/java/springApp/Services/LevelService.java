package springApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.Models.LevelData;
import springApp.Repositories.LevelRepository;

import java.util.stream.IntStream;

@Service
public class LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }

    public int[] getAllLevels(){
        return IntStream.rangeClosed(1, levelRepository.findFirstByOrderByLvlIdDesc().getLvlId()).toArray();
    }

    public void deleteLevel(int id){
        levelRepository.delete(levelRepository.findById(id).orElse(new LevelData()));
    }

    public void saveLevel(LevelData levelData){
        levelRepository.save(levelData);
    }

    public LevelData getLevelData (int id){
        return levelRepository.findById(id).orElse(new LevelData());
    }
}
