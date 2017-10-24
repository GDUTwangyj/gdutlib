package com.dao.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.entity.User;
import com.dao.BaseDao;
import com.dao.UserDao;

public class UserDaoImpl extends BaseDao<User> implements UserDao {

	@Override
	public Object addUser(User entity) {
		String sql="insert into users(u_id,uname,pwd,u_account,address) values(seq_users_id.nextval,?,?,?,?)";
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(entity.getUname());
		paramList.add(entity.getPassword());
		paramList.add(entity.getAccount());
		paramList.add(entity.getAddress());
		return this.executeUpdate(sql, paramList.toArray(),null);
	}

	@Override
	public boolean login(String name, String password) {
		String sql=" select * from users where u_account=? and pwd=?";
		List<User> list = this.query(sql, new Object[]{name,password});
		if(list!=null&&list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<User> query(String name) {
		String sql=" select * from users where u_account=?";
		return this.query(sql, new Object[]{name});
	}

	@Override
	public User getEntity(ResultSet rs) {
		User user=null;
		try {
			user=new User();
			user.setUid(rs.getInt("u_id"));
			user.setUname(rs.getString("uname"));
			user.setPassword(rs.getString("pwd"));
			user.setAccount(rs.getString("u_account"));
			user.setAddress(rs.getString("address"));
				return user;
			} catch (Exception e) {
				e.printStackTrace();
			}	
		return null;
	}


}
