package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3.
 * 公司问答
 */
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String quesContent;  /* 问题内容 */
    @ManyToOne(targetEntity = CommonUser.class)
    @JoinColumn(name = "commonuserId", referencedColumnName = "id", nullable = false)
    private CommonUser quizzer;  /* 提问者 */
    @ManyToOne(targetEntity = ParkCompany.class)
    @JoinColumn(name = "cid", referencedColumnName = "id", nullable = false)
    private ParkCompany relatedCompany;   /* 问题所属公司 */
    private int companyId;
    @OneToMany(targetEntity = FirstComment.class)
    private Set<FirstComment> firstCommentSet = new HashSet<>();
    @Temporal(TemporalType.DATE)
    private Date quizDate;     /* 提问日期 */
    private String questionLabels;  /* 问题标签，根据&br&标识符切分 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuesContent() {
        return quesContent;
    }

    public void setQuesContent(String quesContent) {
        this.quesContent = quesContent;
    }

    public CommonUser getQuizzer() {
        return quizzer;
    }

    public void setQuizzer(CommonUser quizzer) {
        this.quizzer = quizzer;
    }

    public ParkCompany getRelatedCompany() {
        return relatedCompany;
    }

    public void setRelatedCompany(ParkCompany relatedCompany) {
        this.relatedCompany = relatedCompany;
    }

    public Set<FirstComment> getFirstCommentSet() {
        return firstCommentSet;
    }

    public void setFirstCommentSet(Set<FirstComment> firstCommentSet) {
        this.firstCommentSet = firstCommentSet;
    }

    public Date getQuizDate() {
        return quizDate;
    }

    public void setQuizDate(Date quizDate) {
        this.quizDate = quizDate;
    }

    public String getQuestionLabels() {
        return questionLabels;
    }

    public void setQuestionLabels(String questionLabels) {
        this.questionLabels = questionLabels;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
