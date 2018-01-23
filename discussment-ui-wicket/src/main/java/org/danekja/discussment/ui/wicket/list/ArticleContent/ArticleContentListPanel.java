package org.danekja.discussment.ui.wicket.list.ArticleContent;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.domain.User;
import org.danekja.discussment.core.service.ArticleService;
import org.danekja.discussment.ui.wicket.list.article.ArticleListPanel;


import java.util.List;

public class ArticleContentListPanel extends Panel{

    private ArticleService articleService;
    private IModel<Article> articleModel;
    private IModel<List<Article>> articleListModel;

    public ArticleContentListPanel (String id, IModel<List<Article>> articleListModel, ArticleService articleService, IModel<Article> articleModel){
        super(id);

        this.articleListModel = articleListModel;
        this.articleService = articleService;
        this.articleModel = articleModel;
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(createArticleAjaxLink());

        add(new ArticleListPanel("articlePanel", articleListModel, articleService));
    }

    private AjaxLink createArticleAjaxLink() {
        return new AjaxLink("createArticle") {
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {}

            @Override
            protected void onConfigure() {
                super.onConfigure();

                User user = (User) getSession().getAttribute("user");
                this.setVisible(user != null && user.getPermissions().isCreateCategory());
            }
        };
    }
}
