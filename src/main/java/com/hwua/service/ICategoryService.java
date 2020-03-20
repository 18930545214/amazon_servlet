package com.hwua.service;

import com.hwua.pojo.Category;
import com.hwua.pojo.News;

import java.util.List;

public interface ICategoryService {
    public List<Category> queryAll() throws Exception;//查询全部
}
