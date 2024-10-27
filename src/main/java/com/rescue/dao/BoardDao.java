package com.rescue.dao;

import com.rescue.entity.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author hxh
 * @version V1.00
 * @date 2022/2/13 18:11
 * @since V1.00
 */
@Mapper
@Repository
public interface BoardDao {
    void deleteBoard(@Param("id") Integer id);

    void addBoard(@Param("board") Board board);

    void updateBoard(@Param("board") Board board);

   Board getBoardById(@Param("id") Integer id);

    List<Board> findBoard(@Param("board") Board board);

    List<Board> selectAll(@Param("map") Map state);

}
