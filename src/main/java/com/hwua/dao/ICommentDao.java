package com.hwua.dao;

import com.hwua.pojo.Comment;

import java.util.List;

public interface ICommentDao {
    public int save(Comment comment) throws Exception;//增加
    public List<Comment> queryAll(int start, int pageSize) throws Exception;//查询全部
    public Long queryCount()throws Exception; //查询留言个数
}
