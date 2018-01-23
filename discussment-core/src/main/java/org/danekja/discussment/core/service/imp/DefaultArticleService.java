package org.danekja.discussment.core.service.imp;

import org.danekja.discussment.core.dao.ArticleDao;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.service.ArticleService;

import java.util.List;

public class DefaultArticleService implements ArticleService {
    private ArticleDao articleDao;

    public DefaultArticleService(ArticleDao articleDao){ this.articleDao = articleDao; }

    public Article createArticle(Article entity) {
        return articleDao.save(entity);
    }

    public Article getArticleById(long articleId){
        return articleDao.getById(articleId);
    }

    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    public void removeArticle(Article entity){
        articleDao.remove(entity);
    }
}
