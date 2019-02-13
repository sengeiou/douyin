package com.douyin.controller;

import com.douyin.PathManager;
import com.douyin.mapper.BGMMapper;
import com.douyin.pojo.BGM;
import com.douyin.pojo.Video;
import com.douyin.serivce.BgmService;
import com.douyin.utils.IDouyinJSONResult;
import com.douyin.utils.*;
import io.swagger.annotations.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@Api(value = "视频业务相关的接口", tags = {"视频业务相关的Controller"})
@RequestMapping("/video")
public class VideoController extends BasicController {

    @Autowired
    private BgmService bgmService;

    @Autowired
    private MergeVideoMp3Utils mergeVideoMp3Utils;

    @Autowired
    private FileSaveUtils fileSaveUtils;

    @ApiOperation(value = "上传视频", notes = "上传视频的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "bgmId", value = "背景音乐id", required = false,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoSeconds", value = "背景音乐播放长度", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoWidth", value = "视频宽度", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "videoHeight", value = "视频高度", required = true,
                    dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "desc", value = "视频描述", required = false,
                    dataType = "String", paramType = "form")
    })
    @PostMapping(value = "/upload", headers = "content-type=multipart/form-data")
    public IDouyinJSONResult upload(String userId,
                                    String bgmId, double videoSeconds,
                                    int videoWidth, int videoHeight,
                                    String desc,
                                    @ApiParam(value = "短视频", required = true)
                                            MultipartFile file) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return IDouyinJSONResult.errorMsg("用户为空");
        }
        String fileName = null;
        try {
            fileName = file.getOriginalFilename();
            if (StringUtils.isNoneBlank(fileName)) {
                String finalVideoPath = PathManager.getVideoFilePath(userId, fileName);
                fileSaveUtils.saveFile(file.getInputStream(),finalVideoPath);
            }else{
                return IDouyinJSONResult.errorMsg("文件名为空");
            }
            if(StringUtils.isNoneBlank(bgmId)){
                BGM bgm = bgmService.queryBGMById(bgmId);
                String mp3InputPath = PathManager.getMp3Path(bgm.getPath());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return IDouyinJSONResult.errorMsg("上传出错");
        }
        Video video = new Video();
        video.setAudioId(bgmId);
        video.setUserId(userId);
        video.setVideoSeconds((float) videoSeconds);
        video.setVideoDesc(desc);
        video.setVideoHeight(videoHeight);
        video.setVideoWidth(videoWidth);
//        video.setVideoPath();
//        video.setCoverPath();
        return IDouyinJSONResult.ok(PathManager.getFaceFilePathDB(userId, fileName));
    }

}
