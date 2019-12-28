package tjc.lucien.intelligen.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.Interview;

/**
 * Created by Administrator on 2017/3/26.
 */
@Repository
public interface InterviewDao extends JpaRepository<Interview, Integer> {

    /* 根据工作发布者获取投递列表 */
    Page<Interview> findByPublisherAndStatus(String name, String status, Pageable pageable);

    Interview findById(int interId);
}
