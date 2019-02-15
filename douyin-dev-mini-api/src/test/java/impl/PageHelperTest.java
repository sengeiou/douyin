package impl;

import com.Application;
import com.douyin.mapper.UserMapper;
import com.douyin.pojo.User;
import com.douyin.pojo.UserExample;
import com.douyin.serivce.UserSerivece;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class PageHelperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void Test() {
        PageHelper.startPage(1,3);
        UserExample userExample = new UserExample();
        userExample.createCriteria().andIdIsNotNull();
        List<User> list = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        int i = 0;
    }
}
