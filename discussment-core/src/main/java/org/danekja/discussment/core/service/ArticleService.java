package org.danekja.discussment.core.service;

import org.danekja.discussment.core.domain.Article;

import java.util.List;

public interface ArticleService {
    Article createArticle(Article entity);
    Article getArticleById(long articleId);
    List<Article> getArticles();
    void removeArticle(Article entity);
}
