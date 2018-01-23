package org.danekja.discussment.core.dao;

import org.danekja.discussment.core.domain.Article;

import java.util.List;

public interface ArticleDao extends GenericDao<Article> {
    List<Article> getArticles();
}
