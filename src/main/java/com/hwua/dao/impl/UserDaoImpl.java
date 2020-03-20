package com.hwua.dao.impl;

import com.hwua.dao.IUserDao;
import com.hwua.pojo.User;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public int save(User user) throws Exception {//增加一个用户,用于注册
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into amz_user values(null,?,?,?,?,?,?,?,?,?)";
        return qr.update(sql, user.getUName(), user.getPwd(),user.getSex(),user.getBirthday(),user.getIdCard(),user.getEmail(),user.getMobile(),user.getAddress(),user.getUType());
    }

    @Override
    public List<User> queryAll() throws Exception {//查询所有
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,uName,pwd,sex,birthday,idCard,email,mobile,address,uType from amz_user";
        return qr.query(sql, new BeanListHandler<>(User.class));
    }

    @Override
    public User queryByNameAndPwd(String name, String pwd) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,uName,pwd,sex,birthday,idCard,email,mobile,address,uType from amz_user where uName=? and pwd=?";
        return qr.query(sql, new BeanHandler<>(User.class),name,pwd);
    }

    @Override
    public User queryById(Long sendId) throws Exception {//按ID查询
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,uName,pwd,sex,birthday,idCard,email,mobile,address,uType from amz_user where id=?";
        return qr.query(sql, new BeanHandler<>(User.class), sendId);
    }

    @Override
    public User queryByName(String name) throws Exception {//按姓名查询
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_user where uName=?";
        return qr.query(sql, new BeanHandler<>(User.class), name);
    }

    @Override
    public User queryUserByEmail(String email) throws Exception {//按邮箱查询
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,uName,pwd,sex,birthday,idCard,email,mobile,address,uType from amz_user where email=?";
        return qr.query(sql, new BeanHandler<>(User.class), email);
    }

    @Override
    public User retrievePwd(String userName,String idCard,String email) throws Exception {//找回密码
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,uName,pwd,sex,birthday,idCard,email,mobile,address,uType from amz_user where uName=? and idCard=? and email=?";
        return qr.query(sql, new BeanHandler<>(User.class), userName,idCard,email);
    }
}
