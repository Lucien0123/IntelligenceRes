package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tjc.lucien.intelligen.entity.FirstComment;

import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
@Repository
@Transactional
public interface FirstCommentDao extends JpaRepository<FirstComment, Integer> {


    List<FirstComment> findByQuestionId(int id);

    FirstComment findById(int firCommentId);
}
