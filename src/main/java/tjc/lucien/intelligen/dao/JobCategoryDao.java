package tjc.lucien.intelligen.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.JobCategory;

import java.util.List;

/**
 * Created by Lucien on 2017/3/3.
 */
@Repository
public interface JobCategoryDao extends JpaRepository<JobCategory, Integer> {

    /* 根据id获取工作类别 */
    JobCategory findById(int id);

    /* 根据pid获取pid!=-1 */
    List<JobCategory> findByParentIdNot(int pid);
}
