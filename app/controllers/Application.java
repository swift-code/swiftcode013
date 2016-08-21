package controllers;

import forms.SignupForm;
import models.Profile;
import models.User;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Result;
import play.mvc.Controller;

import javax.inject.Inject;

/**
 * Created by lubuntu on 8/21/16.
 */
public class Application extends Controller {
    @Inject
    FormFactory FormFactory;
    public Result signup() {
        Form<SignupForm> form = FormFactory.form(SignupForm.class).bindFromRequest();
        if (form.hasErrors())
            form.data().get("firstName");
        Profile profile = new Profile(form.data().get("firstName"),
                form.data().get("lastname"));
        Profile.db().save(profile);

        User user = new User(form.data().get("email"),
                form.data().get("password"));
        user.profile = profile;
        User.db().save(user);
        return ok(form.errorsAsJson());
    }
}
