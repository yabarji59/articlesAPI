package com.articles.articles.api;

import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import com.articles.articles.api.models.Article;
import com.articles.articles.api.repository.ArticleRepositoryCache;

@RestController()
public class ArticleController {
	
	private ArticleRepositoryCache service = new ArticleRepositoryCache();

	
	@GetMapping("/all") 
	public List<Article> allArticles(){
		return this.service.list(null, null);
	}
	/**
	 * The first endpoint, POST /articles should handle the receipt of some 
	 * article data in json format, and store it within the service.
	 * @param article
	 * @return
	 */
	@PostMapping("/articles")
	public Article create(@Validated @RequestBody Article article) {
		if(article == null) {
			System.out.println("Found an error");
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
		return service.post(article);
	}
	/**
	 * The second endpoint GET /articles/{id} should return the JSON representation of the article.
	 * @param id
	 * @return
	 */
	@GetMapping("/articles/{id}")
	public Article getArticle(@PathVariable(value = "id") Long id) {
		
		if(id == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
		Article article = this.service.get(id);
		return article;
	}
	/**
	 * The final endpoint, GET /tags/{tagName}/{date} will return the list of 
	 * articles that have that tag name on the given date and some summary data 
	 * about that tag for that day.
	 * @param tag
	 * @param date
	 * @return
	 */
	@GetMapping("/tag/{tagName}/{date}") 
	public List<Article> articlesByTag(@PathVariable(value = "tagName") String tag, @PathVariable(value = "date") Date date) {
		if(tag == null || date == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
		return this.service.getByTagForDate(tag, date);
	}

	/**
	 * Retrieve articles by tag only
	 * @param tag
	 * @param date
	 * @return
	 */
	@GetMapping("/tag/{tagName}") 
	public List<Article> articlesByTagOnly(@PathVariable(value = "tagName") String tag) {
		if(tag == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
		return this.service.getByTag(tag);
	}
}
