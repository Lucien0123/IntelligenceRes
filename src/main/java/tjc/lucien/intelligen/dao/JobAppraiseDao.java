package tjc.lucien.intelligen.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.JobAppraise;

/**
 * Created by Administrator on 2017/3/17.
 */
@Repository
public interface JobAppraiseDao extends JpaRepository<JobAppraise, Integer>{

    /* 根据工作id获取面试评价列表 */
    Page<JobAppraise> findByJobId(Pageable pageable);

    /* 根据工作id获取最赞的面试评价 */
    JobAppraise findTop1ByJobIdOrderByApproveCount(int jobid);

    JobAppraise findById(int appraiseId);
}
