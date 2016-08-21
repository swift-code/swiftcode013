package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import forms.LoginForm;
import forms.SignupForm;
import models.Profile;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Controller;

import javax.inject.Inject;
import javax.security.auth.login.LoginContext;

/**
 * Created by lubuntu on 8/21/16.
 */
public class Application extends Controller {
    @Inject
    FormFactory FormFactory;
    @Inject
    ObjectMapper objectMapper;
    public Result signup() {
        Form<SignupForm> form = FormFactory.form(SignupForm.class).bindFromRequest();
        if (form.hasErrors())
            form.data().get("firstName");
        Profile profile = new Profile(form.data().get("firstName"),
                form.data().get("lastName"));
        Profile.db().save(profile);

        User user = new User(form.data().get("email"),
                form.data().get("password"));
        user.profile = profile;
        User.db().save(user);
        return ok((JsonNode) objectMapper.valueToTree(user));
    }

    public Result login() {
        Form<LoginForm> form = FormFactory.form(LoginForm.class).bindFromRequest();
        if (form.hasErrors()){
        return ok(form.errorsAsJson());
        }
        return ok();
    }

}
