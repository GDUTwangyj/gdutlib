package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dao.BaseDao;
import com.dao.FilmTypeDao;
import com.entity.Film;
import com.entity.FilmType;

public class FilmTypeDaoImpl extends BaseDao<FilmType> implements FilmTypeDao {

	@Override
	public FilmType queryById(int type_id) {
		String sql="select * from filmtype where ft_id="+type_id;
		 List<FilmType> list = this.query(sql, null);
		 if(list!=null&&list.size()>0){
			 return list.get(0);
		 }
		 return null;
	}

	@Override
	public FilmType getEntity(ResultSet rs) {
		FilmType ftype=new FilmType();
		try {
			ftype.setId(rs.getInt("ft_id"));
			ftype.setName(rs.getString("ft_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftype;
	}

	@Override
	public List<FilmType> queryByCondition(Map<String, Object> map) {
		List<Object> paramList=new ArrayList<Object>();
		StringBuffer sf=new StringBuffer();
		sf.append("select * from filmtype where 1=1 ");
		if(map!=null && map.size()>0){
			for(Entry e:map.entrySet()){
				if(e.getKey().equals("name")){
					sf.append("and ft_name like ?");
					paramList.add("%"+e.getValue()+"%");
				}
			}
		}		
		return this.query(sf.toString(), paramList.toArray());
	}

}
