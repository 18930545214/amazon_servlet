package com.hwua.dao;

import com.hwua.pojo.User;

import java.util.List;

public interface IUserDao {
    public int save(User user) throws Exception;//增加
    public List<User> queryAll() throws Exception;//查询全部
    public User queryByNameAndPwd(String name, String pwd) throws Exception;//按用户名密码查询
    public User queryById(Long sendId) throws Exception;//按ID查询
    public User queryByName(String name) throws Exception;//按姓名查询
    public User queryUserByEmail(String email)throws Exception;//判断邮箱是否存在
    public User retrievePwd(String userName,String idCard,String email)throws Exception;//找回密码
}
