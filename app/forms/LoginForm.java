package forms;

import models.User;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lubuntu on 8/20/16.
 */
public class LoginForm {
    @Constraints.Required
    public String email;
    @Constraints.Required
    public String password;
    public List<ValidationError> validate() {
        List<ValidationError> errors = new ArrayList<>();
        User user = User.find.where().eq("email",email).findUnique();
        if(user !=null){
            errors.add(new ValidationError("message","Email already exits"));
            errors.add(new ValidationError("email","Error"));
        }
        return errors;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
