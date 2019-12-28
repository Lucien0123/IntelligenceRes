package tjc.lucien.intelligen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import tjc.lucien.intelligen.dao.InterviewDao;
import tjc.lucien.intelligen.dao.MessageEntityDao;
import tjc.lucien.intelligen.entity.Adminer;
import tjc.lucien.intelligen.entity.Interview;
import tjc.lucien.intelligen.utils.FinalMappingUtil;
import tjc.lucien.intelligen.utils.PageableUtil;
import tjc.lucien.intelligen.utils.PushMessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/26.
 */
@Controller
@RequestMapping("/sys/interview")
public class SysInterviewController {

    public Logger log = Logger.getLogger(SysInterviewController.class);
    @Autowired
    private InterviewDao interviewDao;
    @Autowired
    private MessageEntityDao messageEntityDao;

    @RequestMapping("/center")
    public String interviewCenter(HttpServletRequest request, Model model) {

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
        String adminerName = cur_adminer.getRealname();
        Page<Interview> deliverPage = interviewDao.findByPublisherAndStatus(adminerName, FinalMappingUtil.DELIVERRED, PageableUtil.returnDESCPageable(number, size, "id"));
        Page<Interview> interviewPage = interviewDao.findByPublisherAndStatus(adminerName, FinalMappingUtil.NOTICEINTERVIEW, PageableUtil.returnDESCPageable(number, size, "id"));
        model.addAttribute("interviewPage", interviewPage);
        model.addAttribute("deliverPage", deliverPage);
        return "sysInterviewCenter";
    }


    /* ----------Action------------ */

    /* 邀请面试 */
    @RequestMapping("/invite")
    public String inviteAction(HttpServletRequest request, Model model) {
        int interId = Integer.parseInt(request.getParameter("id"));
        Interview interview = interviewDao.findById(interId);
        int jobId = interview.getJobId();
        String jobName = interview.getJobName();
        String companyName = interview.getCompanyName();
        String account = interview.getCommonuserAccount();
        //设置状态为通知面试
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        String adminerName = cur_adminer.getRealname();
        int adminerId = cur_adminer.getId();
        String interviewDate = request.getParameter("interviewDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        interview.setInterviewer(adminerName);
        interview.setInterviewerId(adminerId);
        //设置投递状态修改为通知面试状态
        interview.setStatus(FinalMappingUtil.NOTICEINTERVIEW);
        interview.setInterviewPlace("天津产业园区" + cur_adminer.getParkCompany().getComName());
        /*try {
            interview.setInterviewDate(sdf.parse(interviewDate));
        } catch (ParseException e) {
            return "DATEFORMATERROR";
        }*/
        interview.setInterviewDate(new Date());
        interviewDao.saveAndFlush(interview);
        //推送消息 1.封装消息内容  2.保存消息   3.发送消息
        String msgStr = PushMessageUtil.packInterviewMessage("interview", companyName, jobName, jobId + "");
        String messageId = PushMessageUtil.saveMessageEntity(messageEntityDao, msgStr, account);
        PushMessageUtil.publishMessage(account, msgStr, messageId);  /* 发送消息 */

        return "redirect:/sys/interview/center";
    }

    /* 不合适 */
    @RequestMapping("/nopass")
    public String nopaddAction(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Interview interview = interviewDao.findById(id);
        //设置状态为不通过
        interview.setStatus(FinalMappingUtil.NOSUIT);
        interviewDao.saveAndFlush(interview);
        return "redirect:/sys/interview/center";
    }

    /* 通过面试 */
    @RequestMapping("/passInterview")
    public String passInterviewAction(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter("id"));
        Interview interview = interviewDao.findById(id);
        interview.setStatus(FinalMappingUtil.PASSINTERVIEW);
        interviewDao.saveAndFlush(interview);
        return "redirect:/sys/interview/center";
    }
}
