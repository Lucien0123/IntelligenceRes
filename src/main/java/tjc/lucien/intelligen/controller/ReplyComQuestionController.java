package tjc.lucien.intelligen.controller;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.*;
import tjc.lucien.intelligen.entity.*;
import tjc.lucien.intelligen.utils.PackJsonUtil;
import tjc.lucien.intelligen.utils.PushMessageUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/25.
 */
@Controller
@RequestMapping("/h5/reply")
public class ReplyComQuestionController {

    public Logger log = Logger.getLogger(ReplyComQuestionController.class);
    @Autowired
    private FirstCommentDao firstCommentDao;
    @Autowired
    private SecondCommentDao secondCommentDao;
    @Autowired
    private CommonUserDao commonUserDao;
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private MessageEntityDao messageEntityDao;

    /* 回复操作 */
    @RequestMapping("/replyAction")
    @ResponseBody
    public void replyAction(HttpServletRequest request, HttpServletResponse response) {

        String replyContent = request.getParameter("replyContent");//回复内容
        String replyType = request.getParameter("replyType");     //回复类型
        String account = request.getParameter("replierAccount");  //回复人帐号
        CommonUser replier = commonUserDao.findByAccount(account);
        if ("FIRSTREPLY".equals(replyType)) {

            FirstComment firstComment = new FirstComment();
            int questionId = Integer.parseInt(request.getParameter("questionId"));
            Question question = questionDao.findById(questionId);
            firstComment.setQuestionId(questionId);
            firstComment.setCommenter(replier);
            firstComment.setCommentContent(replyContent);
            firstComment.setCommentDate(new Date());
            firstComment.setCommentStatus(false);
            Set<SecondComment> secondCommentSet = new HashSet<>();
            firstComment.setSecondComments(secondCommentSet);
            firstCommentDao.save(firstComment);
            JSONObject packJson = new JSONObject();

            //推送消息 1.封装消息内容  2.保存消息   3.发送消息
            String replierRealname = replier.getRealname();
            if (replierRealname == null || "".equals(replierRealname)){
                replierRealname = "匿名用户" + replier.getAccount();
            }
            String msgStr = PushMessageUtil.packMessage(replierRealname + "回复了你：" + replyContent, "reply", questionId + "");
            String messageId = PushMessageUtil.saveMessageEntity(messageEntityDao, msgStr, question.getQuizzer().getAccount());
            PushMessageUtil.publishMessage(question.getQuizzer().getAccount(), msgStr, messageId);  /* 发送消息 */

            PackJsonUtil.packCorrectCommonJson(packJson, response);
        } else if ("SECONDREPLY".equals(replyType)) {
            int firCommentId = Integer.parseInt(request.getParameter("firCommentId"));
            //通知
            String beReplierAccount = request.getParameter("beReplier");  //被评论者帐号

            CommonUser beReplier = commonUserDao.findByAccount(beReplierAccount);
            FirstComment firCommment = firstCommentDao.findById(firCommentId);
            int questionId = firCommment.getQuestionId();
            SecondComment secondComment = new SecondComment();

            secondComment.setCommentStatus(false);
            secondComment.setReplyContent(replyContent);
            secondComment.setCommonentDate(new Date());
            String replierName = replier.getRealname();
            String beReplierName = beReplier.getRealname();
            if (replierName == null || "".equals(replierName)) {
                replierName = "匿名用户" + replier.getAccount();
            }
            if (beReplierName == null || "".equals(beReplierName)) {
                beReplierName = "匿名用户" + beReplier.getAccount();
                ;
            }
            secondComment.setReplier(replierName);
            secondComment.setBeReplier(beReplierName);
            secondComment.setReplyAccount(replier.getAccount());   //提供给下次评论
            SecondComment s = secondCommentDao.save(secondComment);
            Set<SecondComment> conmentSet = firCommment.getSecondComments();
            conmentSet.add(s);
            firCommment.setSecondComments(conmentSet);
            firstCommentDao.saveAndFlush(firCommment);

            //推送消息
            //推送消息 1.封装消息内容  2.保存消息   3.发送消息
            String msgStr = PushMessageUtil.packMessage(replierName + "回复了你：" + replyContent, "reply", questionId + "");
            String messageId = PushMessageUtil.saveMessageEntity(messageEntityDao, msgStr, beReplierAccount);
            PushMessageUtil.publishMessage(beReplierAccount, msgStr, messageId);  /* 发送消息 */

            PackJsonUtil.packCorrectCommonJson(new JSONObject(), response);

        } else {
            PackJsonUtil.packErrorCommonJson("评论失败！", response);
        }
    }


    /* 根据问题ID获取问题 */
    @RequestMapping("/getQuestionDetail")
    @ResponseBody
    public void getQuestionDetail(HttpServletRequest request, HttpServletResponse response) {

        int questionId = Integer.parseInt(request.getParameter("questionId"));
        Question question = questionDao.findById(questionId);
        List<FirstComment> firstComments = firstCommentDao.findByQuestionId(questionId);
        JSONObject questionJson = PackJsonUtil.packQuestion(question, firstComments);
        JSONObject packJson = new JSONObject();
        packJson.put("questiondetail", questionJson);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

}
