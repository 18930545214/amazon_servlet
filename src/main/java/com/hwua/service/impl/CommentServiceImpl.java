package com.hwua.service.impl;

import com.hwua.dao.ICommentDao;
import com.hwua.dao.impl.CommentDaoImpl;
import com.hwua.pojo.Comment;
import com.hwua.service.ICommentService;

import java.util.List;

public class CommentServiceImpl implements ICommentService {
    private ICommentDao commentDao = new CommentDaoImpl();

    @Override
    public int save(Comment comment) throws Exception {
        return commentDao.save(comment);
    }

    @Override
    public List<Comment> queryAll(int start, int pageSize) throws Exception {
        return commentDao.queryAll(start,pageSize);
    }

    @Override
    public Long queryCount() throws Exception {
        return commentDao.queryCount();
    }
}
