package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.Category;
import com.hwua.pojo.News;
import com.hwua.service.ICategoryService;
import com.hwua.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/Category.do")
public class CategoryServlet extends HttpServlet {
    private ICategoryService categoryService = new CategoryServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//解码，防止中文乱码问题
        String param = req.getParameter("param");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //新闻
        if (param.equals("queryAll")){
            try {
                List<Category> categories = categoryService.queryAll();
                String jsonStr = JSON.toJSONString(categories);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
