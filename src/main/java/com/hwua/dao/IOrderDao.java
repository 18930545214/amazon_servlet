package com.hwua.dao;

import com.hwua.pojo.Order;

import java.util.List;

public interface IOrderDao {
    public int add(Order order)throws Exception;
    public Order queryByTime(String time)throws Exception;
    public Order queryByName(String name)throws Exception;
}
