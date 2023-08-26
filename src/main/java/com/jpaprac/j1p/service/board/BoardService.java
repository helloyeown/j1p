package com.jpaprac.j1p.service.board;

import com.jpaprac.j1p.dto.board.BoardListRCntDTO;
import com.jpaprac.j1p.dto.page.PageRequestDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface BoardService {

    PageResponseDTO<BoardListRCntDTO> getlistWithRCnt(PageRequestDTO dto);
    
}
