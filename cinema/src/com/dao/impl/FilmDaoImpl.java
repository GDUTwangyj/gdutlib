package com.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.dao.BaseDao;
import com.dao.FilmDao;
import com.dao.FilmTypeDao;
import com.entity.Film;
import com.entity.PageBean;

public class FilmDaoImpl extends BaseDao<Film> implements FilmDao {
	FilmTypeDao filmTypeDao=new FilmTypeDaoImpl();
	@Override
	public PageBean<Film> queryByCondition(int pageNow,int pageSize,Map<String, Object> map) {
		List<Object> paramList=new ArrayList<Object>();
		StringBuffer sf=new StringBuffer();
		sf.append("select * from film where 1=1 ");
		if(map!=null && map.size()>0){
			for(Entry e:map.entrySet()){
				if(e.getKey().equals("fname")){
					sf.append("and fname like ?");
					paramList.add("%"+e.getValue()+"%");
				}if(e.getKey().equals("type_id")){
					sf.append("and type_id=?");
					paramList.add(e.getValue());
				}if(e.getKey().equals("f_id")){
					sf.append("and f_id=?");
					paramList.add(e.getValue());
				}if(e.getKey().equals("stars")){
					sf.append("and stars like ?");
					paramList.add("%"+e.getValue()+"%");
				}
			}
		}
		PageBean<Film> pb=new PageBean<Film>();
		pb.setPageNow(pageNow);
		pb.setPageSize(pageSize);
		pb.setRowCount(getCount(sf.toString(), paramList.toArray()));
		if(pb.getRowCount()>0){
			StringBuffer queryPageSql=new StringBuffer();
			queryPageSql.append(" select * from (");
			queryPageSql.append("  select tmp.*,rownum rn from ( ");
			queryPageSql.append(sf.toString());
			queryPageSql.append("  ) tmp ");
			queryPageSql.append(" ) where rn>? and rn<=? ");
			//当前页的上一页中的最后一条数据
			int startRow = (pb.getPageNow()-1)*pb.getPageSize();
			//当前页的最大条数
			int endRow = pb.getPageNow()*pb.getPageSize();
			paramList.add(startRow);
			paramList.add(endRow);
			pb.setList(this.query(queryPageSql.toString(), paramList.toArray()));
		}
		return pb;
	}

	@Override
	public Film getEntity(ResultSet rs) {
		Film film=null;
		try {
			film=new Film();
			film.setId(rs.getInt("f_id"));
			film.setName(rs.getString("fname"));
			film.setStar(rs.getString("stars"));
			film.setContent(rs.getString("f_content"));
			film.setFilmType(filmTypeDao.queryById(rs.getInt("type_id")));
			film.setPath(rs.getString("f_path"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Object addFilm(Film entity) {
		String sql="insert into film(f_id,fname,stars,f_content,type_id,f_path) values(seq_film_id.nextval,?,?,?,?,?)";
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(entity.getName());
		paramList.add(entity.getStar());
		paramList.add(entity.getContent());
		paramList.add(entity.getFilmType().getId());
		paramList.add(entity.getPath());
		return this.executeUpdate(sql, paramList.toArray(),null);
	}

	@Override
	public int updateFilm(Film entity) {
		String sql="update film set fname=?,stars=?,f_content=?,type_id=?,f_path=? where f_id=?";
		List<Object> paramList=new ArrayList<Object>();
		paramList.add(entity.getName());
		paramList.add(entity.getStar());
		paramList.add(entity.getContent());
		paramList.add(entity.getFilmType().getId());
		paramList.add(entity.getPath());
		paramList.add(entity.getId());
		return (Integer) this.executeUpdate(sql, paramList.toArray(),null);
	}

}
