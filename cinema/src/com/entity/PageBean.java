package com.entity;

import java.util.List;

public class PageBean<T> {
	private int rowCount;   //获取到数据的总记录数
	private int pageSize;  //页面的大小
	private int pageNow;   //当前页
	private int pageCount;  //总页数
	private List<T> list;   //存储查询出来的数据
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		//当总记录数改变的时候，总页数也会跟着变
		setPageCount();
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		//当页面显示条数改变的同时，总页数也会跟着改变
		setPageCount();
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount() {
		//总页数的改变，是通过总记录数与显示条数运算出来的
		this.pageCount = this.rowCount%this.pageSize==0?
				this.rowCount/this.pageSize:this.rowCount/this.pageSize+1;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
}
