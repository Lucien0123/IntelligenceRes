package tcu.lucien.entity;

import javax.persistence.*;

@Table
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;        //商品类别的ID
    private String name;   //商品类别的名称
    private String alias;  //商品类别的变量标识
    private int pid;       //商品类别的类型码（即一级目录的id）总过有8个一级类别，
    private int orderweight;  //排序的权重，通过降序排列（desc）将该列中值大的放在前面

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getOrderweight() {
        return orderweight;
    }

    public void setOrderweight(int orderweight) {
        this.orderweight = orderweight;
    }
}
