package tcu.lucien.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 会员的地址
 */

@Entity
@Table
public class Address implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;            //地址ID
	private String contact;    //收货人
	private String mobile;     //联系电话
	private String street;     //详细地址
	private String zipcode;    //邮政编码
	private String username;   //所属会员（发件人）
	private boolean defaultvalue;  //默认地址标识

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isDefaultvalue() {
		return defaultvalue;
	}

	public void setDefaultvalue(boolean defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
}
