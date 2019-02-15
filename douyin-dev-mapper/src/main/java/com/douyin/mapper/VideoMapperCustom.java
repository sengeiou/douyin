package com.douyin.mapper;

import com.douyin.pojo.Video;
import com.douyin.utils.MyMapper;
import com.douyin.vo.VideoVO;

import java.util.List;

public interface VideoMapperCustom extends MyMapper<Video> {

    public List<VideoVO> queryAllVideos();
}