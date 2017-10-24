package com.entity;

import java.util.HashSet;
import java.util.Set;

public class Provinces {
	private int id;
	private String name;
	
	private Set<City> citys=new HashSet<City>();
	public Provinces() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Provinces(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
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
	public Set<City> getCitys() {
		return citys;
	}
	public void setCitys(Set<City> citys) {
		this.citys = citys;
	}
}
