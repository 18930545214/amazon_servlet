package com.hwua.service.impl;

import com.hwua.dao.IOrderDetailDao;
import com.hwua.dao.impl.OrderDetailDaoImpl;
import com.hwua.pojo.Order;
import com.hwua.pojo.OrderDetail;
import com.hwua.service.IOrderDetailService;

import java.util.List;

public class OrderDetailServiceImpl implements IOrderDetailService {
    private IOrderDetailDao orderDetailDao = new OrderDetailDaoImpl();
    @Override
    public int add(OrderDetail orderDetail) throws Exception {
        return orderDetailDao.add(orderDetail);
    }

    @Override
    public List<OrderDetail> queryByOid(int id) throws Exception {
        return orderDetailDao.queryByOid(id);
    }
}
