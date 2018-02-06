package tjc.lucien.intelligen.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.Task;

import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */
@Repository
public interface TaskDao extends JpaRepository<Task, Integer> {

    Page<Task> findByAuditorNameAndStatusIn(String adminerName, List<String> statusStrs, Pageable pageable);

    Task findById(int taskid);

    /* 根据审核人获取 */
    Page<Task> findByAuditorIdAndStatusIn(int adminerid, List<String> todoStatus, Pageable pageable);

    /* 根据提交人获取 */
    Page<Task> findBySubmiterIdAndStatusIn(int adminerid, List<String> todoStatus, Pageable pageable);


}
