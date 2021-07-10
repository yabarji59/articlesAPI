package com.articles.articles.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import com.articles.articles.api.models.Article;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticlesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticlesApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private int port = 8080;
	
	public String getUrl() {
		return "http://localhost:"+port + "/";
	}
	@BeforeEach
	public void setUp() {

	}

	@Test
	void testGetByTag() {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(this.getUrl() + "/tag/science",
		HttpMethod.GET, entity, String.class);

		assertTrue(response.getBody().getClass() == String.class);
	}

	@Test
	void testCreate() {
		final String title = "Random";

		Article article = new Article();
		article.setBody("Random test article");
		article.setTitle(title);
		article.setDate(new Date());
		article.setTags("random,test");

		ResponseEntity<Article> responseEntity = restTemplate.postForEntity(this.getUrl() + "/articles", article, Article.class);
		assertNotNull(responseEntity.getBody());
		assertEquals(title, responseEntity.getBody().getTitle());
	}

	@Test
	void testGet() {
		ResponseEntity<Article> responseEntity = restTemplate.getForEntity(this.getUrl() + "articles/1", Article.class);
		Article art = responseEntity.getBody();
		assertNotNull(art);
		assertEquals(1L, responseEntity.getBody().getId());
	}
}
