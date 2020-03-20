package com.hwua.dao;

import com.hwua.pojo.Product;
import com.hwua.pojo.ShopCart;

import java.util.List;

public interface IShopCartDao {
    public int add(ShopCart shopCart) throws Exception;//添加购物车

    public List<Product> queryByUid(int uid) throws Exception;//查询全部

    public ShopCart queryByPid(int pid) throws Exception;//pid全部

    public int update(ShopCart shopCart)throws Exception;//修改购物车

    public int delete(int pid)throws Exception;//删除购物车
}
