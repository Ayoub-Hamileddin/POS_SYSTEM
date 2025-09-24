package com.eyuup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyuup.modal.User;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
