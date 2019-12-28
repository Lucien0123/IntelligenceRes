package tcu.lucien.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单实体类
 */
@Entity
@Table
public class Orders implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String number;/* '订单号', */
	private Integer buyerId;/* '所属买家会员编号', */
	private Integer total_amount;/* '商品总数量', */
	private BigDecimal total_price;/* '总的费用', */
	private BigDecimal payment_price;/* '实付的费用', */
	private String remark;/* '买家留言', */
	private String contact;/* '收货人', */
	private String mobile;/* '联系电话', */
	private String street;/* '详细地址', */
	private String zipcode;/* '邮编', */
	private Date create_time;/* '下单时间', */
	private Date payment_time;/* '支付时间', */
	private Date delivery_time;/* '发货时间', */
	private Date end_time;/* '完成时间', */
	
	/* comment '订单状态：0下单,1待付款,2已付款,3待发货,4已发货,5己收货,6已完成,-1已取消' */
	private int status;
	@Transient
	private List<Item> items = new ArrayList<Item>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	public Integer getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Integer total_amount) {
		this.total_amount = total_amount;
	}

	public BigDecimal getTotal_price() {
		return total_price;
	}

	public void setTotal_price(BigDecimal total_price) {
		this.total_price = total_price;
	}

	public BigDecimal getPayment_price() {
		return payment_price;
	}

	public void setPayment_price(BigDecimal payment_price) {
		this.payment_price = payment_price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(Date payment_time) {
		this.payment_time = payment_time;
	}

	public Date getDelivery_time() {
		return delivery_time;
	}

	public void setDelivery_time(Date delivery_time) {
		this.delivery_time = delivery_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
