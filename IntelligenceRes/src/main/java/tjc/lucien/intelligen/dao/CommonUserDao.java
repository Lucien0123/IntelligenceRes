package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.CommonUser;

/**
 * Created by Administrator on 2017/3/3.
 */
@Repository
public interface CommonUserDao extends JpaRepository<CommonUser, Integer> {

    /* 登录操作，根据帐号和密码验证 */
    CommonUser findByAccountAndPassword(String account, String password);

    /* 注册时验证账号是否存在 */
    CommonUser findByAccount(String account);

    CommonUser findById(int quizzerId);

}
