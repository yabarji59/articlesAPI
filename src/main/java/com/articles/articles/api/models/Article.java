package com.articles.articles.api.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@Entity
@Table( name = "articles")
@EntityListeners(AuditingEntityListener.class)
public class Article {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name ="PDate", nullable = false)
	private Date date;

	@Column(name ="title", nullable = false)
	private String title;

	@Column(name ="body", nullable = false)
	private String body;

	@Column(name ="tags", nullable = false)
	private String tags;
	
	public Article() {}
	/**
	 * relying on the Java bean pattern
	 * a default empty constructor
	 */
	public Article(Long id, Date date, String title, String body, String tags) {
		this.id = id;
		this.date = date;
		this.body = body;
		this.tags = tags;
		this.title = title;
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

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
