package tjc.lucien.intelligen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tjc.lucien.intelligen.dao.JobDao;
import tjc.lucien.intelligen.dao.NewsDao;
import tjc.lucien.intelligen.dao.TaskDao;
import tjc.lucien.intelligen.entity.Adminer;
import tjc.lucien.intelligen.entity.Job;
import tjc.lucien.intelligen.entity.NewsAnnouncement;
import tjc.lucien.intelligen.entity.Task;
import tjc.lucien.intelligen.utils.FinalMappingUtil;
import tjc.lucien.intelligen.utils.FormatDate2StringUtil;
import tjc.lucien.intelligen.utils.PageableUtil;
import tjc.lucien.intelligen.utils.PowerMappingUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/22.
 */
@Controller
@RequestMapping("/sys/task")
public class SysTaskController {

    public Logger log = Logger.getLogger(SysTaskController.class);
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private NewsDao newsDao;

    @RequestMapping("/center")
    public String sysTaskCenter(Model model, HttpServletRequest request){
        log.info("跳转进入任务中心页面");
        int size = 15; //每页要显示的记录条数
        int number = 0; //要查询第number页
        String numberStr = request.getParameter("number");
        if (numberStr != null && !"".equals(numberStr)) {
            number = Integer.parseInt(numberStr);
        }
        if (number < 0) {
            number = 0;
        }
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        int adminerid = cur_adminer.getId();
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            log.info("园区管理员获取代办已办列表");
            List<String> todoStatus = new ArrayList<>();
            todoStatus.add("已提交");
            todoStatus.add("已修改");
            List<String> completeStatus = new ArrayList<>();
            completeStatus.add("已发布");
            completeStatus.add("被退回");
            Pageable pageable = PageableUtil.returnASCPageable(number, size, "publishDate");
            Page<Task> todoPage = taskDao.findByAuditorIdAndStatusIn(adminerid, todoStatus, pageable);
            Page<Task> completePage = taskDao.findByAuditorIdAndStatusIn(adminerid, completeStatus, pageable);
            model.addAttribute("todoPage", todoPage);
            model.addAttribute("completePage", completePage);
            model.addAttribute("isSubmiter", true);
        } else if (cur_adminer.getPower() == PowerMappingUtil.BUSINESSMANAGER){
            log.info("企业管理员获取代办已办列表");
            List<String> todoStatus = new ArrayList<>();
            todoStatus.add("被退回");
            List<String> completeStatus = new ArrayList<>();
            completeStatus.add("已修改");
            completeStatus.add("已提交");
            completeStatus.add("已发布");
            Pageable pageable = PageableUtil.returnASCPageable(number, size, "publishDate");
            Page<Task> todoPage = taskDao.findBySubmiterIdAndStatusIn(adminerid, todoStatus, pageable);
            Page<Task> completePage = taskDao.findBySubmiterIdAndStatusIn(adminerid, completeStatus, pageable);
            model.addAttribute("todoPage", todoPage);
            model.addAttribute("completePage", completePage);
            model.addAttribute("isSubmiter", false);
        }

        return "sysTaskCenter";
    }

    /* 跳转审核页面 */
    @RequestMapping("/toAuditPage")
    public String toAuditPage(Model model, HttpServletRequest request){

        String route = request.getParameter("route");
        log.info("跳转审核页面..." + route);
        int id = Integer.parseInt(request.getParameter("id"));
        if (FinalMappingUtil.PUBLISHJOB.equals(route)){
            Job jobDetail = jobDao.findByTaskId(id);
            Map<String, String> jobdetail = packJobDetailFunction(jobDetail);
            model.addAttribute("detail", jobdetail);
            model.addAttribute("id", jobDetail.getId());
            model.addAttribute("commend", true);
        } else if (FinalMappingUtil.PUBLISHNEWS.equals(route)){
            NewsAnnouncement newsDetail = newsDao.findByTaskId(id);
            Map<String, String> newsdetail = packNewsDetailFunction(newsDetail);
            model.addAttribute("detail", newsdetail);
            model.addAttribute("id", newsDetail.getId());
            model.addAttribute("commend", false);
        }
        model.addAttribute("route", route);   /* 用于审核时判断 */
        return "sysAuditPage";
    }

    /* 跳转修改页面 */
    @RequestMapping("/toEditPage")
    public String toEditPage(HttpServletRequest request, Model model){

        String route = request.getParameter("route");
        int taskId = Integer.parseInt(request.getParameter("taskid"));
        HttpSession session = request.getSession();
        if (FinalMappingUtil.PUBLISHJOB.equals(route)){
            log.info("跳转工作修改页面");
            Job editJob = jobDao.findByTaskId(taskId);
            session.setAttribute("editJobId", editJob.getId());
            return "redirect:/sys/recruite/toEditJobPage";

        } else if (FinalMappingUtil.PUBLISHNEWS.equals(route)){
            log.info("跳转新闻公告修改页面");
            NewsAnnouncement news = newsDao.findByTaskId(taskId);
            session.setAttribute("editNewsId", news.getId());
            return "redirect:/sys/news/toEditNewsPage";

        } else {
            model.addAttribute("errmsg", "登录状态异常，请重新登录！");
            return "redirect:/";
        }
    }


    /* ----------------任务列表页面action---------------- */

    /* 推荐该工作 */
    @RequestMapping("/recommendJobAction")
    public String recommendJobAction(HttpServletRequest request, Model model){
        int jobid = Integer.parseInt(request.getParameter("id"));
        log.info("推荐工作的id：--->" + jobid);
        Job job = jobDao.findById(jobid);
        job.setRecomment(true);
        job.setPublished(true);
        jobDao.saveAndFlush(job);
        int taskid = job.getTaskId();
        Task task = taskDao.findById(taskid);
        task.setStatus("已发布");
        log.info("推荐工作任务");
        taskDao.saveAndFlush(task);
        return "redirect:/sys/task/center";
    }

    /* 审核发布工作，不推荐该工作 */
    @RequestMapping("/publishTaskAction")
    public String publishJobAction(HttpServletRequest request, Model model){
        String route = request.getParameter("route");
        log.info("发布任务" + route);
        if (FinalMappingUtil.PUBLISHJOB.equals(route)){
            int jobId = Integer.parseInt(request.getParameter("id"));
            Job job = jobDao.findById(jobId);
            job.setPublished(true);
            job.setRecomment(false);
            int taskId = job.getTaskId();
            Task jobTask = taskDao.findById(taskId);
            jobTask.setStatus("已发布");
            log.info("发布工作任务审核");
            jobDao.saveAndFlush(job);
            taskDao.saveAndFlush(jobTask);

        } else if (FinalMappingUtil.PUBLISHNEWS.equals(route)){
            int newsId = Integer.parseInt(request.getParameter("id"));
            NewsAnnouncement news = newsDao.findById(newsId);
            news.setPublished(true);      /* 设置发布状态标识位 */
            int taskId = news.getTaskId();
            Task newsTask = taskDao.findById(taskId);
            newsTask.setStatus("已发布");
            log.info("发布新闻任务审核");
            newsDao.saveAndFlush(news);
            taskDao.saveAndFlush(newsTask);
        }
        /* 审核通过后重定向到任务列表页面 */
        return "redirect:/sys/task/center";
    }

    /* 退回发布工作任务，不发布 */
    @RequestMapping("/backTaskAction")
    public String backTaskAction(HttpServletRequest request, Model model){
        String backOpinion = request.getParameter("backOpinion");
        String route = request.getParameter("route");
        if ("".equals(backOpinion) || backOpinion == null){
            backOpinion = "管理员没有填写退回意见";
        }
        log.info("退回任务:" + route + "--的退回意见：" + backOpinion);
        if (FinalMappingUtil.PUBLISHJOB.equals(route)){
            int jobId = Integer.parseInt(request.getParameter("id"));
            Job job = jobDao.findById(jobId);
            job.setPublished(false);
            job.setRecomment(false);
            int taskId = job.getTaskId();
            Task jobTask = taskDao.findById(taskId);
            jobTask.setStatus("被退回");
            jobTask.setBackOpinion(backOpinion);
            log.info("退回工作发布任务");
            jobDao.saveAndFlush(job);
            taskDao.saveAndFlush(jobTask);
        } else if (FinalMappingUtil.PUBLISHNEWS.equals(route)){
            int newsId = Integer.parseInt(request.getParameter("id"));
            NewsAnnouncement news = newsDao.findById(newsId);
            news.setPublished(false);      /* 设置发布状态标识位 */
            int taskId = news.getTaskId();
            Task newsTask = taskDao.findById(taskId);
            newsTask.setStatus("被退回");
            newsTask.setBackOpinion(backOpinion);
            log.info("退回新闻发布任务");
            newsDao.saveAndFlush(news);
            taskDao.saveAndFlush(newsTask);
        }

        return "redirect:/sys/task/center";
    }

    /* 任务发布成功后的下架任务对应条目的操作，任务-撤销，工作-未发布 */
    @RequestMapping("/deleteTaskAction")
    public String deleteTaskAction(HttpServletRequest request, Model model){
        String route = request.getParameter("route");
        int taskId = Integer.parseInt(request.getParameter("taskid"));
        log.info("撤销" + route + "任务，任务id--->" + taskId);
        Task deleteTask = taskDao.findById(taskId);
        deleteTask.setStatus("已撤销");
        taskDao.saveAndFlush(deleteTask);
        if (FinalMappingUtil.PUBLISHJOB.equals(route)){
            Job job = jobDao.findByTaskId(taskId);
            job.setPublished(false);
            jobDao.saveAndFlush(job);
        } else if (FinalMappingUtil.PUBLISHNEWS.equals(route)){
            NewsAnnouncement news = newsDao.findByTaskId(taskId);
            news.setPublished(false);
            newsDao.saveAndFlush(news);
        }
        //重新加载任务列表
        return "redirect:/sys/task/center";
    }


    /* 封装职位详情 */
    private Map<String, String> packJobDetailFunction(Job job){
        Map<String, String> jobMap = new HashMap<>();
        jobMap.put("职位名称", job.getJobName());
        jobMap.put("薪资", job.getJobSalary());
        jobMap.put("工作地点", job.getWorkPosition());
        jobMap.put("工作经验", job.getWorkExpression());
        jobMap.put("学历", job.getEducation());
        jobMap.put("工作简介", job.getWorkSummary());
        jobMap.put("公司名称", job.getCompanyName());
        jobMap.put("发布时间", FormatDate2StringUtil.formatDate2String(job.getPublicTime()));
        jobMap.put("职位诱惑", job.getJobAdvantage());
        if (job.getJobStatementStr() != null){
            jobMap.put("岗位职责", job.getJobStatementStr().replaceAll("&br&", ";"));
        }
        if (job.getJobDemandStr() != null){
            jobMap.put("岗位要求",job.getJobDemandStr().replaceAll("&br&", ";"));
        }
        jobMap.put("工作类别", job.getJobCateName());
        return jobMap;
    }


    /* 封装新闻公告详情 */
    private Map<String, String> packNewsDetailFunction(NewsAnnouncement news){
        Map<String, String> newsMap = new HashMap<>();
        newsMap.put("新闻标题", news.getNewsTitle());
        newsMap.put("新闻内容", news.getNewsContent());
        log.info("新闻内容：--->" + news.getNewsContent());
        newsMap.put("公司名称", news.getCompanyName());
        newsMap.put("发布者", news.getPublisher());
        newsMap.put("发布日期", FormatDate2StringUtil.formatDate2String(news.getPublicDate()));
        return newsMap;
    }
}
