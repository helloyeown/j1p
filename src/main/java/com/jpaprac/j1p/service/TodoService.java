package com.jpaprac.j1p.service;

import com.jpaprac.j1p.dto.TodoDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface TodoService {
    
    PageResponseDTO<TodoDTO> getList();

    TodoDTO register(TodoDTO dto);

    TodoDTO getOne(Long tno);

}
