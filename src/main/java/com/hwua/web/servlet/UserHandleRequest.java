package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.User;
import com.hwua.service.IUserService;
import com.hwua.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

public class UserHandleRequest {
    Map<String, Object> map = new HashMap<>();
    private IUserService userService = new UserServiceImpl();

    /**
     * 验证码验证
     * @param req
     * @param resp
     * @throws Exception
     */
    public void checkCode(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Object validateCode = req.getSession().getAttribute("validateCode");//随机验证码
        String code = req.getParameter("veryCode");//获取输入的验证码
        out.println("验证码功能");
        if (code.equals(validateCode)) {
            map.put("info", "验证码正确");
        } else {
            map.put("info", "验证码错误");
        }
        toJSONString(resp);
    }

    /**
     * 邮箱验证
     * @param req
     * @param resp
     * @throws Exception
     */
    public void CheckEmail(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String email = req.getParameter("email");//获取输入邮箱
        System.out.println("邮箱"+email);
        boolean flag = userService.queryUserByEmail(email);
        if (flag){
            map.put("info", "success");
        }else {
            map.put("info", "邮箱已被注册");
        }
        toJSONString(resp);
    }

    /**
     * 用户名验证
     * @param req
     * @param resp
     * @throws Exception
     */
    public void CheckUserName(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String name = req.getParameter("userName");//获取输入的用户名
        System.out.println("用户名"+name);
        boolean flag = userService.queryUserByName(name);
        if (flag) {
            map.put("info", "success");
        } else {
            map.put("info", "用户名已存在");
        }
        toJSONString(resp);
    }

    /**
     * 登录
     * @param loginUser
     * @param req
     * @param resp
     * @throws Exception
     */
    public void login(User loginUser, HttpServletRequest req, HttpServletResponse resp)throws Exception {
        out.println("进入登录功能");
        User login = userService.login(loginUser);
        if (login != null) {//成功
            req.getSession().setAttribute("user", login);//把user放到request域中
            //跳转到用户信息界面
            map.put("info", "登录成功");
        } else {//失败
            map.put("info", "用户名或密码错误!");
            out.println("失败");
        }
        toJSONString(resp);

    }

    /**
     * 注册
     * @param registerUser
     * @param req
     * @param resp
     * @throws Exception
     */
    public void register(User registerUser, HttpServletRequest req, HttpServletResponse resp)throws Exception {
        System.out.println("注册对象"+registerUser);
        if (userService.register(registerUser)){
            map.put("info", "注册成功");
        }else {
            map.put("info", "注册失败!");
        }
        toJSONString(resp);
    }


    /**
     * 返回JSON字符串到客户端
     * @param resp
     * @throws Exception
     */
    public void toJSONString(HttpServletResponse resp)throws Exception{
        PrintWriter out = resp.getWriter();
        String jsonStr = JSON.toJSONString(map);
        System.out.println(jsonStr);
        out.write(jsonStr);
    }

    /**
     * 退出
     * @param req
     * @param resp
     * @throws Exception
     */
    public void logout(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        req.getSession().invalidate();//销毁session
        resp.sendRedirect(req.getContextPath() + "/index.jsp");//回到登录页面
    }

    /**
     * 找回密码
     * @param req
     * @param resp
     * @throws Exception
     */
    public void retrievePwd( HttpServletRequest req, HttpServletResponse resp) throws Exception{
        String userName = req.getParameter("userName");
        String idCard = req.getParameter("idCard");
        String email = req.getParameter("email");
        System.out.println(userName+idCard+email);
        System.out.println("找回密码");
        User user1 = userService.retrievePwd(userName,idCard,email);
        if (user1!=null){
            req.setAttribute("pwd", user1.getPwd());//把user放到request域中
            map.put("info", "找回成功");
        }else {
            map.put("info", "找回失败");
        }
        toJSONString(resp);
    }
}
