package tcu.lucien.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
public class Product implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;        //产品的id
	private String name;   //产品的名称
	private int cateid;    //产品的类型
	private String thumbnail;   //产品的小图片
	private String prodswaper;  //商品详情页面的轮播图片
	private int inventory;      //产品的库存量
	private int salescount;     //卖出的数量
	private BigDecimal price;       //原价
	private BigDecimal saleprice;   //售价
	private String detaildescription; //产品的详细描述
	private Date saletime;      //产品上架日期
	private int type;           //商品类型
	public int getId() {
		return id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getProdswaper() {
		return prodswaper;
	}
	public void setProdswaper(String prodswaper) {
		this.prodswaper = prodswaper;
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
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public int getInventory() {
		return inventory;
	}
	public void setInventory(int inventory) {
		this.inventory = inventory;
	}
	public int getSalescount() {
		return salescount;
	}
	public void setSalescount(int salescount) {
		this.salescount = salescount;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getSaleprice() {
		return saleprice;
	}
	public void setSaleprice(BigDecimal saleprice) {
		this.saleprice = saleprice;
	}
	public String getDetaildescription() {
		return detaildescription;
	}
	public void setDetaildescription(String detaildescription) {
		this.detaildescription = detaildescription;
	}
	public Date getSaletime() {
		return saletime;
	}
	public void setSaletime(Date saletime) {
		this.saletime = saletime;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", cateid=" + cateid + ", thumbnail=" + thumbnail
				+ ", inventory=" + inventory + ", salescount=" + salescount + ", price=" + price + ", saleprice="
				+ saleprice + ", detaildescription=" + detaildescription + ", saletime=" + saletime + "]";
	}
	
	
}
