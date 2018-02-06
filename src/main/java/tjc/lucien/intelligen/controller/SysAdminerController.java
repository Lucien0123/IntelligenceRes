package tjc.lucien.intelligen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tjc.lucien.intelligen.dao.AdminerDao;
import tjc.lucien.intelligen.entity.Adminer;
import tjc.lucien.intelligen.utils.MulFileCopyToUtil;
import tjc.lucien.intelligen.utils.ParamConfigUtil;
import tjc.lucien.intelligen.utils.PowerMappingUtil;
import tjc.lucien.intelligen.utils.SaveFileReturnPathUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Administrator on 2017/3/20.
 */
@Controller
public class SysAdminerController {

    public Logger log = Logger.getLogger(SysAdminerController.class);
    public ParamConfigUtil paramConfigUtil = new ParamConfigUtil();
    @Autowired
    private AdminerDao adminerDao;

    /* 管理员登录跳转，登录后才可以进入管理页面 */
    @RequestMapping("/")
    public String adminerLoginPage(Model model){
        return "sysAdminerLogin";
    }

    /* 跳转个人中心页 */
    @RequestMapping("/sys/adminer/profilepage")
    public String profilePage(HttpServletRequest request, Model model){

        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN){
            model.addAttribute("parkComName", "产业园区管理员");
        } else {
            String comName = cur_adminer.getParkCompany().getComName();
            model.addAttribute("parkComName", comName);
        }
        model.addAttribute("adminer", cur_adminer);

        return "sysProfilePage";
    }

    /* 跳转修改密码界面 */
    @RequestMapping("/sys/adminer/modifypwdPage")
    public String modifypwdPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        String account = cur_adminer.getAccount();
        String realname = cur_adminer.getRealname();
        model.addAttribute("account", account);
        model.addAttribute("realname", realname);

        return "sysModifyPwdPage";
    }


    /* ---------------业务操作部分控制器------------------- */
    /* 管理员登录操作 */
    @RequestMapping("/sys/adminer/adminerlogin")
    public String adminLogin(Adminer loginAdminer, Model model, HttpServletRequest request){
        log.info(loginAdminer.getAccount() + "管理员执行登录");
        HttpSession session = request.getSession();
        Adminer cur_adminer = adminerDao.findByAccountAndPassword(loginAdminer.getAccount(), loginAdminer.getPassword());
        if (cur_adminer != null){
            Date curDate = new Date();
            cur_adminer.setLastLoginDate(curDate);
            session.setAttribute("cur_adminer", cur_adminer);
            adminerDao.saveAndFlush(cur_adminer);
            /* 重定向到管理首页 */
            return "redirect:/sys/recruite/center";
        } else {
            model.addAttribute("errmsg", "帐号、密码有误！");
            return "sysAdminerLogin";
        }
    }

    /* 修改个人信息 */
    @RequestMapping("/sys/adminer/modifyProfileAction")
    @ResponseBody
    public String modifyProfileAction(Model model, HttpServletRequest request, Adminer newAdminer, @RequestParam(value = "headPortrait", required = false) MultipartFile headPortrait){

        log.info("修改后的真实姓名：" + newAdminer.getRealname());
        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        boolean flag = true;

        if (flag){
            cur_adminer.setRealname(newAdminer.getRealname());
            if (cur_adminer.getGender() == null){
                cur_adminer.setGender(newAdminer.getGender());
            }
            cur_adminer.setRemark(newAdminer.getRemark());
            cur_adminer.setTelphone(newAdminer.getTelphone());
            //保存个人头像操作
            try {
                if (headPortrait != null) {
                    String headPath = SaveFileReturnPathUtil.saveFile(headPortrait, paramConfigUtil.getParamStr("syncpicpath2"));
                    headPath = paramConfigUtil.getParamStr("defaultPath") + headPath;
                    cur_adminer.setHeadPortrait(headPath);
                } else {
                    if(cur_adminer.getHeadPortrait()==null || "".equals(cur_adminer.getHeadPortrait())){
                        cur_adminer.setHeadPortrait(paramConfigUtil.getParamStr("defaultadminerpic"));//保存一个默认头像
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                cur_adminer.setHeadPortrait(paramConfigUtil.getParamStr("defaultadminerpic"));//保存一个默认头像
            }
            adminerDao.saveAndFlush(cur_adminer);
        }
        return "SUCCESS";
    }

    /* 异步显示头像操作 */
    @RequestMapping("/sys/adminer/syncshow")
    @ResponseBody
    public String syncUploadPic(@RequestParam(value = "headPortrait") MultipartFile headPic){
        String path = paramConfigUtil.getParamStr("syncpicpath3");
        String srcPicName = headPic.getOriginalFilename();
        String fileName = UUID.randomUUID() + srcPicName.substring(0, srcPicName.lastIndexOf("."));
        String fileType= srcPicName.substring(srcPicName.lastIndexOf("."));
        File file = new File(path);
        file.mkdirs();
        File tarFile;
        try {
            tarFile = File.createTempFile(fileName, fileType, file);
            log.info("显示头像的路径：" + fileName + fileType);
            MulFileCopyToUtil.copyTo(headPic, tarFile);
            String returnPath = paramConfigUtil.getParamStr("defaultPath") + "adminercache/" + tarFile.getAbsolutePath().substring(tarFile.getAbsolutePath().lastIndexOf("\\") + 1);
            log.info("返回路劲路径：" + returnPath);
            return returnPath;
        } catch (IOException e) {
            System.out.println("头像显示失败...");
            e.printStackTrace();
            return "ERROR";
        }
    }

    /* 修改密码操作 */
    @RequestMapping("/sys/adminer/modifyPwdAction")
    public String modifyPwdAction(HttpServletRequest request, Model model, Adminer newAdminer) {

        HttpSession session = request.getSession();
        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        String oldPwd = request.getParameter("oldPwd");
        if (cur_adminer.getPassword().equals(oldPwd)){
            cur_adminer.setPassword(newAdminer.getPassword());
            adminerDao.saveAndFlush(cur_adminer);

            model.addAttribute("errmsg", "修改密码成功，请重新登录！");
            return "sysAdminerLogin";
        } else {
            model.addAttribute("errmsg", "原始密码不正确！");
            return "forward:/sys/adminer/modifypwdPage";
        }
    }

    /* 退出操作 */
    @RequestMapping("/sys/adminer/loginoutAction")
    public String loginoutAction (HttpServletRequest request, Model model){

        log.info("管理员退出系统...");
        HttpSession session = request.getSession();
        session.invalidate();

        return "redirect:/";
    }
}
