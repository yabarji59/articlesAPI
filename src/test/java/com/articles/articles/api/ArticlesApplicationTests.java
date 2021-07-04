package com.articles.articles.api;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.articles.articles.api.models.Article;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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
	void contextLoads() {
	}
	
	@Test
	void getArticleByTag() {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(this.getUrl() + "/politics/Mon, 20 Jun 2021",
		HttpMethod.GET, entity, String.class);
		
		assertFalse(response.getStatusCode() == HttpStatus.OK);
	}
}
