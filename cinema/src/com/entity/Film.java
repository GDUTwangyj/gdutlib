package com.entity;

public class Film {
	private int id;
	private String name;
	private String star;
	private String content;
	private String path;
	private FilmType filmType;
	public Film() {
		super();
	}
	public Film(int id, String name, String star, String content,
			String path) {
		super();
		this.id = id;
		this.name = name;
		this.star = star;
		this.content = content;
		this.path=path;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStar() {
		return star;
	}
	public void setStar(String star) {
		this.star = star;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public FilmType getFilmType() {
		return filmType;
	}
	public void setFilmType(FilmType filmType) {
		this.filmType = filmType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}
