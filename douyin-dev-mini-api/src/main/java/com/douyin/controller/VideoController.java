package com.douyin.controller;

import com.douyin.Statis;
import com.douyin.controller.BasicController;
import com.douyin.pojo.User;
import com.douyin.serivce.BgmService;
import com.douyin.utils.IDouyinJSONResult;
import com.douyin.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@RestController
@Api(value = "视频业务相关的接口", tags = {"视频业务相关的Controller"})
@RequestMapping("/video")
public class VideoController extends BasicController {


    @ApiOperation(value = "上传视频", notes = "上传视频的接口")
    @PostMapping("/upload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId" ,value = "用户ID",required = true,
            dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "bgmId" ,value = "背景音乐的ID",required = false,
                    dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "videoSeconds" ,value = "视频秒数",required = true,
                    dataType = "Double",paramType = "query"),
            @ApiImplicitParam(name = "videoWidth" ,value = "视频宽度",required = true,
                    dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "videoHeight" ,value = "视频高度",required = true,
                    dataType = "Integer",paramType = "query"),
            @ApiImplicitParam(name = "desc" ,value = "视频描述",required = false,
                    dataType = "String",paramType = "query"),
    })
    public IDouyinJSONResult upload(String userId,
                                    String bgmId, Double videoSeconds,
                                    Integer videoWidth, Integer videoHeight,
                                    String desc,
                                    @RequestParam("file") MultipartFile files) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return IDouyinJSONResult.errorMsg("用户为空");
        }


        FileOutputStream fileOutput = null;

        InputStream inputStream = null;
        String fileName = null;
        try {
            fileName = files.getOriginalFilename();
            if (StringUtils.isNoneBlank(fileName)) {
                String finalVideoPath = Statis.getVideoFilePath(userId, fileName);
                File outFile = new File(finalVideoPath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                    //create parent folder
                    outFile.getParentFile().mkdirs();
                }
                fileOutput = new FileOutputStream(outFile);
                inputStream = files.getInputStream();
                IOUtils.copy(inputStream, fileOutput);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return IDouyinJSONResult.errorMsg("上传出错");
        } finally {
            if (fileOutput != null) {
                fileOutput.flush();
                fileOutput.close();
            }
        }

        return IDouyinJSONResult.ok(Statis.getFaceFilePathDB(userId, fileName));
    }


}
