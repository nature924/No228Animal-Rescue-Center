package com.rescue.service;

import com.rescue.entity.Board;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author hxh
 * @version V1.00
 * @date 2022/2/13 18:10
 * @since V1.00
 */
public interface BoardService {
    void deleteBoard(Integer id);

    void addBoard(Board board);

    void updateBoard(Board board);

   Board getBoardById(Integer id);

    PageInfo<Board> findBoard(Board board, Integer pageNum, Integer pageSize);

    List<Board> selectAll(Map map);

}
