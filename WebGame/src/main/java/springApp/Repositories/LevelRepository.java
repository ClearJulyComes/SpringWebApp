package springApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import springApp.Models.LevelData;

import java.util.List;

@Repository
public interface LevelRepository extends CrudRepository<LevelData, Integer> {
    @Transactional
    List<LevelData> findAllByOrderByLvlId();
    @Transactional
    LevelData findFirstByOrderByLvlIdDesc();
}
