package tjc.lucien.intelligen.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.*;
import tjc.lucien.intelligen.entity.*;
import tjc.lucien.intelligen.utils.FinalMappingUtil;
import tjc.lucien.intelligen.utils.PackJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/20.
 */
@Controller
@RequestMapping("/h5/parkcompany")
public class ParkCompanyController {

    public Logger log = Logger.getLogger(ParkCompanyController.class);
    @Autowired
    private JobDao jobDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private CommonUserDao commonUserDao;
    @Autowired
    private ParkCompanyDao parkCompanyDao;
    @Autowired
    private FirstCommentDao firstCommentDao;

    /* 获取公司首页信息 */
    @RequestMapping("/getParkCompanyInfor")
    @ResponseBody
    public void getParkCompanyInfor(HttpServletRequest request, HttpServletResponse response){

        int parkId = Integer.parseInt(request.getParameter("companyId"));
        log.info("公司id-->" + parkId);
        ParkCompany cur_company = parkCompanyDao.findById(parkId);
        JSONObject comJson = new JSONObject();
        comJson.put("companyId", cur_company.getId());
        comJson.put("companyName", cur_company.getComName());
        comJson.put("companyHomepageLink", cur_company.getComHomepageLink());
        comJson.put("companyDesc", cur_company.getComDesc());
        comJson.put("companySignal", cur_company.getComSignal());
        comJson.put("companyAddress", cur_company.getComAddress());
        comJson.put("companyMainBusiness", cur_company.getComMainBusiness());
        comJson.put("companyForm", cur_company.getComForm());
        comJson.put("companyStaffScale", cur_company.getComStaffScale());
        comJson.put("companyLabels", cur_company.getCompanyLabels());
        int jobsCount = jobDao.findByPublishedAndCompanyId(true, cur_company.getId()).size();
        int questionCount = questionDao.findByCompanyId(cur_company.getId()).size();
        comJson.put("recruiteJobsCount", jobsCount);
        comJson.put("questionCount", questionCount);
        JSONObject packJson = new JSONObject();
        packJson.put("companyBaseInfor", comJson);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    /* 获取园区企业正在招聘的职位,no分页 */
    @RequestMapping("/getRecruiteJobList")
    @ResponseBody
    public void getRecruiteJobList(HttpServletRequest request, HttpServletResponse response){
        int comId = Integer.parseInt(request.getParameter("companyId"));
        List<JSONObject> jobJsonList = new ArrayList<>();
        List<Job> jobList = jobDao.findByPublishedAndCompanyId(true, comId);
        for (Job job : jobList) {
            JSONObject obj = PackJsonUtil.packJobJson(job);
            jobJsonList.add(obj);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("recruiteJobs", JSONArray.fromObject(jobJsonList));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    /* 获取言职社区提问问题 */
    @RequestMapping("/getComQuestions")
    @ResponseBody
    public void getComQuestions(HttpServletRequest request, HttpServletResponse response){

        int comId = Integer.parseInt(request.getParameter("companyId"));
        List<JSONObject> jobJsonList = new ArrayList<>();
        List<Question> questionList = questionDao.findByCompanyId(comId);
        for (Question question : questionList) {
            List<FirstComment> firComment = firstCommentDao.findByQuestionId(question.getId());
            JSONObject obj = PackJsonUtil.packQuestion(question, firComment);
            jobJsonList.add(obj);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("questionList", JSONArray.fromObject(jobJsonList));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    /* 提问操作 */
    @RequestMapping("/askQuestionAction")
    @ResponseBody
    public void askQuestionAction(HttpServletRequest request, HttpServletResponse response){

        int comId = Integer.parseInt(request.getParameter("comId"));
        String quizzerAccount = request.getParameter("userId");
        String quesContent = request.getParameter("quesContent");
        String questionLables = FinalMappingUtil.QUESTIONLABEL;
        CommonUser quizzer = commonUserDao.findByAccount(quizzerAccount);
        ParkCompany com = parkCompanyDao.findById(comId);
        Question question = new Question();
        question.setQuesContent(quesContent);
        question.setQuestionLabels(questionLables);
        question.setQuizDate(new Date());
        question.setQuizzer(quizzer);
        question.setRelatedCompany(com);
        question.setCompanyId(com.getId());
        questionDao.save(question);
        PackJsonUtil.packCorrectCommonJson(new JSONObject(), response);
    }
}
