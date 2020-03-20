package com.hwua.dao.impl;

import com.hwua.dao.ICategoryDao;
import com.hwua.pojo.Category;
import com.hwua.pojo.Comment;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class CategoryDaoImpl implements ICategoryDao {
    /**
     * 分类目录
     * @return
     * @throws Exception
     */
    @Override
    public List<Category> queryAll() throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_product_category";
        return qr.query(sql, new BeanListHandler<>(Category.class));
    }
}
