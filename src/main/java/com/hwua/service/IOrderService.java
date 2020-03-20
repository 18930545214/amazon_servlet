package com.hwua.service;

import com.hwua.pojo.Order;

import java.util.List;

public interface IOrderService {
    public Order add(Order order)throws Exception;
    public Order queryByName(String name)throws Exception;
}
