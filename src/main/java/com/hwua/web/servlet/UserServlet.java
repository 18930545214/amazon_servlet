package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    private UserHandleRequest userHR = new UserHandleRequest();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//解码，防止中文乱码问题
        String param = req.getParameter("param");
        resp.setContentType("application/json;charset=utf-8");
        String name = req.getParameter("userName");//获取输入的用户名
        String pwd = req.getParameter("passWord");//获取输入的密码
        String code = req.getParameter("veryCode");//获取输入的验证码
        String sex = req.getParameter("sex");//获取输入性别
        String birthday = req.getParameter("birthday");//获取输入的生日
        String identity = req.getParameter("identity");//获取输入的省份正
        String email = req.getParameter("email");//获取输入邮箱
        String mobile = req.getParameter("mobile");//获取输入的电话
        String address = req.getParameter("address");//获取输入的地址
        User loginUser = new User(name, pwd);//登录对象
        User registerUser = new User(name,pwd,sex,birthday,identity,email,mobile,address,1);//注册对象
        System.out.println("name:"+name+"=====pwd:"+pwd+"=====code:"+code);
        System.out.println("测试"+param);
        try{
            switch (param){
                case "checkCode":
                    userHR.checkCode(req,resp);
                    break;
                case "CheckEmail":
                    userHR.CheckEmail(req,resp);
                    break;
                case "CheckUserName":
                    userHR.CheckUserName(req,resp);
                    break;
                case "login":
                    userHR.login(loginUser,req,resp);
                    break;
                case "register":
                    userHR.register(registerUser,req,resp);
                    break;
                case "logout":
                    userHR.logout(req,resp);
                    break;
                case "retrievePwd":
                    userHR.retrievePwd(req,resp);
                    break;
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
