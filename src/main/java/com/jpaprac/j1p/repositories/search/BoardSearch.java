package com.jpaprac.j1p.repositories.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jpaprac.j1p.domain.Board;
import com.jpaprac.j1p.dto.board.BoardListRCntDTO;
import com.jpaprac.j1p.dto.page.PageRequestDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;

public interface BoardSearch {
    
    // v1
    Page<Board> search1(String searchType, String keyword);

    // v2
    Page<Object[]> searchWithRCnt(String searchType, String keyword, Pageable pageable);

    // v3
    PageResponseDTO<BoardListRCntDTO> searchDTORCnt(PageRequestDTO dto);

    default Pageable makePageable(PageRequestDTO dto){

        Pageable pageable = PageRequest.of(dto.getPage() - 1, dto.getSize(), Sort.by("bno").descending());

        return pageable;

    }

}
