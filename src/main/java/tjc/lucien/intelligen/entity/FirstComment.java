package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3.
 * 对于问题的评论（包括一级评论和二级评论，类似腾讯空间）
 */
@Entity
public class FirstComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String commentContent;     /* 评论内容 */
    @Temporal(TemporalType.DATE)
    private Date commentDate;        /* 评论日期 */
    private boolean commentStatus;      /* 评论状态，是否已接受到通知 */
    @ManyToOne(targetEntity = CommonUser.class)
    @JoinColumn(name = "commonuserId", referencedColumnName = "id", nullable = false)
    private CommonUser commenter;      /* 评论者 */
    private int questionId;
    @OneToMany(targetEntity = SecondComment.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<SecondComment> secondComments = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public boolean isCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(boolean commentStatus) {
        this.commentStatus = commentStatus;
    }

    public CommonUser getCommenter() {
        return commenter;
    }

    public void setCommenter(CommonUser commenter) {
        this.commenter = commenter;
    }

    public Set<SecondComment> getSecondComments() {
        return secondComments;
    }

    public void setSecondComments(Set<SecondComment> secondComments) {
        this.secondComments = secondComments;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
