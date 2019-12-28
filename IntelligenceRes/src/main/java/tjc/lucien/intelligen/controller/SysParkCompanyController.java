package tjc.lucien.intelligen.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import tjc.lucien.intelligen.dao.AdminerDao;
import tjc.lucien.intelligen.dao.ParkCompanyDao;
import tjc.lucien.intelligen.entity.Adminer;
import tjc.lucien.intelligen.entity.ParkCompany;
import tjc.lucien.intelligen.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/20.
 */
@Controller
@RequestMapping("/sys/parkcompanyManager")
public class SysParkCompanyController {

    public Logger log = Logger.getLogger(SysParkCompanyController.class);
    public ParamConfigUtil paramConfigUtil = new ParamConfigUtil();
    @Autowired
    private ParkCompanyDao parkCompanyDao;
    @Autowired
    private AdminerDao adminerDao;

    @RequestMapping("/center")
    public String parkComCenter(Model model, HttpServletRequest request) {

        int size = 6; //每页要显示的记录条数
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
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN) {
            //获取所有已发布的公司列表
            Page<ParkCompany> page = parkCompanyDao.findByIsPassAuth(true, PageableUtil.returnDESCPageable(number, size, "id"));
            System.out.println(page);
            model.addAttribute("parkComList", page);
        } else if (cur_adminer.getPower() == PowerMappingUtil.BUSINESSMANAGER) {
            Page<ParkCompany> page = parkCompanyDao.findById(cur_adminer.getParkCompany().getId(), PageableUtil.returnDESCPageable(0, 1, "id"));
            model.addAttribute("parkComList", page);
        } else {
            model.addAttribute("parkComList", null);
        }
        boolean canAddNewPark = false;   //是否拥有增加公司的权限
        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN) {
            canAddNewPark = true;
        }
        model.addAttribute("canAddNewPark", canAddNewPark);

        return "sysParkCompanyCenter";
    }

    @RequestMapping("/addNewParkCompanyPage")
    public String addNewParkCompanyPage(Model model) {

        model.addAttribute("curDate", FormatDate2StringUtil.formatDate2String(new Date()));
        return "sysPublishComPage";
    }

    /* ---------------新增园区企业----------------------- */

    @RequestMapping("/addNewParkCompany")
    @ResponseBody
    public String addNewParkCompanyAction(Model model, ParkCompany addParkCom, HttpServletRequest request, @RequestParam(value = "comSignal", required = false) MultipartFile comsignal) {
        HttpSession session = request.getSession();
        log.info(request.getParameter("comName") + "公司名称");
        log.info(request.getParameter("busiAccount") + "帐号");

        Adminer cur_adminer = (Adminer) session.getAttribute("cur_adminer");
        //String[] parkcomLabels = request.getParameterValues("parkcomLabel");
        //String parkcomLabelStr = Strings2StringUtil.Strings2String(parkcomLabels);
        String parkcomLabelStr = request.getParameter("parkcomLabelStr");
        log.info("公司标签为：" + parkcomLabelStr);

        if (cur_adminer.getPower() == PowerMappingUtil.ADMIN) {
            String busiAccount = request.getParameter("busiAccount");       //注册一个默认的管理员帐号
            String parkName = addParkCom.getComName();
            ParkCompany company = parkCompanyDao.findByComName(parkName);   //验证公司名称是否重复
            if (company == null) {
                List<Adminer> adminers = adminerDao.findByAccount(busiAccount);
                if (adminers == null || adminers.size() <= 0) {
                    log.info("终于到创建公司的步骤了");
                    log.info("创建公司管理员帐号" + busiAccount);
                    Adminer busiAdminer = new Adminer();
                    busiAdminer.setAccount(busiAccount);
                    busiAdminer.setPassword(busiAccount);
                    busiAdminer.setPower(PowerMappingUtil.BUSINESSMANAGER);
                    Adminer adminer1 = adminerDao.save(busiAdminer);
                    //保存公司图标操作
                    try {
                        if (comsignal != null) {
                            String comsignalPath = SaveFileReturnPathUtil.saveFile(comsignal, paramConfigUtil.getParamStr("syncpicpath"));
                            comsignalPath = paramConfigUtil.getParamStr("defaultPath") + comsignalPath;
                            addParkCom.setComSignal(comsignalPath);
                        } else {
                            addParkCom.setComSignal(paramConfigUtil.getParamStr("defaultcompic"));//保存一个默认头像
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                        addParkCom.setComSignal(paramConfigUtil.getParamStr("defaultcompic"));//保存一个默认头像
                    }

                    addParkCom.setEstablishDate(new Date());
                    addParkCom.setEstablishDateStr(FormatDate2StringUtil.formatDate2String(new Date()));
                    addParkCom.setIsPassAuth(true);
                    addParkCom.setWriteOff(false);
                    addParkCom.setComAddress(FinalMappingUtil.PARKADDRESS + addParkCom.getComAddress());
                    addParkCom.setCompanyLabels(parkcomLabelStr);
                    addParkCom.getAdminerSet().add(busiAdminer);
                    Set<Adminer> set = new HashSet<>();
                    set.add(adminer1);
                    addParkCom.setAdminerSet(set);
                    ParkCompany park = parkCompanyDao.save(addParkCom);
                    adminer1.setParkCompany(park);
                    adminerDao.saveAndFlush(adminer1);
                    return "SUCCESS";
                } else {
                    return "BUSSINESSADMINEREXIST";
                }

            } else {
                return "PARKNAMEEXIST";
            }
        } else {
            return "POWERLIMIT";
        }
    }

    /* 注销公司 */
    @RequestMapping("/deleteComAction")
    @ResponseBody
    public String deleteComAction(HttpServletRequest request) {

        int id = Integer.parseInt(request.getParameter(""));
        ParkCompany parkCompany = parkCompanyDao.findById(id);
        parkCompany.setIsPassAuth(false);
        parkCompanyDao.saveAndFlush(parkCompany);
        return "SUCCESS";
    }
}
