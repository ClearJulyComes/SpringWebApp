package springApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springApp.Models.UserLogin;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<UserLogin, String> {
    UserLogin findByUsername(String username);
    List<UserLogin> findTop10ByOrderByLvlDesc();
}
