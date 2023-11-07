package com.example.application;

import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import com.example.application.service.UserService;
import com.vaadin.flow.spring.security.AuthenticationContext;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Optional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private AuthenticationContext authenticationContext;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenGetUser_thenReturnsUser() {
        // Arrange
        Long id = 1L;
        User user = new User();
        when(repository.findById(id)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.get(id);

        // Assert
        assertThat(result, is(Optional.of(user)));
    }

    @Test
    public void whenUpdateUser_thenUserIsUpdated() {
        // Arrange
        User user = new User();
        when(repository.save(user)).thenReturn(user);

        // Act
        User result = userService.update(user);

        // Assert
        assertThat(result, is(user));
    }

    @Test
    public void whenDeleteUser_thenUserIsDeleted() {
        // Arrange
        Long id = 1L;

        // Act
        userService.delete(id);

        // Assert
        verify(repository).deleteById(id);
    }

    @Test
    public void whenListUsers_thenReturnsPageOfUsers() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<User> page = mock(Page.class);
        when(repository.findAll(pageable)).thenReturn(page);

        // Act
        Page<User> result = userService.list(pageable);

        // Assert
        assertThat(result, is(page));
    }

    @Test
    public void whenCountUsers_thenReturnsCount() {
        // Arrange
        long count = 10L;
        when(repository.count()).thenReturn(count);

        // Act
        long result = userService.count();

        // Assert
        assertThat(result, is(count));
    }

    @Test
    public void whenSaveUser_thenUserIsSaved() {
        // Arrange
        User user = new User();
        when(repository.save(user)).thenReturn(user);

        // Act
        User result = userService.save(user);

        // Assert
        assertThat(result, is(user));
    }


}