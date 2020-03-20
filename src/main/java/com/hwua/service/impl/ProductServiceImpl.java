package com.hwua.service.impl;

import com.hwua.dao.IProductDao;
import com.hwua.dao.impl.ProductDaoImpl;
import com.hwua.pojo.Product;
import com.hwua.service.IProductService;

import java.util.List;

public class ProductServiceImpl implements IProductService {
    private IProductDao productDao = new ProductDaoImpl();

    @Override
    public int update(int count, int pid) throws Exception {
        return productDao.update(count,pid);
    }

    @Override
    public List<Product> queryAll( int start, int pageSize) throws Exception {
        return productDao.queryAll(start,pageSize);
    }

    @Override
    public List<Product> queryAllHot() throws Exception {
        return productDao.queryAllHot();
    }

    @Override
    public Product queryById(int id) throws Exception {
        return productDao.queryById(id);
    }

    @Override
    public List<Product> queryByMajorId(int majorIid,int start, int pageSize) throws Exception {
        return productDao.queryByMajorId(majorIid,start,pageSize);
    }

    @Override
    public List<Product> queryByMinorId(int minorIid,int start, int pageSize) throws Exception {
        return productDao.queryByMinorId(minorIid,start,pageSize);
    }


    @Override
    public List<Product> queryAllByName(String name,int start, int pageSize) throws Exception {
        return productDao.queryAllByName(name,start,pageSize);
    }

    @Override
    public Long queryCount() throws Exception {
        return productDao.queryCount();
    }

    @Override
    public Long queryCountByMajorId(int majorIid) throws Exception {
        return productDao.queryCountByMajorId(majorIid);
    }

    @Override
    public Long queryCountByMinorId(int minorIid) throws Exception {
        return productDao.queryCountByMinorId(minorIid);
    }

    @Override
    public Long queryCountByName(String name) throws Exception {
        return productDao.queryCountByName(name);
    }

}
