package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.entity.City;
import com.entity.Provinces;
import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private User user;
	private String city;
	private String province;
	private String street;
	private String path;
	private String name;
	private UserDao userDao=new UserDaoImpl();
	private static boolean exsit=false;
	HttpServletResponse response=ServletActionContext.getResponse();
	private List<Provinces> prolist;
	private Map<String,String> fieldError;
	public Map<String, String> getFieldError() {
		return fieldError;
	}
	public void setFieldError(Map<String, String> fieldError) {
		this.fieldError = fieldError;
	}
	public List<Provinces> getProlist() {
		return prolist;
	}
	public void setProlist(List<Provinces> prolist) {
		this.prolist = prolist;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public boolean isExsit() {
		return exsit;
	}
	public void setExsit(boolean exsit) {
		this.exsit = exsit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String register(){
			System.out.println("account:"+user.getAccount());
			System.out.println("password:"+user.getPassword());
			System.out.println("uname:"+user.getUname());
			System.out.println(province);
			System.out.println(city);
			if(user!=null){
				if(user.getAccount()==null||"".equals(user.getAccount())){
					addFieldError("error", "账号不能为空");
					path="provinces";
					return "jump";
				}
				if(user.getPassword()==null||"".equals(user.getPassword())){
					this.addFieldError("error", "密码不能为空");
					path="provinces";
					return "jump";
				}
			}
			user.setAddress(province+city+street);
			Object object = userDao.addUser(user);
			if((Integer)object>0){
				path="film_loadData";
				return "jump";
			}
			return null;
			
	}
	public void checkName() throws IOException{
		response.setContentType("text/xml;charset=UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
		System.out.println("name:"+name);
		PrintWriter out=ServletActionContext.getResponse().getWriter();
		JSONObject json=new JSONObject();
		String message="";
		if(name==null||"".equals(name)){
			message="账号不能为空";
		}else{
			List<User> list = userDao.query(name);
			boolean isFlag=false;
			if(list!=null&&list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getAccount().equals(name)){
						isFlag=true;
						break;
					}
				}
			}
			exsit=isFlag?true:false;
			message=isFlag?"该账号已存在":"";
		}
		json.accumulate("msg",message);
		out.print(json.toString());
		out.flush();
		out.close();
	}
	public String login(){
		boolean flag = userDao.login(user.getAccount(), user.getPassword());
		if(flag){
			path="film_loadData";
			return "jump";
		}else{
			this.addFieldError("error", "账号或密码错误");
			path="login.jsp";
			return SUCCESS;
		}
	}
	public void validateLogin() {
		path="login.jsp";
		if(user!=null){
			if(user.getAccount()==null||"".equals(user.getAccount())){
				this.addFieldError("loginName", "账号不能为空");
			}
			if(user.getPassword()==null||"".equals(user.getPassword())){
				this.addFieldError("loginPwd", "密码不能为空");
			}
		}
	}
	
	
}
