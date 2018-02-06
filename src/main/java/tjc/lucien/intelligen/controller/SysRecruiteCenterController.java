package tjc.lucien.intelligen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.AdminerDao;
import tjc.lucien.intelligen.dao.JobCategoryDao;
import tjc.lucien.intelligen.dao.JobDao;
import tjc.lucien.intelligen.dao.TaskDao;
import tjc.lucien.intelligen.entity.*;
import tjc.lucien.intelligen.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by Administrator on 2017/3/20.
 */

@Controller
@RequestMapping("/sys/recruite")
public class SysRecruiteCenterController {

    private Logger log = Logger.getLogger(SysRecruiteCenterController.class);
    @Autowired
    private JobDao jobDao;
    @Autowired
    private JobCategoryDao jobCategoryDao;
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private AdminerDao adminerDao;

    /* 跳转到招聘中心首页 */
    @RequestMapping("/center")
    public String sysRecruiteCenter(Model model, HttpServletRequest request) {
        log.info("跳转招聘中心，已发布工作列表页面");
        int size = 8; //每页要显示的记录条数
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
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            Page<Job> jobPage = jobDao.findByPublished(true, PageableUtil.returnDESCPageable(number, size, "id"));
            model.addAttribute("jobPage", jobPage);
            log.info("园区管理员，不可发布职位");
            model.addAttribute("canPublish", false);
        } else if (cur_adminer.getPower() == PowerMappingUtil.BUSINESSMANAGER){
            String comName = cur_adminer.getParkCompany().getComName();
            Page<Job> jobPage = jobDao.findByPublishedAndCompanyName(true, comName, PageableUtil.returnDESCPageable(number, size, "id"));
            model.addAttribute("jobPage", jobPage);
            log.info("可发布职位");
            model.addAttribute("canPublish", true);
        }
        return "sysRecruitmentCenter";
    }

    /* 跳转发布新的职位页面 */
    @RequestMapping("/publicNewJobPage")
    public String sysPublicJobPage(Model model, HttpServletRequest request) {
        log.info("跳转发布工作页面");
        String isEdit = request.getParameter("isEdit");
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            model.addAttribute("isSelectAuditor", false);
        } else {
            List<Adminer> adminers = adminerDao.findByPower(PowerMappingUtil.ADMIN);
            model.addAttribute("adminers", adminers);
            if ("edit".equals(isEdit)){
                model.addAttribute("isSelectAuditor", false);
            } else {
                model.addAttribute("isSelectAuditor", true);
            }
        }
        List<JobCategory> jobCategories = jobCategoryDao.findByParentIdNot(-1);
        model.addAttribute("jobCategorieList", jobCategories);
        Calendar calendar = Calendar.getInstance();
        model.addAttribute("curDate", calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
        return "sysPublishJobPage";
    }

    /* 发布任务被退回后，跳转修改页面 */
    @RequestMapping("/toEditJobPage")
    public String toEditJobPage(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        int jobId = (int) session.getAttribute("editJobId");
        log.info("修改工作的id为：" + jobId);
        Job job = jobDao.findById(jobId);
        int taskId = job.getTaskId();
        Task backTask = taskDao.findById(taskId);
        String backOpinion = backTask.getBackOpinion();
        model.addAttribute("editJob", job);
        model.addAttribute("backOpinion", backOpinion);
        model.addAttribute("curDate", FormatDate2StringUtil.formatDate2String(new Date()));
        model.addAttribute("demandList", PackJsonUtil.splitStrToArrayList(job.getJobDemandStr()));
        model.addAttribute("statementList", PackJsonUtil.splitStrToArrayList(job.getJobStatementStr()));
        return "sysEditJobPage";
    }


    /* --------------业务操作控制器------------------ */

    /* 发布新的工作 */
    @RequestMapping("/publicNewJob")
    @ResponseBody
    public String publicNewJobAction(Job newJob, HttpServletRequest request) {
        HttpSession session = request.getSession();
        String workLabelStr = "高薪&br&周末双休&br&五险一金&br&年末双薪";
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");

        //仅公司管理员的权限可以发布职位
        if(cur_adminer.getPower() == PowerMappingUtil.BUSINESSMANAGER){
            ParkCompany parkCompany = cur_adminer.getParkCompany();
            int parkId = parkCompany.getId();
            String jobname = newJob.getJobName();
            log.info("公司id：" + parkId + "---工作名称：" + jobname);
            log.info(newJob.getJobDemandStr() + "--" + newJob.getJobStatementStr());
            List<Job> jobList = jobDao.findByJobNameAndCompanyId(jobname, parkId);
            /* 工作名称在本公司不重复 */
            if (jobList.size() <= 0){
                /* 发布工作 */
                if (newJob.getAuditorId() < 0){
                    return "PLEASESELECTAUDITOR";
                }
                int cateId = Integer.parseInt(request.getParameter("jobCateId"));
                JobCategory jobCategory = jobCategoryDao.findById(cateId);
                newJob.setJobCateName(jobCategory.getJobCateName());
                Adminer auditor = adminerDao.findById(newJob.getAuditorId());
                newJob.setCompanyId(parkId);
                String auditorName = auditor.getRealname();
                String publisher = cur_adminer.getRealname();
                if (auditorName == null || "".equals(auditorName)){
                    auditorName = "园区管理员" + auditor.getAccount();
                }
                if (publisher == null || "".equals(publisher)){
                    publisher = "企业管理员" + cur_adminer.getAccount();
                }
                newJob.setAuditorName(auditorName);
                newJob.setPublicer(publisher);
                newJob.setWorkPosition("天津市-西青区-产业园区");
                newJob.setParkCompany(parkCompany);
                newJob.setCompanyName(parkCompany.getComName());
                newJob.setComMainBusiness(parkCompany.getComMainBusiness());
                newJob.setComForm(parkCompany.getComForm());
                newJob.setPublicTime(new Date());
                newJob.setWorkLabels(workLabelStr);
                Task taskJob = new Task();
                taskJob.setPublishDate(newJob.getPublicTime());
                taskJob.setPublishDateStr(FormatDate2StringUtil.formatDate2String(newJob.getPublicTime()));
                taskJob.setStatus("已提交");
                taskJob.setTaskType(FinalMappingUtil.PUBLISHJOB);
                taskJob.setTaskTitle(newJob.getJobName());
                taskJob.setAuditor(auditor);
                taskJob.setAuditorName(auditor.getRealname());
                taskJob.setSubmiter(cur_adminer);
                taskJob.setSubmiterName(cur_adminer.getRealname());
                Task task = taskDao.save(taskJob);
                newJob.setTaskId(task.getId());
                newJob.setPublished(false);
                jobDao.saveAndFlush(newJob);
                return "SUCCESS";
            } else {
                return "JOBNAMEEXIST";
            }
        } else{
            return "POWERLIMIT";
        }
    }


    /* 被退回后的修改工作后重新发布 */
    @RequestMapping("/republishJobAction")
    @ResponseBody
    public String republishJobJobAction(Job newJob, Model model, HttpServletRequest request) {
        log.info("发布新的工作名称为：" + newJob.getJobName());
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        int oldJobId = Integer.parseInt(request.getParameter("id"));

        //仅公司管理员的权限可以发布职位
        if(cur_adminer.getPower() == PowerMappingUtil.BUSINESSMANAGER){
            ParkCompany parkCompany = cur_adminer.getParkCompany();
            int parkId = parkCompany.getId();
            String jobname = newJob.getJobName();
            List<Job> jobList = jobDao.findByJobNameAndCompanyId(jobname, parkId);
            /* 工作名称在本公司不重复 */
            if (jobList.size()==1 && jobList.get(0).getJobName().equals(jobname)){

                Job oldJob = jobDao.findById(oldJobId);
                Date curDate = new Date();
                oldJob.setJobName(newJob.getJobName());
                oldJob.setJobSalary(newJob.getJobSalary());
                oldJob.setEducation(newJob.getEducation());
                oldJob.setWorkExpression(newJob.getWorkExpression());
                oldJob.setJobAdvantage(newJob.getJobAdvantage());
                oldJob.setPublicTime(curDate);
                oldJob.setJobDemandStr(newJob.getJobDemandStr());
                oldJob.setJobStatementStr(newJob.getJobStatementStr());

                Task taskJob = taskDao.findById(oldJob.getTaskId());
                taskJob.setPublishDate(oldJob.getPublicTime());
                taskJob.setPublishDateStr(FormatDate2StringUtil.formatDate2String(oldJob.getPublicTime()));
                taskJob.setStatus("已修改");
                taskJob.setTaskTitle(oldJob.getJobName());
                taskDao.saveAndFlush(taskJob);
                jobDao.saveAndFlush(oldJob);
                return "SUCCESS";
            } else {
                return "JOBNAMEEXIST";
            }
        } else{
            return "POWERLIMIT";
        }
    }

    /* 撤销职位 */
    @RequestMapping("/deleteJobAction")
    @ResponseBody
    public String deleteJobAction(HttpServletRequest request, Model model){
        int jobid = Integer.parseInt(request.getParameter("jobId"));
        Job job = jobDao.findById(jobid);
        Task task = taskDao.findById(job.getTaskId());
        job.setPublished(false);
        task.setStatus("已撤销");
        jobDao.saveAndFlush(job);
        taskDao.saveAndFlush(task);
        return "SUCCESS";
    }
}
