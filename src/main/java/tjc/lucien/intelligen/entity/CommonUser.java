package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/3.
 */
@Entity
public class CommonUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nikename;    /* 昵称 */
    private String realname;    /* 真实姓名 */
    private String account;
    private String password;
    private String telphone;    /* 联系电话 */
    private int gender;         /* 性别 */
    private String occupation;  /* 职业 */
    private String personalDesc;/* 个人介绍 */
    private String headPortrait;/* 头像 */
    private String accessResume;/* 附件简历保存路径 */
    private String accessName;  /* 附件简历名称 */
    private int onlineResume;  /* 在线简历 */
    private String recentlyViewJobIdsStr; /* 最近浏览的工作idStr */
    @OneToMany(targetEntity = JobAppraise.class)
    private Set<JobAppraise> jobAppraiseSet = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNikename() {
        return nikename;
    }

    public void setNikename(String nikename) {
        this.nikename = nikename;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getPersonalDesc() {
        return personalDesc;
    }

    public void setPersonalDesc(String personalDesc) {
        this.personalDesc = personalDesc;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getAccessResume() {
        return accessResume;
    }

    public void setAccessResume(String accessResume) {
        this.accessResume = accessResume;
    }

    public int getOnlineResume() {
        return onlineResume;
    }

    public void setOnlineResume(int onlineResume) {
        this.onlineResume = onlineResume;
    }

    public Set<JobAppraise> getJobAppraiseSet() {
        return jobAppraiseSet;
    }

    public void setJobAppraiseSet(Set<JobAppraise> jobAppraiseSet) {
        this.jobAppraiseSet = jobAppraiseSet;
    }

    public String getRecentlyViewJobIdsStr() {
        return recentlyViewJobIdsStr;
    }

    public void setRecentlyViewJobIdsStr(String recentlyViewJobIdsStr) {
        this.recentlyViewJobIdsStr = recentlyViewJobIdsStr;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName;
    }
}
