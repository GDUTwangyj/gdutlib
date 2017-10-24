package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDao<T> {
	private static String driverClass="oracle.jdbc.OracleDriver";
	private String url="jdbc:oracle:thin:@localhost:1521:orcl";
	private String username="scott";
	private String password="tiger";
	
	static{
		try {
			Class.forName(driverClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection(){
		try {
			return DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeAll(ResultSet rs,Statement sta,Connection conn){
		try {
			if(rs!=null)rs.close();
			if(sta!=null)sta.close();
			if(conn!=null)conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	protected Object executeUpdate(String sql,Object[] objs,String[] primaryKeys){
		//1、获得连接对象
		Connection conn=this.getConnection();
		
		//2、拼接sql语句
		PreparedStatement prs=null;
		ResultSet rs=null;
		int rows=0;
		try {
			//3、获得语句集
			if(primaryKeys!=null){
				prs=conn.prepareStatement(sql,primaryKeys);
			}else{
				prs=conn.prepareStatement(sql);
			}			
			//3、设置占位符的值
			if(objs!=null && objs.length>0){
				for(int i=0;i<objs.length;i++){
					prs.setObject(i+1, objs[i]);
				}
			}
			rows=prs.executeUpdate();
			if(primaryKeys!=null){
				//如果是新增功能那么需要将主键获取并返回
				//获取主键
				rs=prs.getGeneratedKeys();
				if(rs.next()){
					int primaryKey=rs.getInt(1);//获取主键值
					return new Object[]{rows,primaryKey};//新增
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally{
			this.closeAll(null, prs, conn);
		}
		return rows;
	}
	public List<T> query(String sql,Object[] params){
		List<T> list=new ArrayList<T>();
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		conn=this.getConnection();
		try {
			pre=conn.prepareStatement(sql);
			if(params!=null&&params.length>0){
				for(int i=0;i<params.length;i++){
					pre.setObject(i+1, params[i]);
				}
			}
			rs=pre.executeQuery();
			while(rs.next()){
				list.add(this.getEntity(rs));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			this.closeAll(rs, pre, conn);
		}
		return null;
	}
	
	protected int getCount(String sql,Object[] objs){
		String countSql=" select count(*) c from ("+sql+")";
		//3.1、获得连接对象
		Connection conn=this.getConnection();
		PreparedStatement prs=null;
		ResultSet rs=null;
		try {
			//3.2 获得语句集
			prs=conn.prepareStatement(countSql);
			//3.2.1、 设置占位符参数值
			if(objs!=null && objs.length>0){
				for(int i=0;i<objs.length;i++){
					prs.setObject(i+1, objs[i]);
				}
			}
			//3.3、执行查询
			rs=prs.executeQuery();
			if(rs.next()){
				return rs.getInt("c");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			this.closeAll(rs, prs, conn);
		}
		return 0;
	}
	public abstract T getEntity(ResultSet rs);
}
