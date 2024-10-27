package com.rescue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rescue.dao.CommentsDao;
import com.rescue.dao.UserDao;
import com.rescue.entity.Comments;
import com.rescue.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class CommentsServiceImpl implements CommentsService {


    @Autowired
    CommentsDao dao;

    @Autowired
    UserDao userDao;

    @Override
    public void deleteComments(Integer id) {
        dao.deleteComments(id);
    }


    @Override
    public void addComments(Comments comments) {
        dao.addComments(comments);
    }

    @Override
    public void updateComments(Comments comments) {
        dao.updateComments(comments);
    }

    @Override
    public Comments getCommentsById(Integer id) {
        return   dao.getCommentsById(id);

    }

    @Override
    public PageInfo<Comments> findComments(Comments comments, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Comments> list = dao.findComments(comments);
        for(Comments comments1:list){
            comments1.setUser(userDao.getUserById(comments1.getUid()));
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Comments> selectAll(Map state) {
        List<Comments> comments = dao.selectAll(state);
        for(Comments cc : comments){
            cc.setUser(userDao.getUserById(cc.getUid()));
        }
        return  comments;
    }

}
