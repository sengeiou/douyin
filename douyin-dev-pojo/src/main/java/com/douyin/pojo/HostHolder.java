package com.douyin.pojo;

import com.douyin.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public class HostHolder {
    private static ThreadLocal<UserVO> users = new ThreadLocal<>();
    public UserVO get(){
        return users.get();
    }

    public void set(UserVO userVO){
        users.set(userVO);
    }

    public void clear(){
        users.remove();
    }
}
