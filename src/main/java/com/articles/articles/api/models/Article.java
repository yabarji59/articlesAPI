package com.articles.articles.api.models;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class for the article in the following format
 * {
  "id": "1",
  "title": "latest science shows that potato chips are better for you than sugar",
  "date" : "2016-09-22",
  "body" : "some text, potentially containing simple markup about how potato chips are great",
  "tags" : ["health", "fitness", "science"]
}
 * @author bsoni
 *
 */
@NoArgsConstructor
@Getter 
@Setter
public class Article {
	
	private Long id;
	private Date date;
	private String title;
	private String body;
	private String[] tags;
	
	/**
	 * relying on the Java bean pattern
	 * a default empty constructor
	 */
	public Article(Long id, Date date, String body, String[] tags) {
		this.id = id;
		this.date = date;
		this.body = body;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
