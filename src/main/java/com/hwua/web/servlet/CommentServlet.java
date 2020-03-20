package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.Comment;
import com.hwua.pojo.PageEntity;
import com.hwua.service.ICommentService;
import com.hwua.service.impl.CommentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/comment.do")
public class CommentServlet extends HttpServlet {
    Map<String, Object> map = new HashMap<>();
    private ICommentService commentService = new CommentServiceImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//解码，防止中文乱码问题
        String param = req.getParameter("param");
        resp.setContentType("application/json;charset=utf-8");

        //提交留言
        if (param.equals("comment")){
            System.out.println("留言");
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json;charset=utf-8");
            String name = req.getParameter("guestName");
            String title = req.getParameter("guestTitle");
            String content = req.getParameter("guestContent");
            String create_Time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            Comment comment = new Comment(title,content,create_Time,create_Time,name,"0");
            try {
                int save = commentService.save(comment);
                if (save>0){
                    map.put("info","success");
                }else {
                    map.put("info","提交失败");
                }
            } catch (Exception e) {
                map.put("info","联系管理员");
                e.printStackTrace();
            }
            String jsonStr = JSON.toJSONString(map);
            System.out.println(jsonStr);
            out.write(jsonStr);


            //分页查询留言
        }else if (param.equals("queryAll")){
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));//当前页
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));//每页显示的记录数
            PrintWriter out = resp.getWriter();
            try {
                PageEntity pageEntity = new PageEntity();
                List<Comment> comments = commentService.queryAll((pageNo-1)*pageSize, pageSize);
                pageEntity.setList(comments);
                pageEntity.setPageNo(pageNo);
                pageEntity.setPageSize(pageSize);
                pageEntity.setTotalRecords(commentService.queryCount());
                String jsonStr = JSON.toJSONString(pageEntity);
                System.out.println(jsonStr);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
