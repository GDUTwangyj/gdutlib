package com.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.dao.FilmDao;
import com.dao.FilmTypeDao;
import com.dao.impl.FilmDaoImpl;
import com.dao.impl.FilmTypeDaoImpl;
import com.entity.Film;
import com.entity.FilmType;
import com.entity.PageBean;
import com.opensymphony.xwork2.ActionSupport;

public class FilmAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	HttpServletResponse response=ServletActionContext.getResponse();
	private Film film;
	private FilmDao filmDao=new FilmDaoImpl();
	private FilmTypeDao filmTypeDao=new FilmTypeDaoImpl();
	private String path;
	private File upload;  //upload的变量名和上传文件控件的name属性值相同
	private String uploadContentType;  //上传文件的类型：jpg
	private String uploadFileName; //上传文件的名字
	private String savePath; //上传文件的路径
	private String type;
	private int fid;
	private String downPath; //下载文件的根路径
	private String downFileName; //现在文件时显示在浏览器下载框中的文件名
	private InputStream inputStream;
	private String movieName;
	private String stars;
	private String flimtype;
	private PageBean<Film> pb;
	public PageBean<Film> getPb() {
		return pb;
	}
	public void setPb(PageBean<Film> pb) {
		this.pb = pb;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getFlimtype() {
		return flimtype;
	}
	public void setFlimtype(String flimtype) {
		this.flimtype = flimtype;
	}
	public String getDownPath() {
		return downPath;
	}
	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}
	public String getDownFileName() {
		return downFileName;
	}
	public void setDownFileName(String downFileName) {
		this.downFileName = downFileName;
	}
	
	//将要下载的文件以流的方式返回
	public InputStream getInputStream() throws FileNotFoundException {
		//downPath的实际路径：系统下的路径
		String dpath = ServletActionContext.getServletContext().getRealPath(downPath);
		return new BufferedInputStream(new FileInputStream(dpath+File.separator+downFileName));
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getSavePath() {		
		return ServletActionContext.getServletContext().getRealPath("/upload");
	}
	public void setSavePath() {
		this.savePath = "/upload";
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String loadData() throws IOException{
		/*response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		pb = filmDao.queryByCondition(1,5,null);
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		json.accumulate("pb", pb);
		out.print(json.toString());
		System.out.println(json.toString());
		out.flush();
		out.close();*/
		pb = filmDao.queryByCondition(1,5,null);
		path="index.jsp";
		return SUCCESS;
	}
	public void loadtype() throws IOException{
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		List<FilmType> list = filmTypeDao.queryByCondition(null);
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		json.accumulate("ftlist", list);
		out.print(json.toString());
		out.flush();
		out.close();
	}
	public String addFilm() throws Exception{
		File rootPath = new File(this.getSavePath());
		if(!rootPath.exists()){
			rootPath.mkdirs();
		}
		//读取文件
		FileInputStream fis = new FileInputStream(upload);
		//创建一个输出流用来写文件
		//File.separator 自适应的斜杠
		FileOutputStream fos = new FileOutputStream(this.getSavePath()+File.separator+uploadFileName);
		//通过复制文件，直接保存
		IOUtils.copy(fis, fos);
		fos.close();
		fis.close();
		film.setFilmType(filmTypeDao.queryById(Integer.parseInt(type)));
		film.setPath(uploadFileName);
		Object object = filmDao.addFilm(film);
		if((Integer)object>0){
			path="film_loadData";
			return "jump";
		}else{
			path="message.jsp";
			return SUCCESS;
		}
	}
	public String update() throws Exception{
		File rootPath = new File(this.getSavePath());
		if(!rootPath.exists()){
			rootPath.mkdirs();
		}
		//读取文件
		FileInputStream fis = new FileInputStream(upload);
		//创建一个输出流用来写文件
		//File.separator 自适应的斜杠
		FileOutputStream fos = new FileOutputStream(this.getSavePath()+File.separator+uploadFileName);
		//通过复制文件，直接保存
		IOUtils.copy(fis, fos);
		fos.close();
		fis.close();
		film.setFilmType(filmTypeDao.queryById(Integer.parseInt(type)));
		film.setPath(uploadFileName);
		int i = filmDao.updateFilm(film);
		if(i>0){
			path="film_loadData";
			return "jump";
		}else{
			path="message.jsp";
			return SUCCESS;
		}
	}
	public String toUpdateFilm(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("f_id", fid);
		pb = filmDao.queryByCondition(1,5,map);
		film=pb.getList().get(0);
		path="updateFilm.jsp";
		return SUCCESS;
	}
	public String content(){
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("f_id", fid);
		pb = filmDao.queryByCondition(1,5,map);
		film=pb.getList().get(0);
		path="filmContent.jsp";
		return SUCCESS;
	}
	public void query() throws Exception{
		response.setContentType("text/xml;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");
		Map<String,Object> map=new HashMap<String, Object>();
		if(movieName!=null&&!movieName.equals("")){
			map.put("fname", movieName);
		}
		if(stars!=null&&!stars.equals("")){
			map.put("stars", stars);
		}
		System.out.println(type);
		int tid=Integer.parseInt(type);
		if(tid!=0){
			map.put("type_id",tid );
		}
		if(map!=null&&map.size()>0){
			pb = filmDao.queryByCondition(pb.getPageNow(),5,map);
		}else{
			pb = filmDao.queryByCondition(pb.getPageNow(), 5,null);
		}
		PrintWriter out=response.getWriter();
		JSONObject json=new JSONObject();
		json.accumulate("pb", pb);
		out.print(json.toString());
		System.out.println(json.toString());
		out.flush();
		out.close();
	}
	public String down(){
		
		return "download";
	}
}
