package tjc.lucien.intelligen.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import tjc.lucien.intelligen.entity.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3.
 */
public class PackJsonUtil {

    public static Logger log = Logger.getLogger(PackJsonUtil.class);

    public static void packCorrectCommonJson(JSONObject object, HttpServletResponse response){

        response.setContentType("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        JSONObject paramObject = new JSONObject();
        paramObject.put("param", object);
        jsonObject.put("responsecode", "1");
        jsonObject.put("responseMsg", "success");
        jsonObject.put("response", paramObject);
        log.info("操作成功,返回json");
        log.info(jsonObject);
        try {
            response.getWriter().println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void packErrorCommonJson(String errMsg, HttpServletResponse response){
        response.setContentType("application/json;charset=UTF-8");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("responsecode", "0");
        jsonObject.put("responseMsg", errMsg);
        jsonObject.put("response", null);
        log.info("操作失败,返回json");
        log.info(jsonObject);
        try {
            response.getWriter().println(jsonObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* 封装推荐工作，最新工作，热门工作列表JSONArray */
    public static JSONObject packHomePageJobList(List<Job> jobList){
        JSONObject jobListObj = new JSONObject();

        List<JSONObject> jObjList = new ArrayList<>();
        for (Job job : jobList) {
            JSONObject jobj = packJobJson(job);
            jObjList.add(jobj);
        }
        JSONArray recentJobsJSON = JSONArray.fromObject(jObjList);
        jobListObj.put("jobList", recentJobsJSON);

        return jobListObj;
    }


    /**
     * 切割用&br&分割的字符串，转化为JSONArray
     * @param splitStr
     * @return
     */
    public static JSONArray splitStrToJSONArray(String splitStr){

        List<JSONObject> objList = new ArrayList<>();
        log.info("转化为JSONArray的字符串为:" + splitStr);
        String[] strs = splitStr.split("&br&");
        for (int i=0;i<strs.length;i++){
            JSONObject strObj = new JSONObject();
            strObj.put("labelStr", strs[i]);
            objList.add(strObj);
        }

        JSONArray jsonArray = JSONArray.fromObject(objList);
        return jsonArray;
    }


    /**
     * 切割用&br&分割的字符串，转化为ArrayList
     * @param splitStr
     * @return
     */
    public static List<String> splitStrToArrayList(String splitStr){

        List<String> objList = new ArrayList<>();
        log.info("转化为JSONArray的字符串为:" + splitStr);
        if (splitStr == null){
            splitStr = "";
        }
        String[] strs = splitStr.split("&br&");
        for (int i=0;i<strs.length;i++){
            objList.add(strs[i]);
        }

        return objList;
    }

    /**
     * 封装工作
     * @param job
     * @return
     */
    public static JSONObject packJobJson(Job job){
        JSONObject jobJson = new JSONObject();
        //封装job的json
        jobJson.put("jobId", job.getId());
        jobJson.put("jobName", job.getJobName());
        jobJson.put("publishTime", FormatDate2StringUtil.formatDate2String(job.getPublicTime()));
        jobJson.put("jobSalary", job.getJobSalary());
        jobJson.put("workExperience", job.getWorkExpression());
        jobJson.put("education", job.getEducation());
        jobJson.put("jobAdvantage", job.getJobAdvantage());
        jobJson.put("worktype", job.getWorktype());   //实习兼职
        jobJson.put("publicer", job.getPublicer());
        jobJson.put("jobCateName", job.getJobCateName());
        jobJson.put("isRecomment", job.isRecomment());

        jobJson.put("companyMainBusiness", job.getComMainBusiness());
        jobJson.put("companyForm", job.getComForm());
        jobJson.put("companyName", job.getParkCompany().getComName());
        jobJson.put("mainBusiness", job.getParkCompany().getComMainBusiness());
        jobJson.put("companySignal", job.getParkCompany().getComSignal());
        jobJson.put("companyId", job.getParkCompany().getId());
        jobJson.put("companyAddress", job.getParkCompany().getComAddress());
        jobJson.put("staffScale", job.getParkCompany().getComStaffScale());  //人员规模
        jobJson.put("compHomepageLink", job.getParkCompany().getComHomepageLink());
        jobJson.put("jobStatement", PackJsonUtil.splitStrToJSONArray(job.getJobStatementStr()));
        jobJson.put("demand",PackJsonUtil.splitStrToJSONArray(job.getJobDemandStr()));
        jobJson.put("workLabels", PackJsonUtil.splitStrToJSONArray(job.getWorkLabels()));

        return jobJson;
    }

    /**
     * 封装问题内容
     * @param question
     * @param firstComments
     * @return
     */
    public static JSONObject packQuestion(Question question, List<FirstComment> firstComments){
        JSONObject quesJson = new JSONObject();
        JSONObject quizzerObj = new JSONObject();
        quesJson.put("questionId", question.getId());
        quesJson.put("questionContent", question.getQuesContent());
        //封装提问者信息
        CommonUser quizzer = question.getQuizzer();
        quizzerObj.put("quizzerId", quizzer.getId());
        quizzerObj.put("quizzerAccount", quizzer.getAccount());
        quizzerObj.put("quizzerName", quizzer.getRealname());
        quesJson.put("quizzer", quizzerObj);
        quesJson.put("quizDate", FormatDate2StringUtil.formatDate2String(question.getQuizDate()));
        quesJson.put("questionLabels", PackJsonUtil.splitStrToJSONArray(question.getQuestionLabels()));
        quesJson.put("quesCompanyId", question.getRelatedCompany().getId());
        quesJson.put("quesCompanyName", question.getRelatedCompany().getComName());
        quesJson.put("answerCount", firstComments.size());

        //封装问题回复
        List<JSONObject> firCommentArray = new ArrayList<>();
        for (FirstComment f : firstComments){

            JSONObject firObj = new JSONObject();
            firObj.put("firCommentId", f.getId());
            firObj.put("firCommentDate", FormatDate2StringUtil.formatDate2String(f.getCommentDate()));
            firObj.put("firCommentContent", f.getCommentContent());
            CommonUser replier = f.getCommenter();
            String replierName = replier.getRealname();
            String occupation = replier.getOccupation();
            if (replierName == null || "".equals(replierName)){
                replierName = "匿名用户" + replier.getAccount();
            }
            if (occupation == null || "".equals(occupation)){
                occupation = "自由职业";
            }
            firObj.put("firReplierAccount", replier.getAccount());
            firObj.put("firReplierName", replierName);
            firObj.put("firReplierOccupation", occupation);
            firObj.put("firReplierHead", replier.getHeadPortrait());

            Set<SecondComment> sCommentSet = f.getSecondComments();
            List<JSONObject> secommentArray = new ArrayList<>();
            Iterator<SecondComment> iterator = sCommentSet.iterator();
            while (iterator.hasNext()){
                SecondComment s = iterator.next();
                JSONObject sobj = new JSONObject();
                sobj.put("firCommentId", f.getId());
                sobj.put("secCommentId", s.getId());
                sobj.put("secReplier", s.getReplier());
                sobj.put("secReplierAccount", s.getReplyAccount());
                sobj.put("secBeReplier", s.getBeReplier());
                sobj.put("secReplyContent", s.getReplyContent());
                secommentArray.add(sobj);
            }
            if (!secommentArray.isEmpty()){
                secommentArray = SortCollectionUtil.sortJsonListBy(secommentArray, "secCommentId");
            }
            firObj.put("secondComment", JSONArray.fromObject(secommentArray));
            firCommentArray.add(firObj);
        }
        quesJson.put("replyList", JSONArray.fromObject(firCommentArray));

        return quesJson;
    }


    public static JSONObject packNews(NewsAnnouncement news){

        JSONObject newsJson = new JSONObject();
        newsJson.put("newsId", news.getId());
        newsJson.put("publicDate", FormatDate2StringUtil.formatDate2String(news.getPublicDate()));
        newsJson.put("newsTitle", news.getNewsTitle());
        newsJson.put("newsContent", news.getNewsContent());
        newsJson.put("viewCount", news.getViewCount());

        String compantNameStr = news.getCompanyName();
        String publisherName = news.getPublisher();
        if (compantNameStr ==null || "".equals(compantNameStr)){
            compantNameStr = FinalMappingUtil.PARKNAME;
            if (publisherName ==null || "".equals(publisherName)){
                publisherName = "园区管理员";
            }
        } else {
            if (publisherName ==null || "".equals(publisherName)){
                publisherName = compantNameStr + "管理员";
            }
        }
        newsJson.put("publisher", publisherName);
        newsJson.put("companyName", compantNameStr);

        return newsJson;
    }


    public static JSONObject packMessage(MessageEntity entity){

        JSONObject messageJson = new JSONObject();
        messageJson.put("id", entity.getId());
        messageJson.put("account", entity.getAccount());
        messageJson.put("content", entity.getContent());
        messageJson.put("createDate", FormatDate2StringUtil.formatDate2String(entity.getCreateDate()));
        return messageJson;
    }

}
