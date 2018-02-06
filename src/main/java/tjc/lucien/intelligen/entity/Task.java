package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/20.
 */

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String taskType;    /* 热任务类别，发布消息，发布工作 */
    private String taskTitle;   /* 任务标题 */
    private Date publishDate;   /* 发布日期 */
    private String publishDateStr; /* 发布日期显示版字符串 */
    private String status;      /* 发布状态，已提交，被退回，已修改，已发布，已撤销 */
    @ManyToOne(targetEntity = Adminer.class)
    @JoinColumn(name = "submiterId", referencedColumnName = "id")
    private Adminer submiter;   /* 提交人 */
    private String submiterName;/* 提交人姓名 */

    @ManyToOne(targetEntity = Adminer.class)
    @JoinColumn(name = "auditorId", referencedColumnName = "id")
    private Adminer auditor;    /* 审核人 */
    private String auditorName; /* 审核人姓名 */
    private String backOpinion; /* 退回意见 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getPublishDateStr() {
        return publishDateStr;
    }

    public void setPublishDateStr(String publishDateStr) {
        this.publishDateStr = publishDateStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Adminer getSubmiter() {
        return submiter;
    }

    public void setSubmiter(Adminer submiter) {
        this.submiter = submiter;
    }

    public String getSubmiterName() {
        return submiterName;
    }

    public void setSubmiterName(String submiterName) {
        this.submiterName = submiterName;
    }

    public Adminer getAuditor() {
        return auditor;
    }

    public void setAuditor(Adminer auditor) {
        this.auditor = auditor;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getBackOpinion() {
        return backOpinion;
    }

    public void setBackOpinion(String backOpinion) {
        this.backOpinion = backOpinion;
    }
}
