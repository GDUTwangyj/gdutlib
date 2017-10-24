package com.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.entity.City;
import com.entity.Provinces;
import com.opensymphony.xwork2.ActionSupport;

public class ProvincesAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletRequest request=ServletActionContext.getRequest();
	private Map<Integer, List<City>> map;//数据是存储到map集合
	private List<Provinces> prolist;
	private Provinces provinces;
	public void setProlist(List<Provinces> prolist) {
		this.prolist = prolist;
	}
	private City city;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Provinces getProvinces() {
		return provinces;
	}

	public void setProvinces(Provinces provinces) {
		this.provinces = provinces;
	}

	public Map<Integer, List<City>> getMap() {
		return map;
	}

	public void setMap(Map<Integer, List<City>> map) {
		this.map = map;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	public List<Provinces> getProlist() {
		return prolist;
	}
	@Override
	public String execute() throws Exception {
		//1、使用map存储数据
		//2、主与子之间级联是通过对象进行关联
		
		Provinces p1=new Provinces(1,"广东省");
		Provinces p2=new Provinces(2,"广西省");
		Provinces p3=new Provinces(3,"湖南省");
		
		City c1=new City(1,"广州市",p1);
		City c2=new City(2,"深圳市",p1);
		City c3=new City(3,"桂林市",p2);
		City c4=new City(4,"南宁市",p2);
		City c5=new City(5,"长沙市",p3);
		City c6=new City(6,"株洲市",p3);
		
		List<City> cityList1=new ArrayList<City>();
		List<City> cityList2=new ArrayList<City>();
		List<City> cityList3=new ArrayList<City>();
		
		cityList1.add(c1);
		cityList1.add(c2);
		cityList2.add(c3);
		cityList2.add(c4);
		cityList3.add(c5);
		cityList3.add(c6);
		prolist=new ArrayList<Provinces>();
		prolist.add(p1);
		prolist.add(p2);
		prolist.add(p3);
		map=new HashMap<Integer, List<City>>();
		map.put(p1.getId(), cityList1);
		map.put(p2.getId(), cityList2);
		map.put(p3.getId(), cityList3);
		request.setAttribute("map", map); 
		request.setAttribute("prolist", prolist); 
		return SUCCESS;
	}
}
