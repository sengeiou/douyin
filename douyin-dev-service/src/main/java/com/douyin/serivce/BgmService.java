package com.douyin.serivce;

import com.douyin.pojo.BGM;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface BgmService {

    public List<BGM> queryBgmList();
}
