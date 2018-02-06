package tjc.lucien.intelligen.utils;

import io.goeasy.GoEasy;
import tjc.lucien.intelligen.dao.MessageEntityDao;
import tjc.lucien.intelligen.entity.MessageEntity;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/6.
 */
public class PushMessageUtil {

    public static ParamConfigUtil paramConfigUtil = new ParamConfigUtil();

    public static GoEasy goEasy = new GoEasy(paramConfigUtil.getParamStr("goeasycode"));

    /**
     * 推送消息
     * @param account 接受消息的用户帐户
     * @param message 推送的消息
     */
    public static void publishMessage(String account, String message, String messageId){
        /* 面试通知(工作id)，回复通知(问题id) */

        String messagestr = message;
        String messageIdstr = "&:&:&$messageid$" + messageId;
        String msg = messagestr + messageIdstr;
        System.out.println("发送的消息：" + msg);
        goEasy.publish(account, msg);
    }

    /**
     * 封装消息
     * @param message  消息内容
     * @param type     消息类型   reply
     * @param id       问题Id
     * @return 消息
     */
    public static String packMessage(String message, String type, String id){
        String typestr = "$type$" + "reply";
        String messagestr = "&:&:&$message$" + message;
        String idstr = "&:&:&$id$" + id;
        String msg = typestr + messagestr + idstr;
        return msg;
    }

    public static String packInterviewMessage(String type, String companyName, String jobName, String jobId){
        String typestr = "$type$" + "interview";
        String messagestr = "&:&:&$message$" + "您申请的" + companyName + "的" + jobName + "邀请您参加面试，面试通知将在稍后发送至您手机";
        String idstr = "&:&:&$id$" + jobId;
        String msg = typestr + messagestr + idstr;
        return msg;
    }

    /**
     * 保存消息到数据库
     * @param messageEntityDao
     * @param content   消息内容
     * @param account   接收者帐号
     * @return 返回消息id
     */
    public static String saveMessageEntity(MessageEntityDao messageEntityDao, String content, String account){

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setAccount(account);
        messageEntity.setContent(content);
        messageEntity.setCreateDate(new Date());
        messageEntity.setRead(false);
        MessageEntity entity = messageEntityDao.save(messageEntity);
        return entity.getId() + "";
    }
}
