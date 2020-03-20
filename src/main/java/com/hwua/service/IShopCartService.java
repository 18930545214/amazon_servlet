package com.hwua.service;

import com.hwua.pojo.News;
import com.hwua.pojo.Product;
import com.hwua.pojo.ShopCart;

import java.util.List;

public interface IShopCartService {
    public int add(ShopCart shopCart) throws Exception;//添加购物车

    public List<Product> queryByUid(int uid) throws Exception;//uid查询

    public int delete(int pid)throws Exception;//删除购物车
}
