package com.articles.articles.api.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.articles.articles.api.models.Article;

public class ArticlesService implements CRUD<Article> {
	
	private List<Article> articlesCache = new ArrayList<Article>();
	
	private void p(Object o) {
		System.out.println(o);
	}
	@Override
	public Article post(Article obj) {
		Logger.getLogger("ArticleService").log(Level.INFO, "About to create an article post");
		if(this.articlesCache.size() > 0) {
			Long newId = this.articlesCache.get(this.articlesCache.size() - 1).getId();
			newId = (newId + 1L);
			obj.setId(newId); 
		} else {
			obj.setId(1L);
		}
		this.articlesCache.add(obj);
		//return the last element inserted
		return this.articlesCache.get((this.articlesCache.size() - 1));
	}
	
	@Override
	public Article get(Long id) {
		if(this.articlesCache.size() == 0) {
			return null;
		}
		List<Article> list = this.articlesCache.stream().filter(a -> a.getId().longValue() == id.longValue()).collect(Collectors.toList());
		return list.get(0);
	}

	/**
	 * The final endpoint, GET /tags/{tagName}/{date} will return the list of 
	 * articles that have that tag name on the given date and some summary data about that tag for that day.
	 * @param tags
	 * @param on
	 * @return
	 */
	public List<Article> getByTagForDate(String tag, Date on) {
		this.p("1");
		List<Article> searchResult = new ArrayList<Article>();
		LocalDate aTargetDate = this.toLocalDate(on);
		this.p("2");
		this.p(aTargetDate);
		this.p(tag);
		for(Article article: this.articlesCache) {
			LocalDate aDate = this.toLocalDate(article.getDate());
			//if it's on the same day
			this.p("3");
			this.p(aDate);
			this.p(article);
			if(aDate.isEqual(aTargetDate)) {
				//then search through the tags
				if (Arrays.asList(article.getTags()).stream().anyMatch(t -> tag.equalsIgnoreCase(t))) {
					searchResult.add(article);
				}
			}
		}
		return searchResult;
	}
	
	/**
	 * Get the date instant and return local date for the 
	 * zone
	 * @param date
	 * @return
	 */
	private LocalDate toLocalDate(Date date) {
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
				
		Instant instant = date.toInstant();
		
		return instant.atZone(defaultZoneId).toLocalDate();
	}
	
	@Override
	public List<Article> list(String filter, String sort) {
		// TODO Auto-generated method stub
		return null;
	}

}
