package com.example.application.views.main;

import com.example.application.model.User;
import com.example.application.service.UserService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.crypto.bcrypt.BCrypt;


@Route(value = "", layout = MainLayout.class)
@RouteAlias("")
@PermitAll

public class ProfileView extends VerticalLayout {
    private final UserService userService;

    public ProfileView(UserService userService) {
        this.userService = userService;
        content();
    }

    public void content() {
        new H2("Profile");
        User user = userService.getCurrentLoggedInUser();
        Text username = new Text("Username: " + user.getUsername());
        Text email = new Text("Deine Email: "+user.getEmail());
        setSizeFull();
        setAlignItems(Alignment.CENTER);

        add(new VerticalLayout(username, email));
    }
}
