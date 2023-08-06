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
        log.info("result------------------------ " + result);

        List<TodoDTO> list = result.getContent().stream().map(todo -> modelMapper.map(todo, TodoDTO.class)).collect(Collectors.toList());
        log.info("list------------------- " + list);

        PageResponseDTO<TodoDTO> response = new PageResponseDTO<>();
        response.setDtoList(list);

        return response;

        // return null;

    }

    // 등록
    @Override
    public TodoDTO register(TodoDTO dto) {

        // dto -> entity
        Todo entity = modelMapper.map(dto, Todo.class);

        // entity save
        Todo result = repository.save(entity);

        // save한 결과를 다시 dto 타입으로 변환
        return modelMapper.map(result, TodoDTO.class);

    }

}
