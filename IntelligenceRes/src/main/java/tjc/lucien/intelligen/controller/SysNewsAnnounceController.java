package tjc.lucien.intelligen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.AdminerDao;
import tjc.lucien.intelligen.dao.NewsDao;
import tjc.lucien.intelligen.dao.TaskDao;
import tjc.lucien.intelligen.entity.Adminer;
import tjc.lucien.intelligen.entity.NewsAnnouncement;
import tjc.lucien.intelligen.entity.Task;
import tjc.lucien.intelligen.utils.FinalMappingUtil;
import tjc.lucien.intelligen.utils.FormatDate2StringUtil;
import tjc.lucien.intelligen.utils.PageableUtil;
import tjc.lucien.intelligen.utils.PowerMappingUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/3/22.
 */
@Controller
@RequestMapping("/sys/news")
public class SysNewsAnnounceController {

    public Logger log = Logger.getLogger(SysNewsAnnounceController.class);

    @Autowired
    private NewsDao newsDao;
    @Autowired
    private AdminerDao adminerDao;
    @Autowired
    private TaskDao taskDao;


    /* 根据登录权限获取新闻列表，管理员获得所有新闻列表，按照时间排序，
     * 公司管理员获得本公司的新闻列表 */
    @RequestMapping("/center")
    public String newsAnnounceCenter(HttpServletRequest request, Model model){

        log.info("进入新闻资讯列表页面");
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
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            Page<NewsAnnouncement> newspage = newsDao.findByPublished(true, PageableUtil.returnDESCPageable(number, size, "publicDate"));
            model.addAttribute("newspage", newspage);
        } else if(cur_adminer.getPower() == PowerMappingUtil.BUSINESSMANAGER){
            Page<NewsAnnouncement> newspage = newsDao.findByPublishedAndCompanyName(true, cur_adminer.getParkCompany().getComName(), PageableUtil.returnDESCPageable(number, size, "publicDate"));
            model.addAttribute("newspage", newspage);
        } else {
            model.addAttribute("newspage", null);
        }
        return "sysNewsCenter";
    }

    @RequestMapping("/publishNewsPage")
    public String publishNewsPage(Model model, HttpServletRequest request){
        log.info("跳转发布新闻页");
        String isEdit = request.getParameter("isEdit");
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            model.addAttribute("isSelectAuditor", false);
        } else {
            List<Adminer> adminers = adminerDao.findByPower(PowerMappingUtil.ADMIN);
            model.addAttribute("adminers", adminers);
            if ("edit".equals(isEdit)) {
                model.addAttribute("isSelectAuditor", false);
            } else {
                model.addAttribute("isSelectAuditor", true);
            }
        }
        model.addAttribute("publisher", cur_adminer.getRealname());
        model.addAttribute("publishDate", FormatDate2StringUtil.formatDate2String(new Date()));
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            model.addAttribute("companyName", "产业园区");
        } else {
            model.addAttribute("companyName", cur_adminer.getParkCompany().getComName());
        }

        return "sysPublishNewsPage";
    }

    @RequestMapping("/toEditNewsPage")
    public String toEditJobPage(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        int newsId = (int) session.getAttribute("editNewsId");
        log.info("修改工作的id为：" + newsId);
        NewsAnnouncement news = newsDao.findById(newsId);
        int taskId = news.getTaskId();
        Task backTask = taskDao.findById(taskId);
        String backOpinion = backTask.getBackOpinion();
        model.addAttribute("editNews", news);
        model.addAttribute("backOpinion", backOpinion);
        model.addAttribute("publishDateStr", FormatDate2StringUtil.formatDate2String(news.getPublicDate()));

        return "sysEditNewsPage";

    }

    /* ---------------------新闻公告页面action---------------------- */

    /* 发布新闻公告操作 */
    @RequestMapping("/publicNewsAction")
    @ResponseBody
    public String publicNewsAction(Model model, HttpServletRequest request, NewsAnnouncement publishNews){

        log.info("发布新闻的标题为：" + publishNews.getNewsTitle());
        log.info("发布新闻的内容为：" + publishNews.getNewsContent());
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        List<NewsAnnouncement> news = newsDao.findByPublisherAndNewsTitle(cur_adminer.getRealname(), publishNews.getNewsTitle());
        if (news.size() > 0){
            log.info("新闻标题存在,发布失败");
            return "NEWSTITLEEXIST";
        } else {
            publishNews.setViewCount(0);
            if (cur_adminer.getRealname() != null || !"".equals(cur_adminer.getRealname())){
                publishNews.setPublisher(cur_adminer.getRealname());
            } else {
                publishNews.setPublisher("管理员-" + cur_adminer.getId());
            }
            publishNews.setPublicDate(new Date());
            Task taskNews = new Task();
            taskNews.setPublishDate(publishNews.getPublicDate());
            taskNews.setPublishDateStr(FormatDate2StringUtil.formatDate2String(publishNews.getPublicDate()));
            taskNews.setTaskType(FinalMappingUtil.PUBLISHNEWS);
            taskNews.setTaskTitle(publishNews.getNewsTitle());
            taskNews.setSubmiter(cur_adminer);
            taskNews.setSubmiterName(cur_adminer.getRealname());

            if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
                log.info("当期是管理员发布新闻，直接发布，发布人审核人都是自己");
                taskNews.setStatus("已发布");
                publishNews.setAuditorId(cur_adminer.getId()+"");
                publishNews.setAuditorName(cur_adminer.getRealname());
                taskNews.setAuditor(cur_adminer);
                taskNews.setAuditorName(cur_adminer.getRealname());
                publishNews.setPublished(true);

            } else {
                log.info("当前是企业管理员，提交园区管理员审核，发布人是自己，审核人是选择的");
                if (publishNews.getAuditorId() == null || "".equals(publishNews.getAuditorId())){
                    return "PLEASESELECTAUDITOR";
                }
                Adminer auditor = adminerDao.findById(Integer.parseInt(publishNews.getAuditorId()));
                String auditorName = auditor.getRealname();
                publishNews.setCompanyName(cur_adminer.getParkCompany().getComName());
                publishNews.setAuditorName(auditorName);
                taskNews.setStatus("已提交");
                taskNews.setAuditor(auditor);
                taskNews.setAuditorName(auditor.getRealname());
                publishNews.setPublished(false);
            }

            Task task = taskDao.save(taskNews);
            publishNews.setTaskId(task.getId());
            newsDao.save(publishNews);
            log.info("新闻发布成功!");
            return "SUCCESS";
        }
    }

    /* 被退回后进行修改 */
    @RequestMapping("/republishNewsAction")
    @ResponseBody
    public String republishNewsAction(HttpServletRequest request, Model model, NewsAnnouncement publishNews){

        HttpSession session = request.getSession();
        int oldNewsId = Integer.parseInt(request.getParameter("id"));
        NewsAnnouncement oldnews = newsDao.findById(oldNewsId);
        Adminer cur_adminer = (Adminer) session.getAttribute(FinalMappingUtil.CUR_ADMINER);
        List<NewsAnnouncement> news = newsDao.findByPublisherAndNewsTitle(cur_adminer.getRealname(), publishNews.getNewsTitle());
        if (news != null && !oldnews.getNewsTitle().equals(publishNews.getNewsTitle())){
            log.info("新闻标题存在,发布失败");
            return "NEWSTITLEEXIST";
        } else {
            oldnews.setPublicDate(new Date());
            oldnews.setNewsTitle(publishNews.getNewsTitle());
            oldnews.setNewsContent(publishNews.getNewsContent());
            Task taskNews = taskDao.findById(oldnews.getTaskId());
            taskNews.setPublishDate(oldnews.getPublicDate());
            taskNews.setPublishDateStr(FormatDate2StringUtil.formatDate2String(oldnews.getPublicDate()));
            taskNews.setTaskType(FinalMappingUtil.PUBLISHNEWS);
            taskNews.setTaskTitle(oldnews.getNewsTitle());
            taskNews.setStatus("已修改");
            oldnews.setPublished(false);

            taskDao.saveAndFlush(taskNews);
            publishNews.setTaskId(oldnews.getTaskId());
            newsDao.saveAndFlush(oldnews);
            log.info("新闻重新发布成功!");
            return "SUCCESS";
        }
    }

    /* 删除新闻公告列表中的某一项 */
    @RequestMapping("/deleteNewsAction")
    public String deleteNewsAction(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("newsid"));
        NewsAnnouncement news = newsDao.findById(id);
        Task task = taskDao.findById(news.getTaskId());
        news.setPublished(false);
        task.setStatus("已撤销");
        newsDao.saveAndFlush(news);
        taskDao.saveAndFlush(task);
        return "forward:/center";
    }

}
