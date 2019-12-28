package tjc.lucien.intelligen.controller;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tjc.lucien.intelligen.dao.JobAppraiseDao;
import tjc.lucien.intelligen.dao.JobDao;
import tjc.lucien.intelligen.dao.ParkCompanyDao;
import tjc.lucien.intelligen.entity.Job;
import tjc.lucien.intelligen.entity.JobAppraise;
import tjc.lucien.intelligen.utils.FormatDate2StringUtil;
import tjc.lucien.intelligen.utils.PackJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Administrator on 2017/3/16.
 */
@Controller
@RequestMapping("/h5/jobdetail")
public class JobDetailPageController {

    public Logger log = Logger.getLogger(JobDetailPageController.class);
    @Autowired
    private JobDao jobDao;
    @Autowired
    private ParkCompanyDao parkCompanyDao;
    @Autowired
    private JobAppraiseDao jobAppraiseDao;

    /* 根据id获取工作详情 */
    @RequestMapping("/getJobDetail")
    public void getJobDetail(HttpServletResponse response, HttpServletRequest request){
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        log.info("获取工作的id为：" + jobId);
        Job job = jobDao.findById(jobId);
        log.info("获取工作的名称：" + job.getJobName());
        JSONObject jobJSON = PackJsonUtil.packJobJson(job);
        JSONObject packJSON = new JSONObject();
        packJSON.put("JobDetail", jobJSON);
        PackJsonUtil.packCorrectCommonJson(packJSON, response);
    }

    /* 根据工作id获取最赞的一条评论显示 */
    @RequestMapping("/getMostUsefulAppraise")
    public void getMostUsefulAppraise(HttpServletRequest request, HttpServletResponse response){
        int jobid = Integer.parseInt(request.getParameter("jobId"));
        log.info("工作id="+ jobid +"  的获取最有用评论评论");
        JobAppraise mostJobAppraise = jobAppraiseDao.findTop1ByJobIdOrderByApproveCount(jobid);
        JSONObject packJson = new JSONObject();
        packJson.put("appraiser", mostJobAppraise.getCommonUser().getRealname());
        packJson.put("appraiserHead", mostJobAppraise.getCommonUser().getHeadPortrait());
        packJson.put("descScore", mostJobAppraise.getDescScore());
        packJson.put("interviewerScore", mostJobAppraise.getInterviewerScore());
        packJson.put("enviroScore", mostJobAppraise.getEnviroScore());
        packJson.put("publishDate", FormatDate2StringUtil.formatDate2String(mostJobAppraise.getPublishDate()));
        packJson.put("labelStrs", PackJsonUtil.splitStrToJSONArray(mostJobAppraise.getLabelStr()));
        packJson.put("interviewProcess",mostJobAppraise.getInterviewProcess());
        packJson.put("otherAppraise", mostJobAppraise.getOtherAppraise());
        packJson.put("approveCount", mostJobAppraise.getApproveCount());
        packJson.put("appraiseId", mostJobAppraise.getId());
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }
}
