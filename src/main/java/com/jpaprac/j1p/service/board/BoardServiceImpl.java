package com.jpaprac.j1p.service.board;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.jpaprac.j1p.domain.Board;
import com.jpaprac.j1p.dto.board.BoardDTO;
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
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<BoardListRCntDTO> getlistWithRCnt(PageRequestDTO dto) {

        return repository.searchDTORCnt(dto);

    }

    @Override
    public BoardDTO readBoard(Long bno) {

        Optional<Board> result = repository.findById(bno);
        Board entity = result.orElseThrow();
        BoardDTO dto = modelMapper.map(entity, BoardDTO.class);

        return dto;
    }

}
