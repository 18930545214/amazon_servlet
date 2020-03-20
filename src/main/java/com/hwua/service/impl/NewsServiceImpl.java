package com.hwua.service.impl;

import com.hwua.dao.INewDao;
import com.hwua.dao.impl.NewsDaoImpl;
import com.hwua.pojo.Comment;
import com.hwua.pojo.News;
import com.hwua.service.INewService;

import java.util.List;

public class NewsServiceImpl implements INewService {
    private INewDao newDao = new NewsDaoImpl();

    @Override
    public List<News> queryAll() throws Exception {
        return newDao.queryAll();
    }

    @Override
    public News queryAllById(int id) throws Exception {
        return newDao.queryAllById(id);
    }
}
