package com.jpaprac.j1p.repositories.search;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.jpaprac.j1p.domain.Board;

public interface BoardSearch {
    
    // v1
    Page<Board> search1(String searchType, String keyword);

}
