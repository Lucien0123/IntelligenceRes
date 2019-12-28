package tjc.lucien.intelligen.entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/3/10.
 * 不涉及删除
 */
@Entity
public class JobCategory{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jobCateName;      /* 工作类别名称 */
    private int parentId;            /* 工作类别的父id，第一级的工作类别为-1 */
    @Column(nullable = false, columnDefinition = "INT default 0")
    private int searchTimes;         /* 搜索访问次数 */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobCateName() {
        return jobCateName;
    }

    public void setJobCateName(String jobCateName) {
        this.jobCateName = jobCateName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getSearchTimes() {
        return searchTimes;
    }

    public void setSearchTimes(int searchTimes) {
        this.searchTimes = searchTimes;
    }
}
