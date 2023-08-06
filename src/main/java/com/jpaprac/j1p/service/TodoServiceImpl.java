package com.jpaprac.j1p.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpaprac.j1p.domain.Todo;
import com.jpaprac.j1p.dto.TodoDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.repositories.TodoRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@RequiredArgsConstructor
@Log4j2
public class TodoServiceImpl implements TodoService {
    
    private final TodoRepository repository;
    private final ModelMapper modelMapper;

    // 목록
    @Override
    public PageResponseDTO<TodoDTO> getList() {

        Pageable pageable = PageRequest.of(0, 20, Sort.by("tno").descending());

        Page<Todo> result = repository.findAll(pageable);

        List<TodoDTO> list = result.getContent().stream().map(todo -> modelMapper.map(result, TodoDTO.class)).collect(Collectors.toList());

        // PageResponseDTO<TodoDTO> response = new PageResponseDTO<>(list, null, null);
        // response.setDtoList(list);

        // return response;

        return null;

    }

}
