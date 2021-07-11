package com.articles.articles.api.repository;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import com.articles.articles.api.models.Article;

interface CustomSearch <T> {

    List<T> getByTag(List<T> source, String tag);

    List<T> getByTagFor(List<T> source, Date on, String tag);

}

public class ArticleSearch implements CustomSearch<Article> {

    public static ArticleSearch search = new ArticleSearch();

    @Override
    public List<Article> getByTag(List<Article> source, String tag) {
        
        List<Article> searchResult = new ArrayList<Article>();
		searchResult = source.stream().filter(t -> Arrays.asList(t.getTags().split(","))
                                    .contains(tag)).collect(Collectors.toList());
        return searchResult;
    }

    @Override
	public List<Article> getByTagFor(List<Article> source, Date on, String tag) {
        List<Article> searchResult = new ArrayList<Article>();
		LocalDate aTargetDate = this.toLocalDate(on);
		for(Article article: source) {
			LocalDate aDate = this.toLocalDate(article.getDate());
			//if it's on the same day
			if(aDate.isEqual(aTargetDate)) {
				//then search through the tags
				if (Arrays.asList(article.getTags().split(",")).stream()
								.anyMatch(t -> tag.equalsIgnoreCase(t))) {
					searchResult.add(article);
				}
			}
		}
		return searchResult;
    }

    /**
	 * Get the date instant and return local date for the 
	 * zone
	 * @param date
	 * @return
	*/
	private LocalDate toLocalDate(Date date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		return instant.atZone(defaultZoneId).toLocalDate();
	}
}
