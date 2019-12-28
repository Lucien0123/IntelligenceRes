package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.JobCondition;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Repository
public interface JobConditionDao extends JpaRepository<JobCondition, Integer> {

    /* 根据pid获取搜索条件列表 */
    public List<JobCondition> findByPid(int pid);
}
