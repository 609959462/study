package com.ppf.frame.service.impl;

import com.ppf.frame.dao.UserMapper;
import com.ppf.frame.pojo.User;
import com.ppf.frame.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserServiceImpl implements IUserService {

@Autowired
    UserMapper userMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public User UserLogin(User user) {
      // User userInfo =  userMapper.selectByUserAndPassword(user.getUserName(),user.getUserPassword());
        return null;
    }

    @Override
    public User SelectByUsername(String username) {
        User userInfo = userMapper.selectByUser(username);
        return userInfo;
    }

}
