package com.hwua.web.servlet;

import com.alibaba.fastjson.JSON;
import com.hwua.pojo.Order;
import com.hwua.pojo.OrderDetail;
import com.hwua.pojo.Product;
import com.hwua.pojo.User;
import com.hwua.service.*;
import com.hwua.service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/order.do")
public class OrderServlet extends HttpServlet {
    private IOrderService orderService = new OrderServiceImpl();
    private IOrderDetailService orderDetailService = new OrderDetailServiceImpl();
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
        //商品详情页直接购买
        if (param.equals("doBuy")){
            System.out.println("购买");
            String pid = req.getParameter("pid");
            String count = req.getParameter("count");
            int count1 = Integer.parseInt(count);
            Double price = (Double.valueOf(req.getParameter("price")))*count1;
            User user = (User) req.getSession().getAttribute("user");
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            int ros = -1;
            try {
                Order order = new Order(user.getId(),user.getUName(),user.getAddress(),time,price,1,1);
                Order order1 = orderService.add(order);
                if (order1!=null){
                    OrderDetail orderDetail = new OrderDetail(order1.getId(),Long.parseLong(pid),Long.parseLong(count),price);
                    ros = productService.update(count1,Integer.parseInt(pid));
                    ros = orderDetailService.add(orderDetail);
                    String jsonStr = JSON.toJSONString(ros);
                    System.out.println(jsonStr);
                    out.write(jsonStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String jsonStr = JSON.toJSONString(ros);
            System.out.println(jsonStr);
            out.write(jsonStr);

            //订单明细
        }else if (param.equals("queryByUid")){
            System.out.println("订单");
            ArrayList<Product> list = new ArrayList<>();
            Order order = null;
            User user = (User) req.getSession().getAttribute("user");
            try {
                order = orderService.queryByName(user.getUName());
                    List<OrderDetail> orderDetails = orderDetailService.queryByOid((int) order.getId());
                    order.setOrderDetails(orderDetails);
                    //循环查询商品
                    for (OrderDetail orderDetail:orderDetails){
                        Product product = productService.queryById((int) orderDetail.getPid());
                        list.add(product);
                    }
                    order.setProducts(list);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String jsonStr = JSON.toJSONString(order);
            System.out.println(order.getCreate_Time());
            System.out.println(jsonStr);
            out.write(jsonStr);


            //购物车提交
        }else if (param.equals("ByShop")){
            System.out.println("提交");
            Double money = Double.valueOf(req.getParameter("money"));
            User user = (User) req.getSession().getAttribute("user");
            String[] pid = req.getParameter("pid").split("-");
            String[] count = req.getParameter("count").split("-");
            String[] price = req.getParameter("price").split("-");
            //格式: pid-2-3-4
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            try {
                Order order = new Order(user.getId(),user.getUName(),user.getAddress(),time,money,1,1);
                Order order1 = orderService.add(order);
                if (order1!=null){
                    int ros = -1;
                    //for循环取值,下标从1开始
                    for (int i = 1;i<pid.length;i++){
                        OrderDetail orderDetail = new OrderDetail(order1.getId(),Long.parseLong(pid[i]),Long.parseLong(count[i]),Double.valueOf(price[i]));
                        ros = orderDetailService.add(orderDetail);
                        shopCartService.delete(Integer.parseInt(pid[i]));
                        productService.update(Integer.parseInt(count[i]), Integer.parseInt(pid[i]));
                    }
                    String jsonStr = JSON.toJSONString(ros);
                    System.out.println(ros+"测试");
                    out.write(jsonStr);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
