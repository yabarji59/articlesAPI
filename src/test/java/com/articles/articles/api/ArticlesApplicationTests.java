package com.articles.articles.api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArticlesApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ArticlesApplicationTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	public String getUrl() {
		return "http://localhost:"+port + "/";
	}
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void getArticles() {
		
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		
		ResponseEntity<String> response = restTemplate.exchange(this.getUrl() + "/users",
		HttpMethod.GET, entity, String.class);
		
		Assert.notNull(response.getBody());
	}
}
