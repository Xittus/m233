package com.example.application.views.main;


import com.example.application.model.User;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import org.aspectj.weaver.ast.Not;

import com.vaadin.flow.component.notification.Notification;
import org.springframework.security.crypto.bcrypt.BCrypt;

import static com.vaadin.flow.component.notification.Notification.show;

@Route("register")
@RouteAlias("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final UserService userService;


    public RegisterView(final UserService userService) {
//        Notification notification = Notification.show("Passwords do not match");
        this.userService = userService;
        new H2("Register");
        TextField username = new TextField("Username");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Password again");
        Button register = new Button("Register");
        register.addClickListener(e -> {
            if (password1.getValue().equals(password2.getValue())) {
                String hasehed = BCrypt.hashpw(password1.getValue(), BCrypt.gensalt());
                userRegister(username.getValue(), hasehed);
                Notification.show("User registered");
            } else {
             Notification.show("Passwords do not match");
            }

        });
        add(new VerticalLayout(username, password1, password2, register));
        setSizeFull();
        setAlignItems(Alignment.CENTER);

    }

    public void userRegister(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        userService.save(user);

    }
}