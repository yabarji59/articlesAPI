package com.articles.articles.api.repository;

import com.articles.articles.api.models.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepositoryH2 extends JpaRepository<Article, Long> {}
