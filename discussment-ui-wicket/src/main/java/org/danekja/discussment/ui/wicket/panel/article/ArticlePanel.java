package org.danekja.discussment.ui.wicket.panel.article;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.domain.Discussion;
import org.danekja.discussment.core.domain.Post;
import org.danekja.discussment.core.service.ArticleService;
import org.danekja.discussment.core.service.DiscussionService;
import org.danekja.discussment.core.service.PostService;
import org.danekja.discussment.ui.wicket.form.ArticleForm;
import org.danekja.discussment.ui.wicket.list.ArticleContent.ArticleContentListPanel;
import org.danekja.discussment.ui.wicket.model.ArticleWicketModel;
import org.danekja.discussment.ui.wicket.panel.discussion.DiscussionPanel;


import java.util.HashMap;

public class ArticlePanel extends Panel{

    private IModel<HashMap<String, Integer>> parametersModel;

    private static final long DISCUSSION_ID = 0;

    private ArticleService articleService;
    private DiscussionService discussionService;
    private PostService postService;

    private IModel<Article> articleModel;
    private IModel<Discussion> discussionModel;
    private IModel<Post> postModel;

    public ArticlePanel (String id, IModel<HashMap<String, Integer>> parametersModel, ArticleService articleService, DiscussionService discussionService, PostService postService){
        super(id);

        this.parametersModel = parametersModel;

        this.articleService = articleService;
        this.discussionService = discussionService;
        this.postService = postService;

        this.articleModel = new Model<Article>();
        this.discussionModel = new Model<Discussion>();
        this.postModel = new Model<Post>();

    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ArticleForm("articleForm", articleService, new Model<Article>(new Article())));

        if (parametersModel.getObject().get("articleId") == -1){
            add(new ArticleContentListPanel("content",
                    new ArticleWicketModel(articleService), articleService, articleModel)
            );
        } else {
            Article article = articleService.getArticleById(parametersModel.getObject().get("articleId"));
            articleModel.setObject(article);

            Discussion discussion = discussionService.getDiscussionById(DISCUSSION_ID);

            if (discussion == null) {
                discussion = discussionService.createDiscussion(new Discussion("article name", (null)));
            }

            add(new DiscussionPanel("content", new Model<Discussion>(discussion), postService, new Model<Post>()));
        }
    }
}

