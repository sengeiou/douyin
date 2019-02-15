package com.douyin.serivce.impl;

import com.douyin.mapper.VideoMapper;
import com.douyin.mapper.VideoMapperCustom;
import com.douyin.pojo.Video;
import com.douyin.pojo.VideoExample;
import com.douyin.serivce.VideoService;
import com.douyin.utils.PagedResult;
import com.douyin.vo.VideoVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    private VideoMapperCustom videoMapperCustom;

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

    @Override
    public PagedResult queryVideosByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<VideoVO> videoVOList = videoMapperCustom.queryAllVideos();
        PageInfo<VideoVO> pageInfo = new PageInfo<>(videoVOList,pageSize);
        PagedResult pagedResult = new PagedResult();
        pagedResult.setPage(page);
        pagedResult.setTotal(pageInfo.getPages());
        pagedResult.setRows(videoVOList);
        pagedResult.setRecords(pageInfo.getTotal());
        return pagedResult;
    }

}
