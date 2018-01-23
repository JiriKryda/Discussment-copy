package org.danekja.discussment.example.page.article;

import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.danekja.discussment.core.dao.jpa.DiscussionDaoJPA;
import org.danekja.discussment.core.dao.jpa.PostDaoJPA;
import org.danekja.discussment.core.dao.jpa.ArticleDaoJPA;
import org.danekja.discussment.core.domain.Article;
import org.danekja.discussment.core.domain.Discussion;
import org.danekja.discussment.core.domain.Post;
import org.danekja.discussment.core.service.DiscussionService;
import org.danekja.discussment.core.service.PostService;
import org.danekja.discussment.core.service.ArticleService;
import org.danekja.discussment.core.service.imp.DefaultArticleService;
import org.danekja.discussment.core.service.imp.DefaultDiscussionService;
import org.danekja.discussment.core.service.imp.DefaultPostService;
import org.danekja.discussment.example.page.base.BasePage;
import org.danekja.discussment.ui.wicket.panel.article.ArticlePanel;
import org.danekja.discussment.ui.wicket.panel.discussion.DiscussionPanel;

import java.util.HashMap;


/**
 * Homepage
 */
public class ArticlePage extends BasePage {

	private static final long serialVersionUID = 1L;

	private static final long DISCUSSION_ID = 0;

	private ArticleService articleService;
	private DiscussionService discussionService;
	private PostService postService;

    private IModel<HashMap<String, Integer>> parametersModel;

    final PageParameters parameters;

    /**
	 * Constructor that is invoked when page is invoked without a session.
	 * 
	 * @param parameters
	 *            Page parameters
	 */
    public ArticlePage(final PageParameters parameters) {
        this.parameters = parameters;

        this.articleService = new DefaultArticleService(new ArticleDaoJPA());
        this.discussionService = new DefaultDiscussionService(new DiscussionDaoJPA());
        this.postService = new DefaultPostService(new PostDaoJPA());

        parametersModel = new Model<HashMap<String, Integer>>();
        parametersModel.setObject(new HashMap<String, Integer>());
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();
        parametersModel.getObject().put("articleId", parameters.get("articleId").isNull() ? -1 : Integer.parseInt(parameters.get("articleId").toString()));
        add(new ArticlePanel("content", parametersModel, articleService, discussionService, postService));
        /*Discussion discussion = discussionService.getDiscussionById(DISCUSSION_ID);

        if (discussion == null) {
            discussion = discussionService.createDiscussion(new Discussion("article name", (null)));
        }

        add(new DiscussionPanel("content", new Model<Discussion>(discussion), postService, new Model<Post>()));*/
    }

    @Override
    public String getTitle() {
        return "Article page";
    }
}
