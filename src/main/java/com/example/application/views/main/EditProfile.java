package com.example.application.views.main;

import com.example.application.model.User;
import com.example.application.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.crypto.bcrypt.BCrypt;

@Route(value = "editprofile", layout = MainLayout.class)
@RouteAlias("editprofile")
@RolesAllowed("ROLE_ADMIN")
public class EditProfile extends VerticalLayout {
    private final UserService userService;

    public EditProfile(UserService userService) {
        this.userService = userService;
        content();
    }

    public void content() {
        new H2("Edit Profile");
        Text username = new Text("Username: "+ userService.getCurrentLoggedInUser().getUsername());
        User user = userService.getCurrentLoggedInUser();
        Text emailOld = new Text("Old Email: "+ user.getEmail());
      TextField email = new TextField("Email new: ");
        Button safe = new Button("Safe");


        safe.addClickListener(e -> {

                user.setEmail(email.getValue());
                userService.update(user);

        });
        setSizeFull();

        add(new VerticalLayout(email, safe));
    }
}
