package com.entity;

public class User {
	private int uid;
	private String account;
	private String password;
	private String uname;
	private String address;
	public User() {
		super();
	}
	public User(int uid, String account, String password, String uname,String address) {
		super();
		this.uid = uid;
		this.account = account;
		this.password = password;
		this.uname = uname;
		this.address=address;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
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
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
