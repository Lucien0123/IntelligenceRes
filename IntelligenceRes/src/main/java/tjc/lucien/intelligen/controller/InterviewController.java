package tjc.lucien.intelligen.controller;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.CommonUserDao;
import tjc.lucien.intelligen.dao.InterviewDao;
import tjc.lucien.intelligen.dao.JobDao;
import tjc.lucien.intelligen.entity.CommonUser;
import tjc.lucien.intelligen.entity.Interview;
import tjc.lucien.intelligen.entity.Job;
import tjc.lucien.intelligen.utils.FinalMappingUtil;
import tjc.lucien.intelligen.utils.FormatDate2StringUtil;
import tjc.lucien.intelligen.utils.PackJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/6.
 * 面试
 */
@Controller
@RequestMapping("/h5/interview")
public class InterviewController {

    public Logger log = Logger.getLogger(InterviewController.class);

    @Autowired
    private InterviewDao interviewDao;
    @Autowired
    private CommonUserDao commonUserDao;
    @Autowired
    private JobDao jobDao;


    /* 投递简历操作 */
    @RequestMapping("/sendResumeAction")
    @ResponseBody
    public void sendResumeAction(HttpServletRequest request, HttpServletResponse response){

        String account = request.getParameter("account");
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        CommonUser commonUser = commonUserDao.findByAccount(account);
        Job job = jobDao.findById(jobId);
        Interview interview = new Interview();
        //JSONObject interviewJson = PackJsonUtil.packInterview(interview, commonUser, job);
        interview.setCommonuserName(commonUser.getRealname());
        interview.setCommonuserId(commonUser.getId());
        interview.setCommonuserAccount(account);
        String occupation = commonUser.getOccupation();
        if (occupation == null || "".equals(occupation)){
            occupation = "自由职业";
        }
        interview.setOccupation(occupation);
        interview.setTelphone(commonUser.getTelphone());
        interview.setResumeLink(commonUser.getAccessResume());
        interview.setJobName(job.getJobName());
        interview.setJobId(job.getId());
        interview.setPublishCompanyId(job.getCompanyId());
        interview.setCompanyName(job.getCompanyName());
        interview.setPublisher(job.getPublicer());
        interview.setDeliverDate(new Date());
        interview.setDeliverDateStr(FormatDate2StringUtil.formatDate2String(new Date()));
        interview.setStatus(FinalMappingUtil.DELIVERRED);   //设置状态为已投递

        interviewDao.save(interview);
        PackJsonUtil.packCorrectCommonJson(new JSONObject(), response);

    }
}
