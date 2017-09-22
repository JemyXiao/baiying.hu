package com.baiying.hu.mapper;


import com.baiying.hu.entity.User;
import com.baiying.hu.entity.UserExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() throws Exception {
        User user = new User();
//        user.setPhone(18721956624L);
//        user.setCityId(1);
//        user.setNickName("测试1");
//        user.setPassword("password");
//        user.setStatus("EFFECT");
//        user.setUsername("測試name");
//        userMapper.insert(user);
//        System.out.println(userMapper.selectByPrimaryKey(user.getId()));
//        user.setPhone(18765423981L);
//        userMapper.insert(user);
//        System.out.println(userMapper.selectByPrimaryKey(user.getId()));

        UserExample example = new UserExample();
        example.createCriteria().andIdEqualTo(2L);
        System.out.println(userMapper.selectByExample(example));
//		UserMapper.insert(new UserEntity("aa", "a123456", UserSexEnum.MAN));
//		UserMapper.insert(new UserEntity("bb", "b123456", UserSexEnum.WOMAN));
//		UserMapper.insert(new UserEntity("cc", "b123456", UserSexEnum.WOMAN));
//		Assert.assertEquals(3, UserMapper.getAll().size());
    }

}