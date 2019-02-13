package com.douyin.serivce;

import com.douyin.pojo.Video;

public interface VideoService {
    /**
     * 保存视频
     * @param video
     */
    public String saveVideo(Video video);

}
