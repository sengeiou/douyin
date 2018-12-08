package com.douyin.serivce.impl;

import com.douyin.mapper.UserMapper;
import com.douyin.pojo.User;
import com.douyin.pojo.UserExample;
import com.douyin.serivce.UserSerivece;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserSerivece {

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String name) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameEqualTo(name);
        List<User> users = userMapper.selectByExample(userExample);
        if(CollectionUtils.isEmpty(users)){
            return false;
        }
        return true;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void saveUser(User user) {
            String userId = sid.nextShort();
            user.setId(userId);
            userMapper.insert(user);
    }
}
