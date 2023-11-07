package com.example.application.views.main;


import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
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
import org.hibernate.annotations.Check;
import org.springframework.security.crypto.bcrypt.BCrypt;


import static com.vaadin.flow.component.notification.Notification.show;

@Route(value = "register")
@RouteAlias("register")
@AnonymousAllowed
public class RegisterView extends VerticalLayout {

    private final UserService userService;

    private final Checkbox isAdmin;


    public RegisterView(final UserService userService) {
        this.userService = userService;
        new H2("Register");
        TextField username = new TextField("Username");
        PasswordField password1 = new PasswordField("Password");
        PasswordField password2 = new PasswordField("Password again");
        TextField email = new TextField("Email");
        Button register = new Button("Register");
        isAdmin = new Checkbox("Admin");

        register.addClickListener(e -> {
            if (password1.getValue().equals(password2.getValue())) {
                String hasehed = BCrypt.hashpw(password1.getValue(), BCrypt.gensalt());
                userRegister(username.getValue(), hasehed);
                Notification.show("User registered");
            } else {
             Notification.show("Passwords do not match");
            }


        });
        add(new VerticalLayout(username, password1, password2,email, isAdmin, register));
        setSizeFull();
        setAlignItems(Alignment.CENTER);

    }

    public void userRegister(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        if (isAdmin.getValue()) {
            user.setRole(Role.ADMIN);
        }
        else {
            user.setRole(Role.USER);
        }
        userService.save(user);

    }
}