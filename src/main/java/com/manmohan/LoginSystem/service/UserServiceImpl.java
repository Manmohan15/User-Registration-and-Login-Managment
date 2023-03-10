package com.manmohan.LoginSystem.service;

import com.manmohan.LoginSystem.dao.UserDao;
import com.manmohan.LoginSystem.dto.UserRegistrationDTO;
import com.manmohan.LoginSystem.entity.Role;
import com.manmohan.LoginSystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements  UserService{

    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User save(UserRegistrationDTO userRegistrationDTO) {
        User user=new User(userRegistrationDTO.getFirstName(), userRegistrationDTO.getLastName(),userRegistrationDTO.getEmail(), passwordEncoder.encode(userRegistrationDTO.getPassword()), Arrays.asList(new Role("ROLE USER")) );
        return userDao.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userDao.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("Invalid username or password");
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),mapRolesToAuthorities(user.getRoles()));
    }
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
