package com.example.application.service;

import java.util.Optional;

import com.example.application.model.Role;
import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import com.vaadin.flow.spring.security.AuthenticationContext;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final AuthenticationContext authenticationContext;
    private final UserRepository repository;

    public UserService(AuthenticationContext authenticationContext, UserRepository repository) {
        this.authenticationContext = authenticationContext;
        this.repository = repository;
    }

    @Lock(LockModeType.PESSIMISTIC_READ)
    public Optional<User> get(Long id) {
        return repository.findById(id);
    }
    @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    public User update(User entity) {
        User user = repository.save(entity);
        return user;
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Page<User> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

//    public Page<User> list(Pageable pageable, Specification<User> filter) {
//        return repository.findAll(filter, pageable);
//    }

    public int count() {
        return (int) repository.count();
    }

    public User save(User user) {
    	return repository.save(user);
    }

    public User getCurrentLoggedInUser() {
       Authentication authenticatedUser = SecurityContextHolder.getContext().getAuthentication();
    	return repository.findByUsername(authenticatedUser.getName());
    }

    public Iterable<User> findAll() {
    	return repository.findAll();
    }

}
