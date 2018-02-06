package tjc.lucien.intelligen.controller;

import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tjc.lucien.intelligen.dao.CommonUserDao;
import tjc.lucien.intelligen.entity.CommonUser;
import tjc.lucien.intelligen.utils.MulFileCopyToUtil;
import tjc.lucien.intelligen.utils.PackJsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2017/3/3.
 */
@Controller
@RequestMapping("/h5/CommonUser")
public class CommonUserController {

    @Autowired
    private CommonUserDao commonUserDao;
    public Logger log = Logger.getLogger(CommonUserController.class);

    @RequestMapping("/commonuserlogin")
    public void commonuserlogin(HttpServletRequest request, Model model,
            HttpServletResponse response){
        HttpSession session = request.getSession();
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        CommonUser cur_user = commonUserDao.findByAccountAndPassword(account, password);
        if (cur_user != null){
            session.setAttribute("cur_user", cur_user);
            JSONObject respJson = new JSONObject();
            respJson.put("nickname", cur_user.getNikename());
            respJson.put("account", cur_user.getAccount());
            respJson.put("realname", cur_user.getRealname());
            respJson.put("headportrait", cur_user.getHeadPortrait());
            PackJsonUtil.packCorrectCommonJson(respJson, response);
        } else {
            model.addAttribute("msg", "登录异常，帐号密码有误！");
            String errMsg = "登录异常，帐号密码有误！";
            PackJsonUtil.packErrorCommonJson(errMsg, response);
        }
    }

    @RequestMapping("/commonuserregister")
    public void commonuserregister(CommonUser user, Model model,
        HttpServletRequest request, HttpServletResponse response) {
        CommonUser isExist = commonUserDao.findByAccount(user.getAccount());
        if (isExist != null){
            String errMsg = "帐号已存在，请重新输入";
            PackJsonUtil.packErrorCommonJson(errMsg, response);
        } else {
            CommonUser reg_user = commonUserDao.save(user);
            JSONObject object = new JSONObject();
            object.put("account", reg_user.getAccount());
            PackJsonUtil.packCorrectCommonJson(object, response);
        }
    }

    /* 获取用户详细信息 */
    @RequestMapping("/getPersonalInfor")
    @ResponseBody
    public void getPersonalInforAction(HttpServletRequest request, HttpServletResponse response){

        String account = request.getParameter("account");
        CommonUser user = commonUserDao.findByAccount(account);
        JSONObject userJson = new JSONObject();
        userJson.put("realname", user.getRealname());
        userJson.put("telphone", user.getTelphone());
        userJson.put("gender", user.getGender());
        userJson.put("occupation", user.getOccupation());
        userJson.put("personalDesc", user.getPersonalDesc());
        userJson.put("headPortrait", user.getHeadPortrait());
        userJson.put("accessName", user.getAccessName());
        userJson.put("accessResume", user.getAccessResume());
        userJson.put("recentlyViewJobIdsStr", user.getRecentlyViewJobIdsStr());
        JSONObject packJson = new JSONObject();
        packJson.put("personalInfor", userJson);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }


    @RequestMapping("/modifyPersonalInfor")
    @ResponseBody
    public void modifyPersonalInforAction(HttpServletRequest request, HttpServletResponse response, CommonUser modifyUser){

        String account = request.getParameter("account");
        CommonUser user = commonUserDao.findByAccount(account);
        user.setRealname(modifyUser.getRealname());
        user.setGender(modifyUser.getGender());
        user.setOccupation(modifyUser.getOccupation());
        user.setPersonalDesc(modifyUser.getPersonalDesc());
        user.setTelphone(modifyUser.getTelphone());
        CommonUser commonUser =  commonUserDao.saveAndFlush(user);
        log.info("修改用户信息成功");
        JSONObject userJson = new JSONObject();
        userJson.put("realname", commonUser.getRealname());
        userJson.put("telphone", commonUser.getTelphone());
        userJson.put("gender", commonUser.getGender());
        userJson.put("occupation", commonUser.getOccupation());
        userJson.put("personalDesc", commonUser.getPersonalDesc());
        userJson.put("headPortrait", commonUser.getHeadPortrait());
        userJson.put("recentlyViewJobIdsStr", commonUser.getRecentlyViewJobIdsStr());
        JSONObject packJson = new JSONObject();
        packJson.put("personalInfor", userJson);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    /* 修改密码 */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public void modifyPassword(HttpServletRequest request, HttpServletResponse response){
        String account = request.getParameter("account");
        String oldpsw = request.getParameter("oldpsw");
        String password = request.getParameter("password");
        CommonUser user = commonUserDao.findByAccount(account);
        if (user.getPassword().equals(oldpsw)){
            user.setPassword(password);
            commonUserDao.saveAndFlush(user);
            PackJsonUtil.packCorrectCommonJson(new JSONObject(), response);
        } else {
            log.info("修改密码失败！");
            PackJsonUtil.packErrorCommonJson("修改密码失败！原始密码错误！", response);
        }
    }

    /* 返回文件的二进制流 */
    @RequestMapping("/getFileStream")
    @ResponseBody
    public void getFileStream(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "uploadfile", required = false) MultipartFile uploadfile){
        InputStream filesb = null;
        try {
            filesb = MulFileCopyToUtil.returnStream(uploadfile);
        } catch (IOException e) {
            log.info("文件流转化失败！");
            e.printStackTrace();
        }
        JSONObject packJson = new JSONObject();
        packJson.put("fileStream", filesb);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

    /* 保存附件简历的下载路径 */
    @RequestMapping("/saveAttachment")
    @ResponseBody
    public void modifyAccess(HttpServletRequest request, HttpServletResponse response){

        String account = request.getParameter("account");
        String filename = request.getParameter("fileName");
        String filePath = request.getParameter("filePath");
        CommonUser user = commonUserDao.findByAccount(account);
        user.setAccessName(filename);
        user.setAccessResume(filePath);
        commonUserDao.saveAndFlush(user);
        JSONObject packJson = new JSONObject();
        packJson.put("downLoadPath", filePath);
        PackJsonUtil.packCorrectCommonJson(packJson, response);
    }

}
