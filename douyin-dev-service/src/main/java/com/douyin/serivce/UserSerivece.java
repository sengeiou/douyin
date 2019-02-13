package com.douyin.serivce;

import com.douyin.pojo.User;

/**
 * @author manchaoyang
 */
public interface UserSerivece {

    /**
     * 判断用户名是否存在
     * @param name
     * @return
     */
    public boolean queryUsernameIsExist(String name);

    /**
     * 保存User对象
     * @param user
     */
    public void saveUser(User user);

    /**
     * 根据用户名称查找用户
     * @param name
     * @return
     */
    public User queryUserForLogin(String name, String password);

    public void updateUserInfo(User user);

    public User queryUserInfo(String userId);
}
