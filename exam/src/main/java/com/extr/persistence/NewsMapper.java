package com.extr.persistence;

import java.util.List;

import com.extr.domain.news.News;
import com.extr.util.Page;

public interface NewsMapper {

	public List<News> getNewsList(Page<News> page);
	
	public News getNewsById(int newsId);
	
	public void addNews(News news);
}
