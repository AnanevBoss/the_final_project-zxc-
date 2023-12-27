package com.example.springmodels.repos;

import com.example.springmodels.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByLogin(String login);
}
