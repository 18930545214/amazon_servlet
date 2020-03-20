package com.hwua.service;

import com.hwua.pojo.Category;
import com.hwua.pojo.News;
import com.hwua.pojo.Product;

import java.util.List;

public interface IProductService {
    public int update(int count,int pid)throws Exception;//修改库存
    public List<Product> queryAll( int start, int pageSize) throws Exception;//查询全部
    public List<Product> queryAllHot() throws Exception;//热销榜
    public Product queryById(int id) throws Exception;//id查询
    public List<Product> queryByMajorId(int majorIid,int start, int pageSize) throws Exception;//majorId查询;
    public List<Product> queryByMinorId(int minorIid,int start, int pageSize) throws Exception;//minorId查询;
    public List<Product> queryAllByName(String name,int start, int pageSize) throws Exception;//name模糊查询
    public Long queryCount()throws Exception; //查询商品个数
    public Long queryCountByMajorId(int majorIid)throws Exception; //majorId查询商品个数
    public Long queryCountByMinorId(int minorIid)throws Exception; //minorId查询商品个数
    public Long queryCountByName(String name)throws Exception; //name查询商品个数
}
