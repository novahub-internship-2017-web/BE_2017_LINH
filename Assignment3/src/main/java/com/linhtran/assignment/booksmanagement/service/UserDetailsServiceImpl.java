package com.linhtran.assignment.booksmanagement.service;

import com.linhtran.assignment.booksmanagement.dao.UserDao;
import com.linhtran.assignment.booksmanagement.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userDao.findUserByEmail(s);
        UserBuilder userBuilder;

        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(s);
            userBuilder.password(user.getPassword());
            userBuilder.authorities("ROLE_"+user.getRole());
        } else {
            throw new UsernameNotFoundException("This user not found");
        }

        return userBuilder.build();
    }
}
