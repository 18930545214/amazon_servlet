package com.hwua.dao.impl;

import com.hwua.dao.ICommentDao;
import com.hwua.pojo.Comment;
import com.hwua.pojo.User;
import com.hwua.service.ICommentService;
import com.hwua.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.util.List;

public class CommentDaoImpl implements ICommentDao {
    /**
     * 添加留言
     * @param comment
     * @return
     * @throws Exception
     */
    @Override
    public int save(Comment comment) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into amz_comment values(null,?,?,?,?,?,?)";
        return qr.update(sql,comment.getReply(),comment.getContent(),comment.getCreate_Time(),comment.getReply_Time(),comment.getNick_Name(),comment.getState());
    }

    /**
     * 分页查询留言
     * @param start
     * @param pageSize
     * @return
     * @throws Exception
     */
    @Override
    public List<Comment> queryAll(int start, int pageSize) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select * from amz_comment order by create_time desc limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Comment.class),start,pageSize);
    }

    /**
     * 查询留言个数
     * @return
     * @throws Exception
     */
    @Override
    public Long queryCount() throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select count(*) from amz_comment";
        return qr.query(sql, new ScalarHandler<>());
    }
}
