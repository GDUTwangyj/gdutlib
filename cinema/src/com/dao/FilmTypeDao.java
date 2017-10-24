package com.dao;

import java.util.List;
import java.util.Map;

import com.entity.FilmType;

public interface FilmTypeDao {
	public FilmType queryById(int type_id);
	public List<FilmType> queryByCondition(Map<String ,Object > map);
}
