package com.douyin.controller;

import com.douyin.PathManager;
import com.douyin.pojo.User;
import com.douyin.serivce.UserSerivece;
import com.douyin.utils.IDouyinJSONResult;
import com.douyin.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author manchaoyang
 */
@RestController
@Api(value = "用户相关业务的接口", tags = {"用户相关业务的Controller"})
@RequestMapping("/user")
public class UserController extends BasicController {
    @Autowired
    private UserSerivece userSerivece;

    @ApiOperation(value = "用户头像上传", notes = "用户头像上传的接口")
    @PostMapping(value = "/uploadFace")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
            dataType = "String", paramType = "query")
    public IDouyinJSONResult uploadFace(String userId, @RequestParam("file") MultipartFile[] files) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return IDouyinJSONResult.errorMsg("用户为空");
        }

        if (files == null || files.length == 0) {
            return IDouyinJSONResult.errorMsg("文夹为空");
        }

        FileOutputStream fileOutput = null;

        InputStream inputStream = null;
        String fileName = null;
        try {
            fileName = files[0].getOriginalFilename();
            if (StringUtils.isNoneBlank(fileName)) {
                String finalFacePath = PathManager.getFaceFilePath(userId, fileName);
                File outFile = new File(finalFacePath);
                if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                    //create parent folder
                    outFile.getParentFile().mkdirs();
                }
                fileOutput = new FileOutputStream(outFile);
                inputStream = files[0].getInputStream();
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

        User user = new User();
        user.setId(userId);
        user.setFaceImage(PathManager.getFaceFilePathDB(userId, fileName));
        userSerivece.updateUserInfo(user);
        return IDouyinJSONResult.ok(PathManager.getFaceFilePathDB(userId, fileName));
    }


    @ApiOperation(value = "查询用户信息", notes = "查询用户信息的接口")
    @PostMapping(value = "/query")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true,
            dataType = "String", paramType = "query")
    public IDouyinJSONResult uploadFace(String userId) throws Exception {
        if (StringUtils.isBlank(userId)) {
            return IDouyinJSONResult.errorMsg("用户ID不能为空");
        }
        return IDouyinJSONResult.ok(convertUserVO(userSerivece.queryUserInfo(userId)));
    }

    private UserVO convertUserVO(User user) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }
}
