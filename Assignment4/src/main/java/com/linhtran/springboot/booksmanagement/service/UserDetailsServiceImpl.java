package com.linhtran.springboot.booksmanagement.service;


import com.linhtran.springboot.booksmanagement.model.Role;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.RoleRepository;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String s) {
        User user = userRepository.findByEmail(s);
        UserBuilder userBuilder;

        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(s);
            userBuilder.password(user.getPassword());
            Role role = roleRepository.findById(user.getRoleId()).orElse(null);
            if (role != null) {
                userBuilder.authorities("ROLE_" + role.getName());
            } else {
                userBuilder.authorities("ROLE_ANONYMOUS");

            }
        } else {
            throw new UsernameNotFoundException("This user not found");
        }
        return userBuilder.build();
    }
}
