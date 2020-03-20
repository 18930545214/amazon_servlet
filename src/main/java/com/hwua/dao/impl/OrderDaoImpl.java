package com.hwua.dao.impl;

import com.hwua.dao.IOrderDao;
import com.hwua.pojo.News;
import com.hwua.pojo.Order;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class OrderDaoImpl implements IOrderDao {
    /**
     * 生存一条新的订单
     * @param order
     * @return
     * @throws Exception
     */
    @Override
    public int add(Order order) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into amz_order values(null,?,?,?,?,?,?,?)";
        return qr.update(sql,order.getUid(),order.getuName(),order.getuAddress(),order.getCreate_Time(),order.getMoney(),order.getStatus(),order.getType());
    }

    /**
     * 按时间查询
     * @param time
     * @return
     * @throws Exception
     */
    @Override
    public Order queryByTime(String time) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_order where create_time=?";
        return qr.query(sql, new BeanHandler<>(Order.class),time);
    }

    /**
     * 按姓名查询
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Order queryByName(String name) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_order where uname=? order by create_time desc limit 1";
        return qr.query(sql, new BeanHandler<>(Order.class),name);
    }
}
