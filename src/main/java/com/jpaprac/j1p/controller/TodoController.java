package com.jpaprac.j1p.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("api/todos")
@CrossOrigin
public class TodoController {
    
    private final TodoService todoService;

    // 목록
    @GetMapping("/list")
    public PageResponseDTO<TodoDTO> getList(){

        return todoService.getList();

    }

    // 등록
    @PostMapping("/")
    public TodoDTO register(@RequestBody TodoDTO dto){

        log.info("register controller..................");
        log.info(dto);

        return todoService.register(dto);

    }

    // 조회
    @GetMapping("/{tno}")
    public TodoDTO get(@PathVariable("tno") Long tno){

        return todoService.getOne(tno);

    }

    // 삭제
    @DeleteMapping("/{tno}")
    public Map<String, Long> delete(@PathVariable("tno") Long tno){

        todoService.delete(tno);
        return Map.of("result", tno);

    }

    // 수정
    @PutMapping("/{tno}")
    public Map<String, Long> modify(@PathVariable Long tno, @RequestBody TodoDTO dto){

        dto.setTno(tno);    // 안전장치
        todoService.modify(dto);

        return Map.of("result", tno);

    }

}
