package springApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springApp.Models.LevelData;

import java.util.List;

@Repository
public interface LevelRepository extends CrudRepository<LevelData, Integer> {
}
