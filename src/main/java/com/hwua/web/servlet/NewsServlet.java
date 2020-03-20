package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.Comment;
import com.hwua.pojo.News;
import com.hwua.service.INewService;
import com.hwua.service.impl.NewsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/news.do")
public class NewsServlet extends HttpServlet {
    private INewService newService = new NewsServiceImpl();
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

        //查询新闻
        if (param.equals("queryAll")) {
            try {
                List<News> news = newService.queryAll();
                String jsonStr = JSON.toJSONString(news);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //新闻详情
        }else if (param.equals("queryById")){
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                News news = newService.queryAllById(id);
                req.setAttribute("news",news);
                req.getRequestDispatcher("/news_view.jsp").forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
