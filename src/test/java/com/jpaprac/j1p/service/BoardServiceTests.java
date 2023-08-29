package com.jpaprac.j1p.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpaprac.j1p.dto.page.PageRequestDTO;
import com.jpaprac.j1p.service.board.BoardService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardServiceTests {
    
    @Autowired
    private BoardService boardService;

    @Test
    public void getlistWithRCntTest(){

        log.info(boardService.getlistWithRCnt(new PageRequestDTO(1, 10, "t", "8")));

    }

    @Test
    public void readTest(){

        log.info(boardService.readBoard(102L));

    }

}
