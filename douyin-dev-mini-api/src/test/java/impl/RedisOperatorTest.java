package impl;

import com.Application;
import com.douyin.utils.RedisOperator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RedisOperatorTest {
    @Autowired
    private RedisOperator redisOperator;

    /**
     * 检查redis的可用性，阿里云上的redis后台运行的时候总挂 有空去查查 研究一下
     */
    @Test
    public void setTest(){
        redisOperator.set("hello","word");
        System.out.println(redisOperator.get("hello"));
    }
}
