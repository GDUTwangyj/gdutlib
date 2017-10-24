package com.entity;

public class City {
	private int cid;
	private String cname;
	private Provinces province;
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	public City(int cid, String cname, Provinces province) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.province = province;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Provinces getProvince() {
		return province;
	}
	public void setProvince(Provinces province) {
		this.province = province;
	}
	
}
