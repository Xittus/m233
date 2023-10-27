package com.example.application.service;

import java.util.Optional;

import com.example.application.model.User;
import com.example.application.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> get(Long id) {
        return repository.findById(id);
    }

    public User update(User entity) {
        return repository.save(entity);
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

}