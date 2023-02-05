package com.manmohan.LoginSystem.dao;

import com.manmohan.LoginSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
