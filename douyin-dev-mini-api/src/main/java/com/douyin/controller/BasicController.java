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


    /**
     * 存储给定的文件流
     * @param finalFilePath
     * @param inputStream
     */
    public static void saveFile(String finalFilePath, InputStream inputStream){
        try{
        File outFile = new File(finalFilePath);
        //确保文件的父层级是个目录
        if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
            outFile.getParentFile().mkdirs();
        }
        FileOutputStream fileOutput = new FileOutputStream(outFile);
        IOUtils.copy(inputStream, fileOutput);
       } catch (FileNotFoundException e) {
            //todo logger
            e.printStackTrace();
        } catch (IOException e) {
            //todo logger
            e.printStackTrace();
        }
    }
}
