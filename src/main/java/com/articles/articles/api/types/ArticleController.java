package com.articles.articles.api.types;

import java.util.List;

public interface ArticleController<T> {
    
    public List<T> getAll();

    public T create(T obj);

    public T getObject(Long id);

    
}
