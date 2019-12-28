package tjc.lucien.intelligen.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2017/3/20.
 */
@Entity
public class Adminer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String account;
    private String password;
    private String realname;          /* 真实姓名 */
    private String adminNumber;       /* 管理员编号 */
    private String adminerPosition;   /* 管理员职位 */
    private Integer gender;           /* 管理员性别 */
    private String telphone;          /* 管理员联系方式 */
    private String remark;            /* 备注  */
    private String headPortrait;      /* 头像 */
    @Temporal(TemporalType.DATE)
    private Date lastLoginDate;       /* 最近一次登录时间 */
    @ManyToOne(targetEntity = ParkCompany.class)
    @JoinColumn(name = "companyId", referencedColumnName = "id")
    private ParkCompany parkCompany;  /* 所属公司, 园区管理员不适于任何公司 */
    private int power;                /* 权限，管理员权限()，公司管理者权限()，普通员工不能登录 */
    @OneToMany(targetEntity = Task.class)
    private Set<Task> taskSet = new HashSet<>();       /* 任务列表 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getAdminNumber() {
        return adminNumber;
    }

    public void setAdminNumber(String adminNumber) {
        this.adminNumber = adminNumber;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public ParkCompany getParkCompany() {
        return parkCompany;
    }

    public void setParkCompany(ParkCompany parkCompany) {
        this.parkCompany = parkCompany;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public void setTaskSet(Set<Task> taskSet) {
        this.taskSet = taskSet;
    }

    public String getAdminerPosition() {
        return adminerPosition;
    }

    public void setAdminerPosition(String adminerPosition) {
        this.adminerPosition = adminerPosition;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
