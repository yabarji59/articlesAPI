package com.articles.articles.api.service;

import java.util.List;

public interface CRUD<T> {
	
	public T post(T obj);
	
	public T get(Long id);
	
	public List<T> list(String filter, String sort);
	
	
}
