package com.douyin.controller;

import com.douyin.pojo.User;
import com.douyin.serivce.UserSerivece;
import com.douyin.utils.IDouyinJSONResult;
import com.douyin.utils.MD5Utils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

/**
 * @author manchaoyang
 */
@RestController
@Api(value = "用户注册登录的接口", tags = {"注册和登陆的Controller"})
public class RegistLoginController {

    @Autowired
    private UserSerivece userSerivece;

    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
    @PostMapping("/regist")
    public IDouyinJSONResult regist(@RequestBody User user) throws Exception {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return IDouyinJSONResult.errorMsg("用户名和密码不能为空");
        }

        boolean userExist = userSerivece.queryUsernameIsExist(user.getUsername());

        if (!userExist) {
            user.setNickname(user.getUsername());
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setFansCounts(0);
            user.setFollowCounts(0);
            userSerivece.saveUser(user);
        } else {
            return IDouyinJSONResult.errorMsg("用户名已经存在，请重试");
        }
        return null;
    }
}
