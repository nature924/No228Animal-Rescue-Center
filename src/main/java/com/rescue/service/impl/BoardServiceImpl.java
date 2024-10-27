package com.rescue.service.impl;

import com.rescue.dao.BoardDao;
import com.rescue.entity.Board;
import com.rescue.service.BoardService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class BoardServiceImpl implements BoardService {


    @Autowired
    BoardDao dao;

    @Override
    public void deleteBoard(Integer id) {
        dao.deleteBoard(id);
    }


    @Override
    public void addBoard(Board board) {
        dao.addBoard(board);
    }

    @Override
    public void updateBoard(Board board) {
        dao.updateBoard(board);
    }

    @Override
    public Board getBoardById(Integer id) {
        return   dao.getBoardById(id);

    }

    @Override
    public PageInfo<Board> findBoard(Board board, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Board> list = dao.findBoard(board);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public List<Board> selectAll(Map state) {
        return  dao.selectAll(state);
    }

}
