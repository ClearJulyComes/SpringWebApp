package springApp.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springApp.Models.UserLogin;
import springApp.Repositories.UserRepository;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public boolean addOrDeleteAdmin(String username, boolean status){
        try {
            UserLogin userLogin = userRepository.findByUsername(username);
            userLogin.setAdmin(status);
            userRepository.save(userLogin);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public int[] getUserLevels(String username){
        return IntStream.rangeClosed(1, userRepository.findByUsername(username).getLvl()).toArray();
    }

    public List<UserLogin> getTop10(){
        return userRepository.findTop10ByOrderByLvlDesc();
    }

    public boolean checkLevelAccess(String username, int id){
        return userRepository.findByUsername(username).getLvl()>=id;
    }

    public void setFinishedLevel(String username, int id){
        UserLogin userLogin = userRepository.findByUsername(username);
        if(id==userLogin.getLvl()){
            userLogin.setLvl(userLogin.getLvl() + 1);
            userRepository.save(userLogin);
        }
    }

    public void registerNewUser(UserLogin userLogin){
        userRepository.save(userLogin);
    }
}
