package com.rescue.dao;

import com.rescue.entity.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Mapper
@Repository
public interface CommentsDao {
    void deleteComments(@Param("id") Integer id);

    void addComments(@Param("comments") Comments comments);

    void updateComments(@Param("comments") Comments comments);

   Comments getCommentsById(@Param("id") Integer id);

    List<Comments> findComments(@Param("comments") Comments comments);

    List<Comments> selectAll(@Param("data") Map data);

}
