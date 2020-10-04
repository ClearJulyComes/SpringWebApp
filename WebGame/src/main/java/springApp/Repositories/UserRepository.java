package springApp.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springApp.Models.UserLogin;
@Repository
public interface UserRepository extends CrudRepository<UserLogin, String> {
    UserLogin findByUsername(String username);
}
