package org.danekja.discussment.example.page.base;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.danekja.discussment.core.accesscontrol.dao.PermissionDao;
import org.danekja.discussment.core.accesscontrol.dao.jpa.PermissionDaoJPA;
import org.danekja.discussment.core.accesscontrol.domain.Permission;
import org.danekja.discussment.core.accesscontrol.service.impl.DefaultPermissionService;
import org.danekja.discussment.example.core.DefaultUserService;
import org.danekja.discussment.example.core.User;
import org.danekja.discussment.example.core.UserDaoMock;
import org.danekja.discussment.example.core.UserService;
import org.danekja.discussment.example.form.LoginForm;
import org.danekja.discussment.example.form.RegistrationForm;
import org.danekja.discussment.example.page.article.ArticlePage;
import org.danekja.discussment.example.page.dashboard.DashboardPage;
import org.danekja.discussment.example.page.discussion.DiscussionPage;


/**
 * Created by Martin Bláha on 21.01.17.
 */
public abstract class BasePage extends WebPage {

    public BasePage() {

        setMenu();

        add(createLogoutLink());
        add(createLoginAjaxLink());
        add(createRegistrationAjaxLink());
        add(createUsernameLabel());
        add(createUsernameAlert());

        add(new Label("title", new Model<String>(getTitle())));

        PermissionDao permissionDao = new PermissionDaoJPA();
        UserService userService = new DefaultUserService(new UserDaoMock(), permissionDao);

        add(new LoginForm("loginForm", userService, new Model<User>(new User())));
        add(new RegistrationForm("registrationForm",
                new DefaultUserService(new UserDaoMock(), permissionDao),
                new Model<User>(new User()),
                new DefaultPermissionService(permissionDao, userService), Model.of(new Permission())));
    }

    private Label createUsernameLabel() {
        IModel<String> model = new Model() {
            @Override
            public String getObject() {
                User user = (User) getSession().getAttribute("user");

                if (user == null) {
                    return "";
                } else {
                    return user.getDisplayName();
                }
            }
        };

        return new Label("username", model);
    }

    private Link createLogoutLink() {
        return new Link("logout") {
            @Override
            public void onClick() {
                getSession().removeAttribute("user");
            }

            @Override
            protected void onConfigure() {
                super.onConfigure();

                User user = (User) getSession().getAttribute("user");
                this.setVisible(user != null);
            }
        };
    }

    private AjaxLink createLoginAjaxLink() {
        return new AjaxLink("login"){
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {}

            @Override
            protected void onConfigure() {
                super.onConfigure();

                User user = (User) getSession().getAttribute("user");
                this.setVisible(user == null);
            }
        };
    }

    private AjaxLink createRegistrationAjaxLink() {
        return new AjaxLink("registration"){
            @Override
            public void onClick(AjaxRequestTarget ajaxRequestTarget) {}

            @Override
            protected void onConfigure() {
                super.onConfigure();

                User user = (User) getSession().getAttribute("user");
                this.setVisible(user == null);
            }
        };
    }

    private WebMarkupContainer createUsernameAlert() {
         return new WebMarkupContainer("alertUsername") {
            @Override
            protected void onConfigure() {
                super.onConfigure();

                if (getSession().getAttribute("error") != null && getSession().getAttribute("error").equals("username")) {
                    setVisible(true);
                    getSession().setAttribute("error", null);
                } else {
                    setVisible(false);
                }
            }
        };
    }

    private void setMenu() {
        Link dashboardPagePageLink = new Link("dashboardPage") {
            @Override
            public void onClick() {
                setResponsePage(DashboardPage.class);
            }
        };
        add(dashboardPagePageLink);

        Link articlePageLink = new Link("articlePage") {
            @Override
            public void onClick() {
                setResponsePage(ArticlePage.class);
            }
        };
        add(articlePageLink);

        Link discussionPageLink = new Link("discussionPage") {
            @Override
            public void onClick() {
                setResponsePage(DiscussionPage.class);
            }
        };
        add(discussionPageLink);
    }

    public abstract String getTitle();
}
