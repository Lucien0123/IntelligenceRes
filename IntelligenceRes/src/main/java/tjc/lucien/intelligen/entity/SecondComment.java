package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/3.
 * 二级评论,根据id自增排序
 */
@Entity
public class SecondComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String replier;    /* 回复者  -- 回复者 回复 被回复者  */
    private String beReplier;  /* 被回复者 */
    private String replyAccount;  /* 回复者帐号 */
    private String replyContent;/* 回复内容 */
    @Temporal(TemporalType.TIME)
    private Date commonentDate; /* 回复日期 */
    private boolean commentStatus;      /* 评论状态，是否已接受到通知 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier;
    }

    public String getBeReplier() {
        return beReplier;
    }

    public void setBeReplier(String beReplier) {
        this.beReplier = beReplier;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public Date getCommonentDate() {
        return commonentDate;
    }

    public void setCommonentDate(Date commonentDate) {
        this.commonentDate = commonentDate;
    }

    public boolean isCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    public String getReplyAccount() {
        return replyAccount;
    }

    public void setReplyAccount(String replyAccount) {
        this.replyAccount = replyAccount;
    }
}
