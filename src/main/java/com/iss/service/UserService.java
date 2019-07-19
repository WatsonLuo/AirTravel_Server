package com.iss.service;

import com.iss.dao.UserDao;
import com.iss.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public List<User> getAllUser(){return userDao.getAllUser();}

    public User getTheUser(String login){return userDao.getTheUser(login);}

    public int addUser(User user){return userDao.addUser(user);}

    public int updateUser(User user){return userDao.updateUser(user);}

    public int deleteUser(String email){return userDao.deleteUser(email);}

    public int updateIsOnline(User user){return userDao.updateUser(user);
    }
}
