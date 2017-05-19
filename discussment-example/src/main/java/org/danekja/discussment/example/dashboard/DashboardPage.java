package org.danekja.discussment.example.dashboard;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.danekja.discussment.core.dao.jpa.PermissionJPA;
import org.danekja.discussment.core.dao.jpa.UserJPA;
import org.danekja.discussment.core.domain.User;
import org.danekja.discussment.core.service.IUserService;
import org.danekja.discussment.core.service.imp.UserService;
import org.danekja.discussment.example.base.BasePage;

/**
 * Created by Martin Bláha on 21.01.17.
 */
public class DashboardPage extends BasePage {

    public DashboardPage() {

        IUserService userService = new UserService(new UserJPA(), new PermissionJPA());

        add(new ListView<User>("usersListView", userService.getUsers()) {

            public void populateItem(final ListItem<User> item) {

                final User user = item.getModelObject();

                item.add(new Label("username", user.getUsername()));
                item.add(new Label("name", user.getName()));
                item.add(new Label("lastname", user.getLastname()));

                item.add(new Label("cc", user.getPermissions().isCreateCategory()));
                item.add(new Label("rc", user.getPermissions().isRemoveCategory()));

                item.add(new Label("ct", user.getPermissions().isCreateTopic()));
                item.add(new Label("rt", user.getPermissions().isRemoveTopic()));

                item.add(new Label("cd", user.getPermissions().isCreateCategory()));
                item.add(new Label("rd", user.getPermissions().isRemoveCategory()));

                item.add(new Label("cp", user.getPermissions().isCreatePost()));
                item.add(new Label("rp", user.getPermissions().isRemovePost()));
                item.add(new Label("dp", user.getPermissions().isDisablePost()));

                item.add(new Label("rpd", user.getPermissions().isReadPrivateDiscussion()));

            }
        });


    }

    @Override
    public String getTitle() {
        return "Dashboard page";
    }
}