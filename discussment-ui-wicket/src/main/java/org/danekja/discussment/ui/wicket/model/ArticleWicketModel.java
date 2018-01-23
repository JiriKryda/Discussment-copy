package org.danekja.discussment.ui.wicket.model;

import org.apache.wicket.model.LoadableDetachableModel;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.service.ArticleService;

import java.util.List;

public class ArticleWicketModel extends LoadableDetachableModel<List<Article>> {

    private ArticleService articleService;

    public ArticleWicketModel(ArticleService articleService){ this.articleService = articleService; }

    @Override
    protected List<Article> load(){

        return articleService.getArticles();
    }
}
