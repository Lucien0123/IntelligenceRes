package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3.
 */
@Entity
public class ParkCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String comName;             /* 公司名称 */
    private String comAddress;          /* 公司地址天津市-西青区-产业园区详细地址 */
    private String comSignal;           /* 公司图标地址 */
    private String comMainBusiness;     /* 主营业务 */
    private String comForm;             /* 上市公司或者不需要融资，融资阶段 */
    private String comStaffScale;       /* 人员规模，通过下来框选择X~X人 */
    private boolean isPassAuth;         /* 是否通过认证 */
    private String comDesc;             /* 公司描述 */
    private String companyLabels;       /* 公司标签，根据&br&标识符切分 */
    private String comHomepageLink;     /* 公司首页网页链接 */
    private boolean isWriteOff;         /* 是否注销该公司 */
    @OneToMany(targetEntity = Job.class)
    private Set<Job> jobs =  new HashSet<Job>();
    @OneToMany(targetEntity = Question.class)
    private Set<Question> questions = new HashSet<Question>();
    @OneToMany(targetEntity = Adminer.class)
    private Set<Adminer> adminerSet = new HashSet<>();    /* 公司员工 */
    @Temporal(TemporalType.DATE)
    private Date establishDate;        /* 成立日期 */
    private String establishDateStr;   /* 成立日期字符串 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComName() {
        return comName;
    }

    public void setComName(String comName) {
        this.comName = comName;
    }

    public String getComAddress() {
        return comAddress;
    }

    public void setComAddress(String comAddress) {
        this.comAddress = comAddress;
    }

    public String getComSignal() {
        return comSignal;
    }

    public void setComSignal(String comSignal) {
        this.comSignal = comSignal;
    }

    public String getComMainBusiness() {
        return comMainBusiness;
    }

    public void setComMainBusiness(String comMainBusiness) {
        this.comMainBusiness = comMainBusiness;
    }

    public String getComForm() {
        return comForm;
    }

    public void setComForm(String comForm) {
        this.comForm = comForm;
    }

    public String getComStaffScale() {
        return comStaffScale;
    }

    public void setComStaffScale(String comStaffScale) {
        this.comStaffScale = comStaffScale;
    }

    public boolean getIsPassAuth() {
        return isPassAuth;
    }

    public void setIsPassAuth(boolean isPassAuth) {
        this.isPassAuth = isPassAuth;
    }

    public String getComDesc() {
        return comDesc;
    }

    public void setComDesc(String comDesc) {
        this.comDesc = comDesc;
    }

    public String getCompanyLabels() {
        return companyLabels;
    }

    public void setCompanyLabels(String companyLabels) {
        this.companyLabels = companyLabels;
    }

    public String getComHomepageLink() {
        return comHomepageLink;
    }

    public void setComHomepageLink(String comHomepageLink) {
        this.comHomepageLink = comHomepageLink;
    }

    public boolean isWriteOff() {
        return isWriteOff;
    }

    public void setWriteOff(boolean writeOff) {
        isWriteOff = writeOff;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Adminer> getAdminerSet() {
        return adminerSet;
    }

    public void setAdminerSet(Set<Adminer> adminerSet) {
        this.adminerSet = adminerSet;
    }

    public Date getEstablishDate() {
        return establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    public String getEstablishDateStr() {
        return establishDateStr;
    }

    public void setEstablishDateStr(String establishDateStr) {
        this.establishDateStr = establishDateStr;
    }
}
