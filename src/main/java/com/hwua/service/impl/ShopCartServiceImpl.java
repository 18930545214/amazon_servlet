package com.hwua.service.impl;

import com.hwua.dao.IShopCartDao;
import com.hwua.dao.impl.ShopCartDaoImpl;
import com.hwua.pojo.Product;
import com.hwua.pojo.ShopCart;
import com.hwua.service.IShopCartService;

import java.util.List;

public class ShopCartServiceImpl implements IShopCartService {
    private IShopCartDao shopCartDao = new ShopCartDaoImpl();
    @Override
    public int add(ShopCart shopCart) throws Exception {
        int res = 0;
        ShopCart shopCart1 = shopCartDao.queryByPid((int) shopCart.getPid());
        if (shopCart1==null){
            res = shopCartDao.add(shopCart);
        }else {
            res = shopCartDao.update(shopCart);
        }
        System.out.println(res);
        return res;
    }

    @Override
    public List<Product> queryByUid(int uid) throws Exception {
        return shopCartDao.queryByUid(uid);
    }


    @Override
    public int delete(int pid) throws Exception {
        return shopCartDao.delete(pid);
    }
}
