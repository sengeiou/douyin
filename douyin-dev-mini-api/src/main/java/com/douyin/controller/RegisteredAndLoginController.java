package com.douyin.controller;

import com.douyin.pojo.HostHolder;
import com.douyin.pojo.User;
import com.douyin.serivce.UserSerivece;
import com.douyin.utils.IDouyinJSONResult;
import com.douyin.utils.KeyUtils;
import com.douyin.utils.MD5Utils;
import com.douyin.utils.RedisOperator;
import com.douyin.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.omg.PortableInterceptor.HOLDING;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.util.StringUtils;

import java.util.UUID;

/**
 * @author manchaoyang
 */
@RestController
@Api(value = "用户注册登录的接口", tags = {"注册和登陆的Controller"})
public class RegisteredAndLoginController extends BasicController{

    @Autowired
    private KeyUtils keyUtils;

    @Autowired
    private UserSerivece userSerivece;

    @Autowired
    private HostHolder hostHolder;

    @ApiOperation(value = "用户注册", notes = "用户注册的接口")
    @PostMapping("/registered")
    public IDouyinJSONResult registered(@RequestBody User user) throws Exception {
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
        user.setPassword(null);
        return IDouyinJSONResult.ok(user);
    }

    @ApiOperation(value = "用户登录", notes = "用户登录的接口")
    @PostMapping(value = "/login")
    public IDouyinJSONResult login(@RequestBody User user) throws Exception {
        if (StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
            return IDouyinJSONResult.errorMsg("用户名和密码不能为空");
        }
        User existUser = userSerivece.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));

        if (existUser == null) {
            return IDouyinJSONResult.errorMsg("用户名或密码不正确");
        }
       UserVO userVO = setUserRedisSessionToken(existUser);
        return IDouyinJSONResult.ok(userVO);
    }


    public UserVO setUserRedisSessionToken(User user){
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        String token = UUID.randomUUID().toString();
        userVO.setUserToken(token);
        //设置redis中的session过期时间
        redisOperator.set(getSessionKey(userVO.getId()),
                token,1000*60*30);
        hostHolder.set(userVO);
        return userVO;
    }

    @ApiOperation(value = "用户注销", notes = "用户注销的接口")
    @PostMapping(value = "/logout")
    @ApiImplicitParam(name = "userId",value = "用户ID",required = true,
                        dataType = "String",paramType = "query")
    public IDouyinJSONResult logout(String userId) throws Exception {
        redisOperator.del(getSessionKey(userId));
        return IDouyinJSONResult.ok();
    }
}
