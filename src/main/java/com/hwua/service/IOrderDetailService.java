package com.hwua.service;

import com.hwua.pojo.Order;
import com.hwua.pojo.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    public int add(OrderDetail orderDetail)throws Exception;
    public List<OrderDetail> queryByOid(int id)throws Exception;
}
