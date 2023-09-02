package com.jpaprac.j1p.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaprac.j1p.dto.board.BoardDTO;
import com.jpaprac.j1p.dto.board.BoardListRCntDTO;
import com.jpaprac.j1p.dto.page.PageRequestDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.service.board.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
@CrossOrigin
public class BoardController {
    
    private final BoardService boardService;

    @GetMapping("/list")
    public PageResponseDTO<BoardListRCntDTO> getList(PageRequestDTO dto){
        
        return boardService.getlistWithRCnt(dto);

    }

    @GetMapping("/{bno}")
    public BoardDTO readBoard(@PathVariable Long bno){

        return boardService.readBoard(bno);

    }

}
