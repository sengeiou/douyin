package com.douyin.serivce.impl;

import com.douyin.mapper.VideoMapper;
import com.douyin.pojo.Video;
import com.douyin.pojo.VideoExample;
import com.douyin.serivce.VideoService;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private Sid sid;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public String saveVideo(Video video) {
        String id = sid.nextShort();
        video.setId(id);
        videoMapper.insertSelective(video);
        return id;
    }

    @Override
    public String updateVideoCover(String videoId, String coverPath) {
        Video video = new Video();
        video.setCoverPath(coverPath);
        VideoExample example = new VideoExample();
        example.createCriteria().andIdEqualTo(videoId);
        videoMapper.updateByExampleSelective(video, example);
        return videoId;
    }

}
