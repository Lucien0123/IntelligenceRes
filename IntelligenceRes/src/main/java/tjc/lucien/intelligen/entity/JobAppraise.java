package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/16.
 * 面试评价
 */
@Entity
public class JobAppraise implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int descScore;          /* 描述得分 */
    private int interviewerScore;   /* 面试官评分 */
    private int enviroScore;        /* 公司环境得分 */
    private String interviewProcess;/* 面试过程 */
    private String otherAppraise;   /* 其他评价 */
    @Temporal(TemporalType.DATE)
    private Date publishDate;     /* 评价时间 */
    @Column(nullable=false,columnDefinition="INT default 0")
    private int approveCount;       /* 赞同数量 */
    @ManyToOne(targetEntity = CommonUser.class)
    @JoinColumn(name = "commonUserId", referencedColumnName = "id")
    private CommonUser commonUser;
    private String labelStr;        /* 评价标签，根据&br&切割 */
    @ManyToOne(targetEntity = Job.class)
    @JoinColumn(name = "jobId", referencedColumnName = "id")
    private Job job;                /* 面试职位 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDescScore() {
        return descScore;
    }

    public void setDescScore(int descScore) {
        this.descScore = descScore;
    }

    public int getInterviewerScore() {
        return interviewerScore;
    }

    public void setInterviewerScore(int interviewerScore) {
        this.interviewerScore = interviewerScore;
    }

    public int getEnviroScore() {
        return enviroScore;
    }

    public void setEnviroScore(int enviroScore) {
        this.enviroScore = enviroScore;
    }

    public String getInterviewProcess() {
        return interviewProcess;
    }

    public void setInterviewProcess(String interviewProcess) {
        this.interviewProcess = interviewProcess;
    }

    public String getOtherAppraise() {
        return otherAppraise;
    }

    public void setOtherAppraise(String otherAppraise) {
        this.otherAppraise = otherAppraise;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getApproveCount() {
        return approveCount;
    }

    public void setApproveCount(int approveCount) {
        this.approveCount = approveCount;
    }

    public CommonUser getCommonUser() {
        return commonUser;
    }

    public void setCommonUser(CommonUser commonUser) {
        this.commonUser = commonUser;
    }

    public String getLabelStr() {
        return labelStr;
    }

    public void setLabelStr(String labelStr) {
        this.labelStr = labelStr;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
