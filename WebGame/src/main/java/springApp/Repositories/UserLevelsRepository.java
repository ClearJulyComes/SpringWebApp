package springApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springApp.Models.UserLevels;

@Repository
public interface UserLevelsRepository extends CrudRepository<UserLevels, Long> {
}
