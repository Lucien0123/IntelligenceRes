package tjc.lucien.intelligen.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tjc.lucien.intelligen.dao.MessageEntityDao;
import tjc.lucien.intelligen.entity.MessageEntity;
import tjc.lucien.intelligen.utils.PackJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/14.
 */
@Controller
@RequestMapping("/h5/message")
public class MessageEntieyController {

    public Logger log = Logger.getLogger(MessageEntieyController.class);
    @Autowired
    MessageEntityDao messageEntityDao;

    @RequestMapping("/getNoReadMessageList")
    @ResponseBody
    public void getNoReadMessageList(HttpServletRequest request, HttpServletResponse response){

        String account = request.getParameter("account");
        List<MessageEntity> entities = messageEntityDao.findByAccountAndIsRead(account, false);
        List<JSONObject> entityJsons = new ArrayList<>();
        for (MessageEntity entity : entities) {
            JSONObject entityJson = PackJsonUtil.packMessage(entity);
            entityJsons.add(entityJson);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("messageList", JSONArray.fromObject(entityJsons));
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    @RequestMapping("/readMessageAction")
    @ResponseBody
    public void readMessageAction(HttpServletRequest request, HttpServletResponse response){

        int id = Integer.parseInt(request.getParameter("id"));
        String account = request.getParameter("account");
        MessageEntity entity = messageEntityDao.findById(id);
        entity.setRead(true);
        messageEntityDao.saveAndFlush(entity);
        //刷新后返回新的信息列表
        List<MessageEntity> entities = messageEntityDao.findByAccountAndIsRead(account, false);
        List<JSONObject> entityJsons = new ArrayList<>();
        for (MessageEntity message : entities) {
            JSONObject entityJson = PackJsonUtil.packMessage(message);
            entityJsons.add(entityJson);
        }
        JSONObject packJson = new JSONObject();
        packJson.put("messageList", JSONArray.fromObject(entityJsons));
        PackJsonUtil.packCorrectCommonJson(packJson, response);

    }

}
