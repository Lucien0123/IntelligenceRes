package tjc.lucien.intelligen.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tjc.lucien.intelligen.dao.JobAppraiseDao;
import tjc.lucien.intelligen.entity.JobAppraise;
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
@RequestMapping("/h5/moreAppraise")
public class MoreAppraiseController {

    public Logger log = Logger.getLogger(MoreAppraiseController.class);
    @Autowired
    private JobAppraiseDao jobAppraiseDao;

    /* 分页获取更多工作评价 */
    @RequestMapping("/getMoreAppraiseByPage")
    public void getMoreAppraiseByPage(HttpServletRequest request, HttpServletResponse response){

        int pageNumber = Integer.parseInt(request.getParameter("pagenumber"));
        int pagesize = Integer.parseInt(request.getParameter("pagesize"));
        Page<JobAppraise> jobAppraises = jobAppraiseDao.findByJobId(PageableUtil.returnDESCPageable(pageNumber, pagesize, "id"));

        List<JSONObject> jobAppraiseList = new ArrayList<>();
        for (JobAppraise appraise : jobAppraises) {
            /* 封装每一条工作评价 */
            JSONObject appraiseJson = new JSONObject();
            appraiseJson.put("descScore", appraise.getDescScore());
            appraiseJson.put("interviewerScore", appraise.getInterviewerScore());
            appraiseJson.put("enviroScore", appraise.getEnviroScore());
            appraiseJson.put("interviewProcess", appraise.getInterviewProcess());
            appraiseJson.put("otherAppraise", appraise.getOtherAppraise());
            appraiseJson.put("approveCount", appraise.getApproveCount());
            appraiseJson.put("publishDate", appraise.getPublishDate());
            appraiseJson.put("appraiseLabels", PackJsonUtil.splitStrToJSONArray(appraise.getLabelStr()));
            appraiseJson.put("jobName", appraise.getJob().getJobName());
            appraiseJson.put("publisher", appraise.getCommonUser().getRealname());
            appraiseJson.put("headPortrait", appraise.getCommonUser().getHeadPortrait());
            jobAppraiseList.add(appraiseJson);
        }

        JSONObject packJSON = new JSONObject();
        packJSON.put("jobAppraises", JSONArray.fromObject(jobAppraiseList));
        PackJsonUtil.packCorrectCommonJson(packJSON, response);
    }

    /* 点赞操作 */
    @RequestMapping("/approveJobAppraise")
    public void approveJobAppraise(HttpServletRequest request, HttpServletResponse response){
        int appraiseId = Integer.parseInt(request.getParameter("appraiseId"));
        log.info("点赞评价的id：" + appraiseId);
        JobAppraise appraise = jobAppraiseDao.findById(appraiseId);
        appraise.setApproveCount(appraise.getApproveCount()+1);
        jobAppraiseDao.saveAndFlush(appraise);
        JSONObject packJson = new JSONObject();
        packJson.put("appraiseId", appraise.getId());
        packJson.put("approveCount", appraise.getApproveCount());
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }
}
