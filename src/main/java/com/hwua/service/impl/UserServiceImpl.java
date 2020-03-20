package com.hwua.service.impl;

import com.hwua.dao.IUserDao;
import com.hwua.dao.impl.UserDaoImpl;
import com.hwua.pojo.User;
import com.hwua.service.IUserService;

import java.util.List;

public class UserServiceImpl implements IUserService {
    private IUserDao usersDao = new UserDaoImpl();


    @Override
    public User login(User user) throws Exception {//登录

        return usersDao.queryByNameAndPwd(user.getUName(), user.getPwd());

    }

    @Override
    public boolean register(User user) throws Exception {//注册
        boolean flag =false;
        if (usersDao.save(user) != 0) {
            flag = true;
        }
        return flag;
    }

    @Override
    public List<User> getAllUsers() throws Exception {//查询所有

        return usersDao.queryAll();
    }

    @Override
    public User queryUserBySendId(Long sendId) throws Exception {

        return usersDao.queryById(sendId);

    }

    @Override
    public boolean queryUserByName(String name) throws Exception {
        boolean flag =false;
        if (usersDao.queryByName(name) == null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public boolean queryUserByEmail(String email) throws Exception {
        boolean flag =false;
        if (usersDao.queryUserByEmail(email) == null) {
            flag = true;
        }
        return flag;
    }

    @Override
    public User retrievePwd(String userName,String idCard,String email) throws Exception {
        return usersDao.retrievePwd(userName,idCard,email);

    }
}
