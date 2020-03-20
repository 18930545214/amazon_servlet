package com.hwua.dao.impl;

import com.hwua.dao.IProductDao;
import com.hwua.pojo.News;
import com.hwua.pojo.Product;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class ProductDaoImpl implements IProductDao {
    /**
     * 生成订单后减少库存
     * @param count
     * @param pid
     * @return
     * @throws Exception
     */
    @Override
    public int update(int count,int pid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "update amz_product set stock=(stock-?) where id=?";
        return qr.update(sql,count,pid);
    }

    /**
     * 分页查询所有商品
     * @param start
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> queryAll( int start, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_product  limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Product.class),start,pageSize);
    }

    /**
     * 热销推荐
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> queryAllHot() throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select *\n" +
                "from amz_product\n" +
                "where id in(select pid\n" +
                "from amz_order_detail\n" +
                "group by pid\n" +
                "order by count(pid))\n" +
                "limit 6;";
        return qr.query(sql, new BeanListHandler<>(Product.class));
    }

    /**
     * 按id查询
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Product queryById(int id) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_product where id=?";
        return qr.query(sql, new BeanHandler<>(Product.class),id);
    }

    /**
     * 一级目录查询
     * @param majorIid
     * @param start
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> queryByMajorId(int majorIid,int start, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_product where major_id=? limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Product.class),majorIid,start,pageSize);
    }

    /**
     * 二级目录查询
     * @param minorId
     * @param start
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> queryByMinorId(int minorId,int start, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_product where minor_id=? limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Product.class),minorId,start,pageSize);
    }

    /**
     * 商品名模糊查询
     * @param name
     * @param start
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Product> queryAllByName(String name,int start, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_product  where name like ? limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Product.class),name,start,pageSize);
    }

    /**
     * 商品个数
     * @return
     * @throws Exception
     */
    @Override
    public Long queryCount() throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select count(*) from amz_product";
        return qr.query(sql, new ScalarHandler<>());
    }

    /**
     * 一级目录个数
     * @param majorIid
     * @return
     * @throws Exception
     */
    @Override
    public Long queryCountByMajorId(int majorIid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select count(*) from amz_product where major_id=?";
        return qr.query(sql, new ScalarHandler<>(),majorIid);
    }

    /**
     * 二级目录个数
     * @param minorIid
     * @return
     * @throws Exception
     */
    @Override
    public Long queryCountByMinorId(int minorIid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select count(*) from amz_product where minor_id=?";
        return qr.query(sql, new ScalarHandler<>(),minorIid);
    }

    /**
     * 模糊查询个数
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Long queryCountByName(String name) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select count(*) from amz_product where name like ?";
        return qr.query(sql, new ScalarHandler<>(),name);
    }
}
