package com.rescue.service;

import com.github.pagehelper.PageInfo;
import com.rescue.entity.Comments;

import java.util.List;
import java.util.Map;


public interface CommentsService {
    void deleteComments(Integer id);

    void addComments(Comments comments);

    void updateComments(Comments comments);

   Comments getCommentsById(Integer id);

    PageInfo<Comments> findComments(Comments comments, Integer pageNum, Integer pageSize);

    List<Comments> selectAll(Map map);

}
