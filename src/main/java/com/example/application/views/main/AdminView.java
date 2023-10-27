package com.example.application.views.main;

import com.example.application.model.User;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@Route(value = "AdminView", layout = MainLayout.class)
@RouteAlias("AdminView")
@RolesAllowed("ROLE_ADMIN")
public class AdminView extends VerticalLayout {
    public AdminView(final UserService userService) {
        Grid<User> grid = new Grid<>(User.class, false);
        Editor<User> editor = grid.getEditor();
        Grid.Column<User> usernameCloumn = grid.addColumn(User::getUsername)
                .setHeader("Username").setFlexGrow(0);
        Grid.Column<User> email = grid.addColumn(User::getEmail)
                .setHeader("E-Mail").setFlexGrow(0);
        Grid.Column<User> isAdmin = grid.addColumn(User::getRole)
                .setHeader("Role").setFlexGrow(0);

        Grid.Column<User> editColumn = grid.addComponentColumn(person -> {
            Button editButton = new Button("Edit");
            editButton.addClickListener(e -> {
                if (editor.isOpen())
                    editor.cancel();
                grid.getEditor().editItem(person);
            });
            return editButton;
        }).setWidth("150px").setFlexGrow(0);

        Binder<User> binder = new Binder<>(User.class);
        editor.setBinder(binder);
        editor.setBuffered(true);
        TextField usernameField = new TextField();
        usernameField.setWidthFull();
        binder.forField(usernameField).asRequired("Username must not be empty")
                .bind(User::getUsername, User::setUsername);
        usernameCloumn.setEditorComponent(usernameField);

        TextField emailField = new TextField();
        emailField.setWidthFull();
        binder.forField(emailField).asRequired("E-Mail must not be empty")
                .bind(User::getEmail, User::setEmail);
        email.setEditorComponent(emailField);

        Button saveButton = new Button("Save", e -> editor.save());
        Button cancelButton = new Button(VaadinIcon.CLOSE.create(),
                e -> editor.cancel());

        HorizontalLayout actions = new HorizontalLayout(saveButton,
                cancelButton);

        actions.setPadding(false);
        editColumn.setEditorComponent(actions);

        List<User> users = new ArrayList<>();
        Iterable<User> dbUsers = userService.findAll();

        dbUsers.forEach(users::add);

        grid.setItems(users);

        add(grid);


    }



}