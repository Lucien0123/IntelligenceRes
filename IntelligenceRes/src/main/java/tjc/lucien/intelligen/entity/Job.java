package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3.
 * 职位实体,使用多对一关联时，在创建数据时不好操作
 */
@Entity
public class Job implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String jobName;
    private String jobSalary;      /* 薪资 */
    private String workPosition;   /* 工作地点天津市-西青区-详细地址 */
    private String workExpression; /* 工作经验要求 */
    private String education;      /* 学历要求 */
    private String workSummary;    /* 工作简介 */
    @ManyToOne(targetEntity = ParkCompany.class)
    @JoinColumn(name = "comId", referencedColumnName = "id")
    private ParkCompany parkCompany;
    private int companyId;         /* 发布该职位的公司id */
    private String companyName;
    private String comMainBusiness;/* 所属公司的主营业务 */
    private String comForm;        /* 所属公司的融资形式 */
    private String worktype;       /* 工作类别（全职兼职实习） */
    private String workLabels;     /* 工作标签通过&br&标识符切割成多个条目 */
    @Temporal(TemporalType.DATE)
    private Date publicTime;       /* 工作发布时间 */
    private String publicer;       /* 发布人 */
    private String jobAdvantage;   /* 职位诱惑 */
    private String jobStatementStr;/* 岗位职责通过&br&标识符切割成多个条目 */
    private String jobDemandStr;         /* 任职要求通过&br&标识符切割成多个条目 */
    private String jobCateName;    /* 工作类别 */
    private int visitTimes;        /* 访问次数，热门职位 */
    private boolean isRecomment;   /* 是否推荐该工作 */
    @OneToMany(targetEntity = JobAppraise.class, cascade = CascadeType.ALL)
    private Set<JobAppraise> jobAppraiseSet = new HashSet<>();
    private int taskId;           /* 任务id */
    private String auditorName;   /* 审核人姓名 */
    private int auditorId;        /* 审核人id */
    private boolean published;    /* 是否发布 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(String jobSalary) {
        this.jobSalary = jobSalary;
    }

    public String getWorkPosition() {
        return workPosition;
    }

    public void setWorkPosition(String workPosition) {
        this.workPosition = workPosition;
    }

    public String getWorkExpression() {
        return workExpression;
    }

    public void setWorkExpression(String workExpression) {
        this.workExpression = workExpression;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getWorkSummary() {
        return workSummary;
    }

    public void setWorkSummary(String workSummary) {
        this.workSummary = workSummary;
    }

    public ParkCompany getParkCompany() {
        return parkCompany;
    }

    public void setParkCompany(ParkCompany parkCompany) {
        this.parkCompany = parkCompany;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getWorkLabels() {
        return workLabels;
    }

    public void setWorkLabels(String workLabels) {
        this.workLabels = workLabels;
    }

    public Date getPublicTime() {
        return publicTime;
    }

    public void setPublicTime(Date publicTime) {
        this.publicTime = publicTime;
    }

    public String getPublicer() {
        return publicer;
    }

    public void setPublicer(String publicer) {
        this.publicer = publicer;
    }

    public String getJobAdvantage() {
        return jobAdvantage;
    }

    public void setJobAdvantage(String jobAdvantage) {
        this.jobAdvantage = jobAdvantage;
    }

    public String getJobStatementStr() {
        return jobStatementStr;
    }

    public void setJobStatementStr(String jobStatementStr) {
        this.jobStatementStr = jobStatementStr;
    }

    public String getJobDemandStr() {
        return jobDemandStr;
    }

    public void setJobDemandStr(String jobDemandStr) {
        this.jobDemandStr = jobDemandStr;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public boolean isRecomment() {
        return isRecomment;
    }

    public void setRecomment(boolean recomment) {
        isRecomment = recomment;
    }

    public Set<JobAppraise> getJobAppraiseSet() {
        return jobAppraiseSet;
    }

    public void setJobAppraiseSet(Set<JobAppraise> jobAppraiseSet) {
        this.jobAppraiseSet = jobAppraiseSet;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public int getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(int auditorId) {
        this.auditorId = auditorId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }

    public String getJobCateName() {
        return jobCateName;
    }

    public void setJobCateName(String jobCateName) {
        this.jobCateName = jobCateName;
    }
}
