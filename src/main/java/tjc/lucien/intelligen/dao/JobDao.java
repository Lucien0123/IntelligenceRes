package tjc.lucien.intelligen.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tjc.lucien.intelligen.entity.Job;

import java.util.List;

/**
 * Created by Administrator on 2017/3/13.
 */
@Repository
public interface JobDao extends JpaRepository<Job, Integer> {

    /* 获取最新发布的前十条工作 */
    List<Job> findTop10ByPublishedOrderByPublicTimeDesc(boolean b);

    /* 获取推荐工作 */
    List<Job> findByIsRecomment(boolean isRecomment);

    /* 获取热门工作 */
    List<Job> findTop10ByOrderByVisitTimes();

    /* 根据工作类别获取工作列表 */
    Page<Job> findByJobCateNameAndPublished(String jobcate, boolean b, Pageable pageable);

    /* 获取工作详情，根据Id */
    Job findById(int id);

    /* 获取本公司是否存在同名招聘职位 */
    List<Job> findByJobNameAndCompanyId(String jobname, int parkId);

    Page<Job> findByPublished(boolean b, Pageable pageable);

    Page<Job> findByPublishedAndCompanyName(boolean b, String comName, Pageable pageable);

    Job findByTaskId(int taskId);

    List<Job> findByPublishedAndIdIn(boolean b, String[] ids);

    /* 根据公司id，获取改公司的所有发布的工作，不做分页 */
    List<Job> findByPublishedAndCompanyId(boolean b, int cid);

    Page<Job> findByJobCateNameAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(String jobName, List<String> jobSalary, List<String> worktype, boolean b, List<String> workExpressions, List<String> educations, List<String> worktypes, Pageable pageable);

    Page<Job> findByJobNameLikeAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(String likeStr, List<String> jobSalary, List<String> worktype, boolean b, List<String> workExpressions, List<String> educations, List<String> worktypes, Pageable pageable);

    Page<Job> findByJobNameLikeAndJobCateNameAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(String likeStr, String jobCate, List<String> jobSalary, List<String> worktype, boolean b, List<String> workExpressions, List<String> educations, List<String> worktypes, Pageable pageable);
}
