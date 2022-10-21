package com.handicraftsnepal.shecrafts.services;

import com.handicraftsnepal.shecrafts.entities.User;

public interface UserServices {
    public void signup(User user);
    public boolean authenticate(String userName,String password) throws Exception;
}
