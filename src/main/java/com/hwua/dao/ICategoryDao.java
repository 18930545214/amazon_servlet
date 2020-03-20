package com.hwua.dao;

import com.hwua.pojo.Category;

import java.util.List;

public interface ICategoryDao {
    public List<Category> queryAll() throws Exception;//查询全部
}
