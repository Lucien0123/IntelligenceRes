package tjc.lucien.intelligen.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.JobCategoryDao;
import tjc.lucien.intelligen.dao.JobDao;
import tjc.lucien.intelligen.entity.Job;
import tjc.lucien.intelligen.entity.JobCategory;
import tjc.lucien.intelligen.utils.PackJsonUtil;
import tjc.lucien.intelligen.utils.PageableUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lucien on 2017/3/1.
 */
@Controller
@Configuration
@ComponentScan("tjc.lucien.intelligen.dao")
@RequestMapping(value = "/h5/home")
public class HomePageController {

    public Logger log = Logger.getLogger(HomePageController.class);
    @Autowired
    private JobCategoryDao jobCategoryDao;
    @Autowired
    private JobDao jobDao;

    /* 获取所有工作类别，封装成json，两级 */
    @RequestMapping("/getJobCates")
    @ResponseBody
    public void getJobCates(HttpServletResponse response){
        log.info("获取工作类别");
        List<JobCategory> cates = jobCategoryDao.findAll();
        List<JobCategory> firstList = new ArrayList<>();
        List<JSONObject> cateArray = new ArrayList<>();
        if (cates != null){
            for (JobCategory cate : cates) {
                if (cate.getParentId() == -1){
                    firstList.add(cate);
                }
            }
        }
        for (JobCategory firCate : firstList) {
            JSONObject firJSON = new JSONObject();   // 定义第一级的json
            List<JSONObject> childrenC = new ArrayList<>();
            for (JobCategory cate : cates) {
                if (cate.getParentId() == firCate.getId()){
                    JSONObject cateJson = new JSONObject();
                    cateJson.put("cateId", cate.getId());
                    cateJson.put("cateStr", cate.getJobCateName());
                    childrenC.add(cateJson);
                }
            }
            JSONArray childCates = JSONArray.fromObject(childrenC);
            firJSON.put("pcate", firCate.getJobCateName());
            firJSON.put("childcates", childCates);
            cateArray.add(firJSON);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("cates", JSONArray.fromObject(cateArray));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }


    /* 获取首页热门搜过工作类别 */
    @RequestMapping(value = "/getHotSearch")
    @ResponseBody
    public void getHotSearch(HttpServletRequest request, HttpServletResponse response) {
        log.info("获取热门搜索工作类别的前八条");
        List<JobCategory> jobCategories = jobCategoryDao.findAll(PageableUtil.returnDESCPageable(0, 8, "searchTimes")).getContent();
        List<JSONObject> hotJobCates = new ArrayList<>();
        if (jobCategories.size() > 0) {
            for (int i = 0; i < 8; i++) {
				JSONObject obj = new JSONObject();
				JobCategory cate = jobCategories.get(i);
				obj.put("cateStr", cate.getJobCateName());
				obj.put("cateId", cate.getId());
                hotJobCates.add(obj);
            }
        }
        log.info("热门搜索工作类别的长度：" + hotJobCates.size());
        JSONArray hotJobSearchCates = JSONArray.fromObject(hotJobCates);
        JSONObject object = new JSONObject();
        object.put("hotJobSearchCates", hotJobSearchCates);
        PackJsonUtil.packCorrectCommonJson(object, response);

    }

    /* 获取最新工作 */
    @RequestMapping("/getRecentlyJobs")
    @ResponseBody
    public void getRecentlyJobs(HttpServletResponse response) {
        log.info("获取最新工作");
        List<Job> recentlyJobs = new ArrayList<>();
        recentlyJobs = jobDao.findTop10ByPublishedOrderByPublicTimeDesc(true);
        log.info("获取最新工作的长度" + recentlyJobs.size());
        JSONObject object = PackJsonUtil.packHomePageJobList(recentlyJobs);
        PackJsonUtil.packCorrectCommonJson(object, response);
    }

    /* 获取热门工作 */
    @RequestMapping("/getHotJobs")
    @ResponseBody
    public void getHotJobs(HttpServletResponse response) {
        log.info("获取热门工作");
        List<Job> hotJobs = new ArrayList<>();
        hotJobs = jobDao.findTop10ByOrderByVisitTimes();
        log.info("获取热门工作的长度" + hotJobs.size());
        JSONObject object = PackJsonUtil.packHomePageJobList(hotJobs);
        PackJsonUtil.packCorrectCommonJson(object, response);
    }

    /* 获取推荐工作 */
    @RequestMapping("/getRecommendJobs")
    @ResponseBody
    public void getRecommendJobs(HttpServletResponse response) {
        log.info("获取推荐工作");
        List<Job> recommendJobs = new ArrayList<>();
        recommendJobs = jobDao.findByIsRecomment(true);
        log.info("获取推荐工作的长度" + recommendJobs.size());
        JSONObject object = PackJsonUtil.packHomePageJobList(recommendJobs);
        PackJsonUtil.packCorrectCommonJson(object, response);
    }

}
