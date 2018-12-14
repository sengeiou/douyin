package com.douyin.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author manchaoyang
 */
@Component
public class KeyUtils {
    /**
     * 用redis来做session 设置过期时间 查缓存 横向扩展性好 redis来保证集群的token可用性
     * @param userId
     * @return
     */
    public String createUserTokenKey(String userId,String UUID) {
        return UUID + ":" + userId;
    }
}
