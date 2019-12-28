package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/20.
 * 此处不做多表关联，防止删除人员公司时的问题
 */
@Entity
public class NewsAnnouncement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String publisher;     /* 发布者 */
    @Temporal(TemporalType.DATE)
    private Date publicDate;      /* 发布日期 */
    private String newsTitle;     /* 新闻公告标题 */
    private String newsContent;   /* 新闻内容 */
    @Column(nullable=false,columnDefinition="INT default 0")
    private int viewCount;        /* 浏览次数 */
    private String companyName;   /* 公司名称 */
    private String auditorName;   /* 审核人姓名 */
    private String auditorId;     /* 审核人id */
    private Integer taskId;       /* 新闻发布对应任务id */
    private boolean published;    /* 是否已经发布 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(Date publicDate) {
        this.publicDate = publicDate;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getAuditorId() {
        return auditorId;
    }

    public void setAuditorId(String auditorId) {
        this.auditorId = auditorId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public boolean isPublished() {
        return published;
    }

    public void setPublished(boolean published) {
        this.published = published;
    }
}
