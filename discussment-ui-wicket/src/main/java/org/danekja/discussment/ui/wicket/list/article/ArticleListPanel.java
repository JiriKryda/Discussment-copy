package org.danekja.discussment.ui.wicket.list.article;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.domain.Category;
import org.danekja.discussment.core.domain.User;
import org.danekja.discussment.core.service.ArticleService;
import org.danekja.discussment.ui.wicket.model.ArticleWicketModel;

import java.util.List;

public class ArticleListPanel extends Panel {

    private ArticleService articleService;
    private IModel<List<Article>> articleListModel;

    public ArticleListPanel (String id, IModel<List<Article>> articleListModel, ArticleService articleService){
        super(id);

        this.articleListModel = articleListModel;
        this.articleService = articleService;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ListView<Article>("articleList", articleListModel) {
            protected void populateItem(final ListItem<Article> listItem){

                listItem.add(createOpenArticleLink(listItem.getModel()));
                listItem.add(createRemoveArticleLink(listItem.getModel()));
            }
        });
    }
    private Link createOpenArticleLink(final IModel<Article> am) {
        return new Link("openArticle") {
            @Override
            public void onClick() {
                PageParameters pageParameters = new PageParameters();
                pageParameters.add("articleId", am.getObject().getId());

                setResponsePage(getWebPage().getClass(), pageParameters);
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();

                setBody(Model.of(am.getObject().getName()));
            }
        };
    }

    private Link createRemoveArticleLink(final IModel<Article> am) {
        return new Link("remove") {
            @Override
            public void onClick() {
                articleService.removeArticle(am.getObject());
                setResponsePage(getWebPage().getClass());
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();

                User user = (User) getSession().getAttribute("user");
                this.setVisible(user != null && user.getPermissions().isRemoveCategory());
            }
        };
    }

}
