package impl;
import com.Application;
import com.douyin.pojo.User;
import com.douyin.serivce.UserSerivece;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {
    @Resource
    private UserSerivece userSerivece;

    @Test
    public void loadUserByNameTest(){
        User user = userSerivece.queryUserForLogin("imooc","1212312");
        int i = 0;
    }
}
