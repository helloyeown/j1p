package com.jpaprac.j1p.service.board;

import org.springframework.stereotype.Service;

import com.jpaprac.j1p.dto.board.BoardListRCntDTO;
import com.jpaprac.j1p.dto.page.PageRequestDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.repositories.BoardRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
    
    private final BoardRepository repository;

    @Override
    public PageResponseDTO<BoardListRCntDTO> getlistWithRCnt(PageRequestDTO dto) {

        return repository.searchDTORCnt(dto);

    }

}
