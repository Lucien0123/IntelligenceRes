package tjc.lucien.intelligen.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.NewsDao;
import tjc.lucien.intelligen.entity.NewsAnnouncement;
import tjc.lucien.intelligen.utils.PackJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/4.
 */
@Controller
@RequestMapping("/h5/news")
public class NewsController {

    public Logger log = Logger.getLogger(NewsController.class);
    @Autowired
    private NewsDao newsDao;

    /* 获取新闻公告列表 */
    @RequestMapping("/getNewsList")
    @ResponseBody
    public void getNewsList(HttpServletRequest request, HttpServletResponse response) {

        log.info("获取h5新闻公告列表");
        List<JSONObject> newsArray = new ArrayList<>();
        List<NewsAnnouncement> newsList = newsDao.findByPublished(true);
        for (NewsAnnouncement news : newsList) {
            JSONObject newsObj = PackJsonUtil.packNews(news);
            newsArray.add(newsObj);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("newsList", JSONArray.fromObject(newsArray));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }


    /* 获取新闻公告的详情 */
    @RequestMapping("/getNewsDetails")
    @ResponseBody
    public void getNewsDetails(HttpServletRequest request, HttpServletResponse response){
        log.info("获取h5新闻公告详情");

        int newsId = Integer.parseInt(request.getParameter("newsId"));
        NewsAnnouncement news = newsDao.findById(newsId);
        JSONObject newsJson = PackJsonUtil.packNews(news);
        JSONObject packJson = new JSONObject();
        packJson.put("newsDetails", newsJson);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

}
