package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.Film;
import com.entity.PageBean;

public interface FilmDao {
	public PageBean<Film> queryByCondition(int pageNow,int pageSize,Map<String ,Object > map);
	
	public Object addFilm(Film film);
	
	public int updateFilm(Film film);
}
