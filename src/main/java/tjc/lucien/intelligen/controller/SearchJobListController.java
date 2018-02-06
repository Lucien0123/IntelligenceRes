package tjc.lucien.intelligen.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.CommonUserDao;
import tjc.lucien.intelligen.dao.JobCategoryDao;
import tjc.lucien.intelligen.dao.JobConditionDao;
import tjc.lucien.intelligen.dao.JobDao;
import tjc.lucien.intelligen.entity.CommonUser;
import tjc.lucien.intelligen.entity.Job;
import tjc.lucien.intelligen.entity.JobCategory;
import tjc.lucien.intelligen.entity.JobCondition;
import tjc.lucien.intelligen.utils.FinalMappingUtil;
import tjc.lucien.intelligen.utils.PackJsonUtil;
import tjc.lucien.intelligen.utils.PageableUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/h5/searchJobList")
public class SearchJobListController {

    public Logger log = Logger.getLogger(SearchJobListController.class);
    @Autowired
    private JobConditionDao jobConditionDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private CommonUserDao commonUserDao;
    @Autowired
    private JobCategoryDao jobCategoryDao;


    /* 获取工作搜索条件 */
    @RequestMapping("/getJobSearchCondition")
    public void getJobSearchCondition(HttpServletResponse response) {

        int pid = -1;
        List<JobCondition> allSearchCondition = jobConditionDao.findAll();
        List<JobCondition> firstCondition = new ArrayList<>();
        List<JSONObject> firConditionArray = new ArrayList<>(); //第一级条件json集合，包含第二级条件
        JSONObject packJSON = new JSONObject();
        //获取第一级的条件
        for (JobCondition obj : allSearchCondition) {
            if (obj.getPid() == -1) {
                firstCondition.add(obj);
            }
        }
        //封装第二级的条件到第一级json
        for (JobCondition firObj : firstCondition) {
            JSONObject firJSON = new JSONObject();   // 定义第一级的json
            List<JSONObject> childrenC = new ArrayList<>();

            for (JobCondition sCondition : allSearchCondition) {
                if (sCondition.getPid() == firObj.getId()) {
                    JSONObject jobj = new JSONObject();
                    jobj.put("cname", sCondition.getConditionStr());
                    childrenC.add(jobj);
                }
            }
            JSONArray childConditions = JSONArray.fromObject(childrenC);
            firJSON.put("fname", firObj.getConditionStr());
            firJSON.put("childConditions", childConditions);
            firConditionArray.add(firJSON);
        }
        JSONArray conditions = JSONArray.fromObject(firConditionArray);
        packJSON.put("conditions", conditions);
        PackJsonUtil.packCorrectCommonJson(packJSON, response);
    }

    /* 根据工作类别获取工作列表 */
    @RequestMapping("/getJobListByJobCate")
    @ResponseBody
    public void getJobListByJobCate(HttpServletResponse response, HttpServletRequest request) {

        log.info("根据工作类别获取工作列表");
        String jobcate = request.getParameter("jobCate");
        int number = 0;
        int size = Integer.parseInt(request.getParameter("pageSize"));
        String numberStr = request.getParameter("pageNumber");
        if (numberStr != null && !"".equals(numberStr)) {
            number = Integer.parseInt(numberStr);
        }
        if (number < 0) {
            number = 0;
        }
        Page<Job> jobPage = jobDao.findByJobCateNameAndPublished(jobcate, true, PageableUtil.returnDESCPageable(number, size, "id"));
        List<Job> allJob = jobDao.findAll();
        List<Job> jobList = jobPage.getContent();
        JSONObject packJson = new JSONObject();
        int totalPageSize = 0;
        if (allJob != null) {
            totalPageSize = allJob.size();
        }
        packJson.put("totalPageSize", totalPageSize);
        packJson.put("jobList", JSONArray.fromObject(jobList));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    /* 根据条件筛选工作列表 */
    @RequestMapping("/getJobListByCondition")
    @ResponseBody
    public void getJobListByCondition(HttpServletRequest request, HttpServletResponse response) {
        log.info("根据选中条件搜索工作");
        String jobCate = request.getParameter("jobCate");
        String jobName = request.getParameter("jobName");
        String jobSalary = request.getParameter("jobSalary");
        String workPosition = request.getParameter("workPosition");
        String workExpression = request.getParameter("workExpression");
        String education = request.getParameter("education");
        String worktype = request.getParameter("worktype");
        String pageNumber = request.getParameter("pageNumber");
        int number = 0;
        int size = 10;
        if (pageNumber != null || !"".equals(pageNumber)) {
            number = Integer.parseInt(pageNumber);
        }
        if (number < 0) {
            number = 0;
        }
        List<String> jobSalarys = new ArrayList<>();
        List<String> workPositions = new ArrayList<>();
        List<String> educations = new ArrayList<>();
        List<String> worktypes = new ArrayList<>();
        List<String> workExpressions = new ArrayList<>();

        String[] workExpressionStrs = workExpression.split(",");  //工作经验要求
        String[] educationStrs = education.split(","); //学历要求
        String[] worktypeStrs = worktype.split(",");   //行业邻域
        //拆封数组根据"-"
        if (jobSalary == null || "".equals(jobSalary)) {       //薪资
            jobSalarys = FinalMappingUtil.JOBSALARYS;
        } else {
            jobSalarys.add(jobSalary);
        }
        if (workPosition == null || "".equals(workPosition)) {  //工作形式
            workPositions = FinalMappingUtil.WORKPOSITIONS;
        } else {
            workPositions.add(workPosition);
        }
        System.out.println(workExpressionStrs.length);
        if ("".equals(workExpression)) {  //工作经验
            workExpressions = FinalMappingUtil.WORKEXPERIENCES;
        } else {
            for (int i = 0; i < workExpressionStrs.length; i++) {
                workExpressions.add(workExpressionStrs[i]);
            }
        }
        System.out.println(educationStrs.length);
        if ("".equals(education)) {  //学历
            educations = FinalMappingUtil.EDUCATIONS;
        } else {
            for (int i = 0; i < educationStrs.length; i++) {
                educations.add(educationStrs[i]);
            }
        }
        System.out.println(worktypeStrs.length);
        if ("".equals(worktype)) {   //行业邻域
            worktypes = FinalMappingUtil.BUSSINESSFORM;
        } else {
            for (int i = 0; i < worktypeStrs.length; i++) {
                worktypes.add(worktypeStrs[i]);
            }
        }

        //查询工作列表
        List<Job> jobList = new ArrayList<>();
        int totalPage = 0;
        int pagen = 0;   //当前页码
        if (jobCate == null || "".equals(jobCate)) { //工作类别为空，对搜索关键字进行匹配（优先匹配工作类别）
            boolean matchFlag = matchSearchKeyCate(jobName);
            if (matchFlag) {
                //匹配工作类别
                Page<Job> jobPage = jobDao.findByJobCateNameAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(jobName, jobSalarys, workPositions, true, workExpressions, educations, worktypes, PageableUtil.returnDESCPageable(number, size, "id"));
                jobList = jobPage.getContent();
                totalPage = jobPage.getTotalPages();
                pagen = jobPage.getNumber();
            } else {
                String likeStr = "%" + jobName + "%";
                Page<Job> jobPage = jobDao.findByJobNameLikeAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(likeStr, jobSalarys, workPositions, true, workExpressions, educations, worktypes, PageableUtil.returnDESCPageable(number, size, "id"));
                jobList = jobPage.getContent();
                totalPage = jobPage.getTotalPages();
                pagen = jobPage.getNumber();
            }
        } else if (!"".equals(jobCate) && "".equals(jobName)) { //工作类别不空，工作名称空
            Page<Job> jobPage = jobDao.findByJobCateNameAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(jobCate, jobSalarys, workPositions, true, workExpressions, educations, worktypes, PageableUtil.returnDESCPageable(number, size, "id"));
            jobList = jobPage.getContent();
            totalPage = jobPage.getTotalPages();
            pagen = jobPage.getNumber();
        } else {    //工作类别不空，工作名称不空
            String likeStr = "%" + jobName + "%";
            Page<Job> jobPage = jobDao.findByJobNameLikeAndJobCateNameAndJobSalaryInAndWorktypeInAndPublishedAndWorkExpressionInAndEducationInAndComMainBusinessIn(likeStr, jobCate, jobSalarys, workPositions, true, workExpressions, educations, worktypes, PageableUtil.returnDESCPageable(number, size, "id"));
            jobList = jobPage.getContent();
            totalPage = jobPage.getTotalPages();
            pagen = jobPage.getNumber();
        }

        //封装json
        List<JSONObject> jobArray = new ArrayList<>();
        for (Job job : jobList) {
            JSONObject jobObj = PackJsonUtil.packJobJson(job);
            jobArray.add(jobObj);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("searchJobList", JSONArray.fromObject(jobArray));
        packJson.put("totalPage", totalPage);
        packJson.put("pageNumber", pagen);
        PackJsonUtil.packCorrectCommonJson(packJson, response);

    }

    /* 获取最近浏览的三条工作 */
    @RequestMapping("/getRecentlyViewedJobs")
    @ResponseBody
    public void getRecentlyViewedJobs(HttpServletRequest request, HttpServletResponse response) {
        String account = request.getParameter("username");
        CommonUser cur_user = commonUserDao.findByAccount(account);
        String viewJobsId = cur_user.getRecentlyViewJobIdsStr();
        List<JSONObject> jobJsonList = new ArrayList<>();
        List<Job> jobs = new ArrayList<>();
        String[] ids = viewJobsId.split(",");
        if (ids.length > 0) {
            jobs = jobDao.findByPublishedAndIdIn(true, ids);
            log.info("最近浏览工作的长度：" + jobs.size());
        } else {
            jobs = jobDao.findByPublished(true, PageableUtil.returnDESCPageable(0, 3, "id")).getContent();
        }
        for (Job job : jobs) {
            JSONObject obj = new JSONObject();
            obj.put("jobId", job.getId());
            obj.put("jobName", job.getJobName());
            obj.put("jobSalary", job.getJobSalary());
            obj.put("comName", job.getCompanyName());
            jobJsonList.add(obj);
        }

        JSONObject packJson = new JSONObject();
        packJson.put("recentlyViewJobs", JSONArray.fromObject(jobJsonList));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }


    /* 工具方法 */
    private boolean matchSearchKeyCate(String jobName) {

        log.info("工作类别为空，对jobName进行匹配：" + jobName);
        //获取所有的工作类别
        List<JobCategory> cateList = jobCategoryDao.findByParentIdNot(-1);
        for (JobCategory cate : cateList) {
            if (cate.getJobCateName().equals(jobName)) {
                return true;
            }
        }
        return false;
    }
}
