package com.articles.articles.api.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.articles.articles.api.models.Article;
import com.articles.articles.api.util.MockData;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService implements CRUD<Article> {

	private List<Article> articlesCache = new ArrayList<Article>();

	/**
	 * Constructor to populate it with mock data
	 * on startup
	 */
	public ArticlesService() {

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
		//return the last element inserted
		return this.articlesCache.get((this.articlesCache.size() - 1));
	}
	/**
	 * @param id
	 */
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
	 * articles that have that tag name on the given date and some summary data 
	 * about that tag for that day.
	 * @param tags
	 * @param on
	 * @return
	 */
	public List<Article> getByTagForDate(String tag, Date on) {
		List<Article> searchResult = new ArrayList<Article>();
		LocalDate aTargetDate = this.toLocalDate(on);
		for(Article article: this.articlesCache) {
			LocalDate aDate = this.toLocalDate(article.getDate());
			//if it's on the same day
			if(aDate.isEqual(aTargetDate)) {
				//then search through the tags
				if (Arrays.asList(article.getTags()).stream().anyMatch(t -> tag.equalsIgnoreCase(t))) {
					searchResult.add(article);
				}
			}
		}
		return searchResult;
	}

	public List<Article> getByTag(String tag) {
		Logger.getGlobal().log(Level.INFO,"getting articles by tag");
		//what about equals ignores case?
		List<Article> searchResult = new ArrayList<Article>();
		searchResult = this.articlesCache.stream().filter(t -> Arrays.asList(t.getTags()).contains(tag)).collect(Collectors.toList());
		
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
		Optional<String> fOpts = Optional.ofNullable(filter);
		Optional <String> sOpts = Optional.ofNullable(sort);
		if(fOpts.isPresent()) {
			//filter articles

		}
		if(sOpts.isPresent()) {
			//sort the articles
		}
		Logger.getLogger("artService").log(Level.INFO, "queried all the optionals");
		return this.articlesCache;
	}

}
