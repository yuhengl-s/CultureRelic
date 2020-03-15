package com.edu.zju.culture.Mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.zju.culture.mbg.entity.User;
import com.edu.zju.culture.mbg.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Wrapper;
import java.util.List;

/**
 * @author y4oung
 * @date 2020/3/13 2:27 AM
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testWrapper(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq(true,"id",1).or().eq("id",2);
        System.out.println(wrapper.getSqlSegment());
    }


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(1, userList.size());
        userList.forEach(System.out::println);

    }

    @Test
    public void queryForPage(){
//        IPage<User> page = new Page<>(1,5);
//        userMapper.selectPage(page,null);

        //mp-ar模式
//        User user = new User();
//        List<User> res = user.selectAll();
//        System.out.println(res);

//        User user = new User();
//        user.setUsername("aaa");
//        user.insert();
    }

    //mp-ar模式
    @Test
    public void deleteUser(){
//        User user = new User();
//        user.setId(3);
//        user.deleteById();

    }

}