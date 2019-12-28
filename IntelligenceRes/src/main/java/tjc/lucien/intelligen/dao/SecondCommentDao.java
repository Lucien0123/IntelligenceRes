package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.SecondComment;

/**
 * Created by Administrator on 2017/3/14.
 */
@Repository
public interface SecondCommentDao extends JpaRepository<SecondComment, Integer> {


}
