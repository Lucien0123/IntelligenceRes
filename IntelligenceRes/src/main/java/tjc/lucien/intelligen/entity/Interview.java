package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/26.
 */
@Entity
public class Interview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commonuserName;      /* 面试者姓名 */
    private int commonuserId;
    private String commonuserAccount;   /* 面试者帐号 */
    private String jobName;             /* 面试岗位名称 */
    private String occupation;          /* 面试者职业 */
    private int jobId;
    private String publisher;           /* 职位发布者，根据此字段获取列表 */
    private int publishCompanyId;       /* 职位发布公司id */
    private String companyName;         /* 职位发布公司名称 */
    @Temporal(TemporalType.DATE)
    private Date deliverDate;           /* 投递时间 */
    private String deliverDateStr;      /* 投递时间字符串 */

    private String telphone;            /* 联系电话 */
    private String resumeLink;          /* 简历预览地址 */
    @Temporal(TemporalType.DATE)
    private Date interviewDate;         /* 面试时间 */
    private String inerviewDateStr;     /* 面试时间字符串 */
    private String interviewPlace;      /* 面试地点 */
    private int interviewerId;
    private String interviewer;         /* 面试官 */
    private String status;              /* 面试状态，已投递，通知面试，不合适，通过面试 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommonuserName() {
        return commonuserName;
    }

    public void setCommonuserName(String commonuserName) {
        this.commonuserName = commonuserName;
    }

    public int getCommonuserId() {
        return commonuserId;
    }

    public void setCommonuserId(int commonuserId) {
        this.commonuserId = commonuserId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getResumeLink() {
        return resumeLink;
    }

    public void setResumeLink(String resumeLink) {
        this.resumeLink = resumeLink;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
    }

    public String getInterviewPlace() {
        return interviewPlace;
    }

    public void setInterviewPlace(String interviewPlace) {
        this.interviewPlace = interviewPlace;
    }

    public int getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(int interviewerId) {
        this.interviewerId = interviewerId;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliverDateStr() {
        return deliverDateStr;
    }

    public void setDeliverDateStr(String deliverDateStr) {
        this.deliverDateStr = deliverDateStr;
    }

    public String getInerviewDateStr() {
        return inerviewDateStr;
    }

    public void setInerviewDateStr(String inerviewDateStr) {
        this.inerviewDateStr = inerviewDateStr;
    }

    public int getPublishCompanyId() {
        return publishCompanyId;
    }

    public void setPublishCompanyId(int publishCompanyId) {
        this.publishCompanyId = publishCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCommonuserAccount() {
        return commonuserAccount;
    }

    public void setCommonuserAccount(String commonuserAccount) {
        this.commonuserAccount = commonuserAccount;
    }
}
