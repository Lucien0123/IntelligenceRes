package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.Adminer;

import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Repository
public interface AdminerDao extends JpaRepository<Adminer, Integer>{

    /* 根据用户名和密码获取用户 */
    public Adminer findByAccountAndPassword(String account, String password);

    List<Adminer> findByAccount(String busiAccount);

    List<Adminer> findByPower(int admin);

    Adminer findById(int auditorId);
}
