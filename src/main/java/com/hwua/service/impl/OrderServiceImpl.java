package com.hwua.service.impl;

import com.hwua.dao.IOrderDao;
import com.hwua.dao.IOrderDetailDao;
import com.hwua.dao.IProductDao;
import com.hwua.dao.impl.OrderDaoImpl;
import com.hwua.dao.impl.OrderDetailDaoImpl;
import com.hwua.dao.impl.ProductDaoImpl;
import com.hwua.pojo.Order;
import com.hwua.pojo.OrderDetail;
import com.hwua.pojo.Product;
import com.hwua.service.IOrderService;

import java.util.List;

public class OrderServiceImpl implements IOrderService {
    private IOrderDao orderDao = new OrderDaoImpl();
    private IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    private IProductDao productDao = new ProductDaoImpl();
    @Override
    public Order add(Order order) throws Exception {
        Order orders = null;
        if(orderDao.add(order)>0){
            orders = orderDao.queryByTime(order.getCreate_Time());
        }
        return orders;
    }

    @Override
    public Order queryByName(String name) throws Exception {
        return orderDao.queryByName(name);
    }
}
