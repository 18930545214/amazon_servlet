package com.hwua.dao.impl;

import com.hwua.dao.INewDao;
import com.hwua.pojo.Comment;
import com.hwua.pojo.News;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class NewsDaoImpl implements INewDao {

    /**
     * 查询5条新闻倒叙
     * @return
     * @throws Exception
     */
    @Override
    public List<News> queryAll() throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_news order by create_time desc limit 5";
        return qr.query(sql, new BeanListHandler<>(News.class));
    }

    /**
     * 按id查询新闻
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public News queryAllById(int id) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_news where id = ?";
        return qr.query(sql, new BeanHandler<>(News.class),id);
    }
}
