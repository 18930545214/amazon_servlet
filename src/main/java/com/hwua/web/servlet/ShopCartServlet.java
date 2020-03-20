package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.Product;
import com.hwua.pojo.ShopCart;
import com.hwua.pojo.User;
import com.hwua.service.IProductService;
import com.hwua.service.IShopCartService;
import com.hwua.service.IUserService;
import com.hwua.service.impl.ProductServiceImpl;
import com.hwua.service.impl.ShopCartServiceImpl;
import com.hwua.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/shopCart.do")
public class ShopCartServlet extends HttpServlet {
    private IShopCartService shopCartService = new ShopCartServiceImpl();
    private IProductService productService = new ProductServiceImpl();
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
        //添加购物车
        if (param.equals("add")){
            System.out.println("购物车");
            User user = (User) req.getSession().getAttribute("user");
            long pid = Long.parseLong(req.getParameter("pid"));
            long count = Long.parseLong(req.getParameter("count"));
            try {
                ShopCart shopCart = new ShopCart(pid,count,user.getId());
                System.out.println(user);
                int add = shopCartService.add(shopCart);
                String jsonStr = JSON.toJSONString(add);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //删除购物车
        }else if (param.equals("delete")){
            System.out.println("删除商品");
            int id = Integer.parseInt(req.getParameter("id"));
            try {
                int delete = shopCartService.delete(id);
                String jsonStr = JSON.toJSONString(delete);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //查询购物车
        }else if (param.equals("queryAll")){
            int uid = Integer.parseInt(req.getParameter("uid"));
            System.out.println(uid);
            try {
                List<Product> products = shopCartService.queryByUid(uid);
                String jsonStr = JSON.toJSONString(products);
                out.write(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
