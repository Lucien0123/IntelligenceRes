package tcu.lucien.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import tcu.lucien.entity.User;

/**
 * Created by Administrator on 2016/12/25.
 */
@Component
public interface UserDao extends JpaRepository<User, Integer> {

    //根据用户名和密码查找用户
    User findByUsernameAndPassword(String username, String password);

}
