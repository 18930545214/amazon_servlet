package com.hwua.dao;

import com.hwua.pojo.Order;
import com.hwua.pojo.OrderDetail;

import java.util.List;

public interface IOrderDetailDao {
    public int add(OrderDetail orderDetail)throws Exception;
    public List<OrderDetail> queryByOid(int oid)throws Exception;
}
