package com.douyin.controller;

import com.douyin.PathManager;
import com.douyin.utils.RedisOperator;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.*;

@Controller
public class BasicController {


    @Autowired
    protected RedisOperator redisOperator;

    public static final String USER_REDIS_SESSION = "user-redis-session";

    public static String getSessionKey(String userId) {
        return USER_REDIS_SESSION + ":" + userId;
    }



}
