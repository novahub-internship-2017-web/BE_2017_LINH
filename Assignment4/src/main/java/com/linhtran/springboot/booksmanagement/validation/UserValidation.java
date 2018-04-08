package com.linhtran.springboot.booksmanagement.validation;

import com.fasterxml.jackson.annotation.JsonView;
import com.linhtran.springboot.booksmanagement.model.User;
import com.linhtran.springboot.booksmanagement.repository.UserRepository;
import com.linhtran.springboot.booksmanagement.view.Views;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;

public class UserValidation {

    @Autowired
    UserRepository userRepository;

    private User user;

    @JsonView(Views.Public.class)
    private boolean validUser;

    @JsonView(Views.Public.class)
    private boolean validEmail;

    @JsonView(Views.Public.class)
    private boolean availableEmail;

    @JsonView(Views.Public.class)
    private boolean confirmPasswordMatchPassword;

    public UserValidation(User user) {
        if (user != null) {
            this.validEmail = EmailValidator.getInstance().isValid(user.getEmail());
            this.confirmPasswordMatchPassword = user.getPassword().equals(user.getConfirmPassword());
            this.availableEmail = userRepository.findByEmail(user.getEmail()) != null;
        } else {
            this.validEmail = false;
            this.confirmPasswordMatchPassword =false;
            this.availableEmail = false;
        }
        this.validUser = validEmail && availableEmail && confirmPasswordMatchPassword;

    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isValidUser() {
        return validUser;
    }

    public void setValidUser(boolean validUser) {
        this.validUser = validUser;
    }

    public boolean isValidEmail() {
        return validEmail;
    }

    public void setValidEmail(boolean validEmail) {
        this.validEmail = validEmail;
    }

    public boolean isAvailableEmail() {
        return availableEmail;
    }

    public void setAvailableEmail(boolean availableEmail) {
        this.availableEmail = availableEmail;
    }

    public boolean isConfirmPasswordMatchPassword() {
        return confirmPasswordMatchPassword;
    }

    public void setConfirmPasswordMatchPassword(boolean confirmPasswordMatchPassword) {
        this.confirmPasswordMatchPassword = confirmPasswordMatchPassword;
    }
}
