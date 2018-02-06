package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tjc.lucien.intelligen.entity.Question;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21.
 */
@Repository
@Transactional
public interface QuestionDao extends JpaRepository<Question, Integer>{
    List<Question> findByCompanyId(int id);

    Question findById(int questionId);
}
