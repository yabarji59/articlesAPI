package com.articles.articles.api;

import java.util.List;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;

import jdk.jshell.spi.ExecutionControl.NotImplementedException;

import com.articles.articles.api.models.Article;
import com.articles.articles.api.repository.ArticleRepositoryH2;
import com.articles.articles.api.repository.ArticleSearch;

@RestController()
public class ArticleControllerH2 {
    
    @Autowired
	private ArticleRepositoryH2 articleRepo;

    @GetMapping("/h2/all")
    public List<Article> getAll() {
        return this.articleRepo.findAll();
    }

    /**
     * @param article
     * @return
     */
    @PostMapping("/h2/articles")
    public Article create(@Validated @RequestBody Article article) {
		if(article == null) {
			System.out.println("Found an error");
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
        return this.articleRepo.save(article);
	}
    /**
     * @param id
     * @return
     */
    @GetMapping("/h2/articles/{id}")
	public Article getArticle(@PathVariable(value = "id") Long id) {

        if(id == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
        Article a = this.articleRepo.getById(id);
		return a;
	}

    /**
     * @param tag
     * @param date
     * @return
     */
    @GetMapping("/h2/tag/{tagName}/{date}") 
	public List<Article> articlesByTagFor(@PathVariable(value = "date") Date date, @PathVariable(value = "tagName") String tag) throws NotImplementedException {
		if(tag == null || date == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
        List<Article> articles = this.getAll();
        return ArticleSearch.search.getByTagFor(articles, date, tag);
    }

    /**
     * @param tag
     * @return
     */
    @GetMapping("/h2/tag/{tagName}") 
    public List<Article> getByTag(@PathVariable(value = "tagName") String tag) {
        if(tag == null) {
			throw new HttpServerErrorException(HttpStatus.BAD_REQUEST);
		}
        List<Article> articles = this.getAll();
        return ArticleSearch.search.getByTag(articles, tag);
    }
}
