package org.danekja.discussment.ui.wicket.form;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.model.IModel;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.service.ArticleService;
import org.danekja.discussment.ui.wicket.form.article.ArticleFormComponent;

public class ArticleForm extends Form {

    private ArticleService articleService;

    private IModel<Article> articleModel;

    public ArticleForm(String id, IModel<Article> articleModel) { this (id, null, articleModel);}

    public ArticleForm(String id, ArticleService articleService, IModel<Article> articleModel){
        super(id);

        this.articleService = articleService;
        this.articleModel = articleModel;
    }

    @Override
    protected void onInitialize(){
        super.onInitialize();

        add(new ArticleFormComponent("articleFormComponent", articleModel));
    }

    public void setArticleService(ArticleService articleService) { this.articleService = articleService; }

    @Override
    protected void onSubmit(){
        if(articleService != null){
            articleService.createArticle(articleModel.getObject());

            articleModel.setObject(new Article());
        }
    }
}
