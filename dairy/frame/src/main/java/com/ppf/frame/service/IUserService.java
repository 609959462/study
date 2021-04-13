package com.ppf.frame.service;

import com.ppf.frame.pojo.User;

public interface IUserService {
    /**
     *用户登录
     *
     * @param user
     * @return
     */
    public User UserLogin(User user);

    public User SelectByUsername(String username);
}
