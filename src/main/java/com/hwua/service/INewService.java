package com.hwua.service;

import com.hwua.pojo.Comment;
import com.hwua.pojo.News;

import java.util.List;

public interface INewService {
    public List<News> queryAll() throws Exception;//查询全部
    public News queryAllById(int id) throws Exception;//id查询

}
