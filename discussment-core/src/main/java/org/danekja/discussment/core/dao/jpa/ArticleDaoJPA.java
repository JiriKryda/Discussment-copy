package org.danekja.discussment.core.dao.jpa;

import org.danekja.discussment.core.dao.ArticleDao;
import org.danekja.discussment.core.domain.Article;

import javax.persistence.TypedQuery;
import java.util.List;

public class ArticleDaoJPA extends GenericDaoJPA<Article> implements ArticleDao{
    public ArticleDaoJPA() {
        super(Article.class);
    }

    public List<Article> getArticles() {
        TypedQuery<Article> q = em.createNamedQuery(Article.GET_ARTICLES, Article.class);
        return q.getResultList();
    }
}
