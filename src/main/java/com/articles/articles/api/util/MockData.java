package com.articles.articles.api.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.articles.articles.api.models.Article;

/**
 * {
  "id": "1",
  "title": "latest science shows that potato chips are better for you than sugar",
  "date" : "2016-09-22",
  "body" : "some text, potentially containing simple markup about how potato chips are great",
  "tags" : ["health", "fitness", "science"]
   * Wesfarmers acquires Priceline - for some 687 million
 * Murray Abhrams - Academy award winner plays author in new series
 * Lockdown pay - more payments coming for workers affected by lockdown

}
 */
public class MockData {
    
    public static List<Article> mockArticles() {
        List<Article> mockArticles = new ArrayList<Article>();
        
        Article a1 = new Article(1L, new Date(), "Wesfarmers acquires Priceline","Wesfarmers makes $687 million takeover offer for Priceline, pharmacy group", 
        new String ("health,fitness,science"));

        Article a2 = new Article(2L, new Date(),"F. Murray Abhrams", "Academy award winner plays author in new series for Apple TV, Mythic Quest", 
        new String("politics,national,monarchy"));

        Article a3 = new Article(3L, new Date(),"Lockdown releif","Government payments coming for workers affected by lockdown", 
        new String ("health,fitness,science"));

        mockArticles.add(a1);
        mockArticles.add(a2);
        mockArticles.add(a3);

        return mockArticles;
        
    }
}
