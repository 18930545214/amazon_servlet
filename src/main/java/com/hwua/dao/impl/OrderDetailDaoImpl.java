package com.hwua.dao.impl;

import com.hwua.dao.IOrderDetailDao;
import com.hwua.pojo.Order;
import com.hwua.pojo.OrderDetail;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class OrderDetailDaoImpl implements IOrderDetailDao {
    /**
     * 添加订单明细
     * @param orderDetail
     * @return
     * @throws Exception
     */
    @Override
    public int add(OrderDetail orderDetail) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into amz_order_detail values(null,?,?,?,?)";
        return qr.update(sql,orderDetail.getOid(),orderDetail.getPid(),orderDetail.getQuantity(),orderDetail.getMoney());
    }

    /**
     * 按oid查询
     * @param oid
     * @return
     * @throws Exception
     */
    @Override
    public List<OrderDetail> queryByOid(int oid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_order_detail where oid=?";
        return qr.query(sql, new BeanListHandler<>(OrderDetail.class),oid);
    }
}
