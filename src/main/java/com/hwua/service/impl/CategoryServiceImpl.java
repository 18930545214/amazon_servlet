package com.hwua.service.impl;

import com.hwua.dao.ICategoryDao;
import com.hwua.dao.impl.CategoryDaoImpl;
import com.hwua.pojo.Category;
import com.hwua.service.ICategoryService;

import java.util.List;

public class CategoryServiceImpl implements ICategoryService {
    private ICategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> queryAll() throws Exception {
        return categoryDao.queryAll();
    }
}
