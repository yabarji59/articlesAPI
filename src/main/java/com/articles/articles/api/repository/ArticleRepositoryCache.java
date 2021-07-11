package com.articles.articles.api.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.articles.articles.api.models.Article;
import com.articles.articles.api.service.CRUD;
import com.articles.articles.api.util.MockData;

/**
 * A service class without any annotations to demonstrate
 * a manual approach to maintaining an in-memory
 * articles cache
 */
public class ArticleRepositoryCache implements CRUD<Article> {

	private List<Article> articlesCache = new ArrayList<Article>();

	/**
	 * Constructor to populate it with mock data
	 * on startup
	 */
	public ArticleRepositoryCache() {

		if(this.articlesCache.size() == 0) {
			for(Article article: MockData.mockArticles()) {
				this.articlesCache.add(article);
			}
		}
	}
	
	@Override
	public Article post(Article obj) {
		if(this.articlesCache.size() > 0) {
			//an auto-increment id hack
			Long newId = this.articlesCache.get(this.articlesCache.size() - 1).getId();
			newId = (newId + 1L);
			obj.setId(newId); 
		} else {
			obj.setId(1L);
		}
		this.articlesCache.add(obj);
		return this.articlesCache.get((this.articlesCache.size() - 1));
	}
	/**
	 * @param id
	 */
	@Override
	public Article get(Long id) {
		if(this.articlesCache.size() == 0 || id == null) {
			return null;
		}
		List<Article> list = this.articlesCache.stream()
							.filter(a -> a.getId().longValue() == id.longValue())
							.collect(Collectors.toList());
		return list.get(0);
	}

	/**
	 * The final endpoint, GET /tags/{tagName}/{date} will return the list of 
	 * articles that have that tag name on the given date and some summary data 
	 * about that tag for that day.
	 * @param tags
	 * @param on
	 * @return
	 */
	public List<Article> getByTagForDate(String tag, Date on) {
		return ArticleSearch.search.getByTagFor(this.articlesCache, on, tag);
	}

	public List<Article> getByTag(String tag) {
		//what about equals ignores case?
		return ArticleSearch.search.getByTag(this.articlesCache, tag);
	}
	
	@Override
	public List<Article> list(String filter, String sort) {
		Optional<String> fOpts = Optional.ofNullable(filter);
		Optional <String> sOpts = Optional.ofNullable(sort);
		/** to be added later */
		if(fOpts.isPresent()) {
			//extra filter parameters
		}

		if(sOpts.isPresent()) {
			//sort params supplied
		}
		return this.articlesCache;
	}
}
