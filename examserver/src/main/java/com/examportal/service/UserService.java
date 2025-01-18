package com.examportal.service;

import java.util.Set;

import com.examportal.model.User;
import com.examportal.model.UserRole;

public interface UserService {

    public User createUser(User user, Set<UserRole> userRoles) throws Exception;

    public User getUser(String username);

    public void deleteUser(Long id);
}