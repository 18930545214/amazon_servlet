package com.hwua.dao.impl;

import com.hwua.dao.IShopCartDao;
import com.hwua.pojo.Product;
import com.hwua.pojo.ShopCart;
import com.hwua.pojo.User;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class ShopCartDaoImpl implements IShopCartDao {
    /**
     * 添加购物车
     * @param shopCart
     * @return
     * @throws Exception
     */
    @Override
    public int add(ShopCart shopCart) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into amz_shop_cart values(null,?,?,?)";
        return qr.update(sql,shopCart.getPid(),shopCart.getPum(),shopCart.getUid());
    }

    /**
     * 按用户id查找购物车
     * @param uid
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> queryByUid(int uid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select p.id,p.name,p.description,p.price,p.stock,p.major_id,(s.pnum)minor_id,p.img_source\n" +
                "from amz_product p JOIN amz_shop_cart s\n" +
                "ON p.id=s.pid\n" +
                "where s.uid=?";
        return qr.query(sql, new BeanListHandler<>(Product.class),uid);
    }

    /**
     * 按商品ID查询购物车
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public ShopCart queryByPid(int pid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_shop_cart where pid=?";
        return qr.query(sql, new BeanHandler<>(ShopCart.class),pid);
    }

    /**
     * 修改数量
     * @param shopCart
     * @return
     * @throws Exception
     */
    @Override
    public int update(ShopCart shopCart) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "update amz_shop_cart set pnum=(pnum+?) where pid=?";
        return qr.update(sql,shopCart.getPum(),shopCart.getPid());
    }

    /**
     * 删除购物车
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public int delete(int pid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "delete from amz_shop_cart where pid=?";
        return qr.update(sql,pid);
    }
}
