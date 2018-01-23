package org.danekja.discussment.ui.wicket.form.article;

import org.danekja.discussment.core.domain.Article;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;


public class ArticleFormComponent extends Panel {
    public ArticleFormComponent (String id, IModel<Article> articleModel) { super (id, articleModel);}
    @Override
    protected void onInitialize() {
        super.onInitialize();

        TextField<String> name = new TextField<String>("name", new PropertyModel<String>(getDefaultModel(), "name"));
        name.setRequired(true);
        add(name);

        TextArea<String> articleText = new TextArea<String>("articleText", new PropertyModel<String>(getDefaultModel(), "articleText"));
        articleText.setRequired(true);
        add(articleText);
    }
}
