package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.PageEntity;
import com.hwua.pojo.Product;
import com.hwua.service.IProductService;
import com.hwua.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/Product.do")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");//解码，防止中文乱码问题
        resp.setCharacterEncoding("utf-8");
        String param = req.getParameter("param");
        PageEntity pageEntity = new PageEntity();
        //resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        //分页查询所有商品
        if (param.equals("queryAll")){
            System.out.println("查询全部");
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));//当前页
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));//每页显示的记录数
            try {
                List<Product> list = productService.queryAll((pageNo-1)*pageSize, pageSize);
                pageEntity.setList(list);
                pageEntity.setPageNo(pageNo);
                pageEntity.setPageSize(pageSize);
                pageEntity.setTotalRecords(productService.queryCount());
                String jsonStr = JSON.toJSONString(pageEntity);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //按id查询商品,保存cookie
        }else if (param.equals("queryById")){
            String id1 = req.getParameter("id");
            int id2 = Integer.parseInt(id1);
            try {
                //拼接图书的id字符串(逻辑判断).格式4-3-2-1
                String historyId = linkId(id1,req);
                Cookie cookie = new Cookie("historyId",historyId);
                cookie.setMaxAge(60*60*24);
                resp.addCookie(cookie);
                Product product = productService.queryById(id2);
                req.setAttribute("product",product);
                req.getRequestDispatcher("/product_view.jsp").forward(req,resp);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //模糊查询
        }else if (param.equals("queryByName")){
            String name = req.getParameter("name");
            System.out.println(name);
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));//当前页
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));//每页显示的记录数
            try {
                List<Product> products = productService.queryAllByName("%"+name+"%", (pageNo-1)*pageSize, pageSize);
                System.out.println(products);
                pageEntity.setList(products);
                pageEntity.setPageNo(pageNo);
                pageEntity.setPageSize(pageSize);
                pageEntity.setTotalRecords(productService.queryCountByName("%"+name+"%"));
                String jsonStr = JSON.toJSONString(pageEntity);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //最近浏览
        }else if (param.equals("showProductDetail")){
            ArrayList<Product> list = new ArrayList<>();
            //显示浏览过的图书,就是从cookie中取出historyId的字符串4-3-2-1
            Cookie[] cookies = req.getCookies();
            boolean flag =false;//默认没有找到指定的cookie
            if(cookies!=null){
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("historyId")){
                        String historyId = cookie.getValue();  //得到浏览过的字符串
                        System.out.println(historyId);
                        String[] ids = historyId.split("-");
                        for(String s:ids){
                            try {
                                System.out.println(s);
                                Product product = productService.queryById(Integer.parseInt(s));
                                list.add(product);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                }
            }
            String jsonStr = JSON.toJSONString(list);
            System.out.println(jsonStr);
            out.write(jsonStr);

            //热销
        }else if (param.equals("queryAllHot")){
            try {
                List<Product> products = productService.queryAllHot();
                String jsonStr = JSON.toJSONString(products);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //一级目录查询
        }else if (param.equals("queryByMajorId")){
            String id = req.getParameter("id");
            int majorId = Integer.parseInt(id);
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));//当前页
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));//每页显示的记录数
            try {
                List<Product> products = productService.queryByMajorId(majorId,(pageNo-1)*pageSize, pageSize);
                pageEntity.setList(products);
                pageEntity.setPageNo(pageNo);
                pageEntity.setPageSize(pageSize);
                pageEntity.setTotalRecords(productService.queryCountByMajorId(majorId));
                String jsonStr = JSON.toJSONString(pageEntity);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //耳机目录查询
        }else if (param.equals("queryByMinorId")){
            String id = req.getParameter("id");
            int minorId = Integer.parseInt(req.getParameter("id"));
            int pageNo = Integer.parseInt(req.getParameter("pageNo"));//当前页
            int pageSize = Integer.parseInt(req.getParameter("pageSize"));//每页显示的记录数
            try {
                List<Product> products = productService.queryByMinorId(minorId,(pageNo-1)*pageSize, pageSize);
                pageEntity.setList(products);
                pageEntity.setPageNo(pageNo);
                pageEntity.setPageSize(pageSize);
                pageEntity.setTotalRecords(productService.queryCountByMinorId(minorId));
                String jsonStr = JSON.toJSONString(pageEntity);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }



    private String linkId(String id,HttpServletRequest req){
        //1.判断,前面是否浏览过图书.加入浏览过我们预设就应该有名字为historyId的cookie
        Cookie[] cookies = req.getCookies();
        String historyId = null;
        boolean flag = false;//默认没有找到指定的cookie
        if(cookies!=null){
            for(Cookie cookie:cookies){
                if(cookie.getName().equals("historyId")){
                    flag=true;
                    historyId = cookie.getValue();
                    break;
                }
            }
        }
        /**
         * 第一次浏览商品
         */
        if(cookies==null || flag==false){
            historyId = id;
        }
        /**
         * 不是第一次访问,要做逻辑判断后进行拼接成新的id字符串
         */
        LinkedList<String> list = new LinkedList<>(Arrays.asList(historyId.split("-")));
        if(list.size()<4){
            //1.浏览过的  2.没有浏览过的
            if(list.contains(id)){
                list.remove(id);//包含了就删除
            }
        }
        if (list.size()==4){
            if(list.contains(id)==false){
                list.removeLast();  //删除最后一个
            }else{
                list.remove(id);    //删除浏览过的
            }
        }
        list.addFirst(id);//添加到头部

        //把得到最新的list中的数据拼成一个id字符串

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++) {
            if (i > 0) {
                sb.append("-");
            }
            sb.append(list.get(i));
        }
        return sb.toString();
    }
}
