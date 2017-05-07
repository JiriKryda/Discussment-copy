package cz.zcu.fav.kiv.discussion.gui.form;

import cz.zcu.fav.kiv.discussion.core.model.DiscussionModel;
import cz.zcu.fav.kiv.discussion.core.model.UserModel;
import cz.zcu.fav.kiv.discussion.core.service.DiscussionService;
import cz.zcu.fav.kiv.discussion.core.service.UserService;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * Created by Martin Bláha on 25.01.17.
 */
public class PasswordForm extends Form {

    private String password;

    private long discussionId;

    public PasswordForm(String id) {
        super(id);

        setDefaultModel(new CompoundPropertyModel(this));

        add(new TextField("password"));
    }

    public long getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(long discussionId) {
        this.discussionId = discussionId;
    }

    @Override
    protected void onSubmit() {

        DiscussionModel dis = DiscussionService.getDiscussionById(discussionId);

        PageParameters pageParameters = new PageParameters();

        if (dis.getPass().equals(password)) {

            UserModel user = (UserModel) getSession().getAttribute("user");

            if (user != null) {
                UserService.addAccessToDiscussion(user.getId(), discussionId);
            }

            pageParameters.add("discussionId", discussionId);
        } else {
            pageParameters.add("error", "denied");
        }

        setResponsePage(getPage().getClass(), pageParameters);

    }
}