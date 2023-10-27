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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private AuthenticationContext authenticationContext;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGet() {
        // Arrange
        Long id = 1L;
        User user = new User();
        when(repository.findById(id)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.get(id);

        // Assert
        assertEquals(Optional.of(user), result);
    }

    @Test
    public void testUpdate() {
        // Arrange
        User user = new User();
        when(repository.save(user)).thenReturn(user);

        // Act
        User result = userService.update(user);

        // Assert
        assertEquals(user, result);
    }

    @Test
    public void testDelete() {
        // Arrange
        Long id = 1L;

        // Act
        userService.delete(id);

        // Assert
        verify(repository, times(1)).deleteById(id);
    }

    @Test
    public void testList() {
        // Arrange
        Pageable pageable = mock(Pageable.class);
        Page<User> page = mock(Page.class);
        when(repository.findAll(pageable)).thenReturn(page);

        // Act
        Page<User> result = userService.list(pageable);

        // Assert
        assertEquals(page, result);
    }

    @Test
    public void testCount() {
        // Arrange
        long count = 10L;
        when(repository.count()).thenReturn(count);

        // Act
        int result = userService.count();

        // Assert
        assertEquals((int) count, result);
    }

    @Test
    public void testSave() {
        // Arrange
        User user = new User();
        when(repository.save(user)).thenReturn(user);

        // Act
        User result = userService.save(user);

        // Assert
        assertEquals(user, result);
    }


}