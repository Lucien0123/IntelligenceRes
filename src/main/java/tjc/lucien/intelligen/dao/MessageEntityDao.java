package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.MessageEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/5/6.
 */
@Repository
public interface MessageEntityDao extends JpaRepository<MessageEntity, Integer>{

    MessageEntity findById(int id);

    List<MessageEntity> findByAccountAndIsRead(String account, boolean b);
}
