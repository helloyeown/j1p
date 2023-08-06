package com.jpaprac.j1p.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaprac.j1p.dto.TodoDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.service.TodoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/todos/")
public class TodoController {
    
    private final TodoService todoService;

    @GetMapping("list")
    public PageResponseDTO<TodoDTO> getList(){

        return todoService.getList();

    }

}
