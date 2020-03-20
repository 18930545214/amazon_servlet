package com.hwua;

import com.hwua.pojo.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;
import org.junit.Test;

import java.util.List;

public class IUserServiceTest {
    private IUserService usersService = new UserServiceImpl();
    @Test
    public void login()throws Exception {//登录
        User user = new User("admin","1234");
        User login = usersService.login(user);
        System.out.println(login);
    };
    @Test
    public void register()throws Exception {//注册
        User user = new User("马涛","1234","男","2012","622624","123@qq.com","1","1",1);
        boolean register = usersService.register(user);
        System.out.println(register);
    };
    @Test
    public void getAllUsers() throws Exception {//查询所有的用户
        List<User> allUsers = usersService.getAllUsers();
        for (User user :allUsers){
            System.out.println(user);
        }
    };
    @Test
    public void queryUserBySendId()throws Exception {//查询发送者用户信息
        User user = usersService.queryUserBySendId(1L);
        System.out.println(user);
    };
    @Test
    public void queryUserByName()throws Exception {//判断用户名是否已经注册
        String s = "1-2-3-4";
        String[] split = s.split("-");
        for (int i = 1;i<split.length;i++){
            System.out.println(split[i]);
        }
    };
}
