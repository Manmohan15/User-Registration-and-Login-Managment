package com.manmohan.LoginSystem.service;

import com.manmohan.LoginSystem.dto.UserRegistrationDTO;
import com.manmohan.LoginSystem.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDTO userRegistrationDTO);
}
