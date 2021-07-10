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
}
 */
public class MockData {
    
    public static List<Article> mockArticles() {
        List<Article> mockArticles = new ArrayList<Article>();
        
        Article a1 = new Article(1L, new Date(),"some text, potentially containing simple markup about how potato chips are great", 
        new String ("health,fitness,science"));
        a1.setTitle("Latest science shows that potato chips are better for you than sugar");

        Article a2 = new Article(2L, new Date(),"The queen is evil, her archaic laws are bad and are destroying us", 
        new String("politics,national,monarchy"));
        a2.setTitle("Queen is killing us");

        Article a3 = new Article(3L, new Date(),"Some sample text about fitness and stuff", 
        new String ("health,fitness,science"));
        a3.setTitle("Potato chips aren't any better for you ");


        mockArticles.add(a1);
        mockArticles.add(a2);
        mockArticles.add(a3);

        return mockArticles;
        
    }
}
