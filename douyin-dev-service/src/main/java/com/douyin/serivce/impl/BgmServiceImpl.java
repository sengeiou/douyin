package com.douyin.serivce.impl;

import com.douyin.mapper.BGMMapper;
import com.douyin.pojo.BGM;
import com.douyin.pojo.BGMExample;
import com.douyin.serivce.BgmService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    private BGMMapper bgmMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<BGM> queryBgmList() {
        BGMExample example = new BGMExample();
        example.createCriteria().andIdIsNotNull();
        List<BGM> allBgm = bgmMapper.selectByExample(example);
        return allBgm;
    }
}
