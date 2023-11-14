package com.example.application;

import com.example.application.model.Reservierung;
import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import com.example.application.service.ReservierungService;
import com.example.application.service.UserService;
import com.vaadin.flow.spring.security.AuthenticationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private AuthenticationContext authenticationContext;

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private ReservierungService reservierungService;

    @Test
    public void whenUpdateUser_thenUserIsUpdated() {
        // Arrange
        User user = new User();
        user.setUsername("Test");
        String pw = "test";
        String hasehed = BCrypt.hashpw(pw, BCrypt.gensalt());
        user.setPassword(hasehed);
        when(repository.save(user)).thenReturn(user);
        // Act
        User result = userService.update(user);
        // Assert
        assertThat(result, is(user));
        System.out.println("Username: "+result.getUsername()+" PW: "+result.getPassword());
    }

    @Test
    public void whenDeleteUser_thenUserIsDeleted() {
        User standart = new User();
        standart.setUsername("basic");
        String pw = "password";
        String hasehed = BCrypt.hashpw(pw, BCrypt.gensalt());
        standart.setPassword(hasehed);
        standart.setEmail("Email");
        standart.setId(2l);
        standart.setRole(Role.ADMIN);
        Long id = 2l;
        // Act
        userService.delete(id);
        // Assert
        verify(repository).deleteById(id);
    }



    @Test
    public void whenSaveUser_thenUserIsSaved() {
        // Arrange
        User user = new User();
        user.setUsername("Ewan");
        user.setEmail("Email");
        user.setRole(Role.USER);
        String pw = "test";
        String hasehed = BCrypt.hashpw(pw, BCrypt.gensalt());
        user.setPassword(hasehed);
        when(repository.save(user)).thenReturn(user);
        // Act
        User result = userService.save(user);
        // Assert
        assertThat(result, is(user));
    }

    @Test
    public void testIfReservationCorrect() {

        LocalDateTime start = LocalDate.of(2023,11,7).atStartOfDay();
        LocalDateTime end = LocalDate.of(2023,12,8).atStartOfDay();
        LocalDateTime start2 = LocalDate.of(2020,12,12).atStartOfDay();
        LocalDateTime end2 = LocalDate.of(2021,12,6).atStartOfDay();

        Reservierung resv1 = new Reservierung("Raum 1", start, end);
        Reservierung resv2 = new Reservierung("Raum 1", start2,end2);

        System.out.println("Startzeit: " + resv1.getStartZeit());
        System.out.println("Endzeit: " + resv1.getEndZeit());

        assertTrue(reservierungService.verifyReservierung(resv1,resv2));
    }
}