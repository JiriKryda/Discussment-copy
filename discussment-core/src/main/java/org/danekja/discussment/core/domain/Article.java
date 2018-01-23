package org.danekja.discussment.core.domain;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;
import java.util.Objects;

import static org.danekja.discussment.core.domain.Article.GET_ARTICLES;

@Entity
@NamedQueries({
        @NamedQuery(name = GET_ARTICLES,
                query = "SELECT a FROM Article a WHERE id != 0")
})
public class Article extends BaseEntity implements Serializable{
    public static final String GET_ARTICLES = "FileEntity.getArticles";

    private String name;

    private String articleText;

    public Article() {}

    public Article (String name, String articleText) {
        this.name = name;
        this.articleText = articleText;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getArticleText() { return articleText; }

    public void setArticleText(String articleText) { this.articleText = articleText; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Article article = (Article) o;
        return Objects.equals(name, article.name) &&
                Objects.equals(articleText, article.articleText);
    }

    @Override
    public int hashCode() {

        return Objects.hash(super.hashCode(), name, articleText);
    }
}
