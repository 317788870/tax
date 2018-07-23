package com.tsms.entity;

import java.io.Serializable;

/**
 *
 * @Author: zhoujunpeng
 * @Date: 2018年7月10日 下午2:38:38
 */
public class User implements Serializable {

	private int id;
	private String username;
	private String password;
	private int taxerId;
	private String salt;
	private int permissionId;
	private int state;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getTaxerId() {
		return taxerId;
	}
	public void setTaxerId(int taxerId) {
		this.taxerId = taxerId;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getPermissionId() {
		return permissionId;
	}
	public void setPermissionId(int permissionId) {
		this.permissionId = permissionId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
