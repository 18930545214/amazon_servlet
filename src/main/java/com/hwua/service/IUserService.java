package com.hwua.service;

import com.hwua.pojo.User;

import java.util.List;

public interface IUserService {
    public User login(User user)throws Exception;//登录
    public boolean register(User user)throws Exception;//注册
    public List<User> getAllUsers() throws Exception; //查询所有的用户
    public User queryUserBySendId(Long sendId)throws Exception; //查询发送者用户信息
    public boolean queryUserByName(String name)throws Exception;//判断用户名是否已经注册
    public boolean queryUserByEmail(String email)throws Exception;//判断邮箱是否存在retrieve
    public User retrievePwd(String userName,String idCard,String email)throws Exception;//找回密码
}
