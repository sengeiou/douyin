package com.douyin.serivce;

import com.douyin.pojo.Video;

public interface VideoService {
    /**
     * 保存视频
     * @param video
     */
    public String saveVideo(Video video);


    /**
     * 更新Video的封面
     * @param videoId
     * @param coverPath
     * @return
     */
    public String updateVideoCover(String videoId,String coverPath);

}
