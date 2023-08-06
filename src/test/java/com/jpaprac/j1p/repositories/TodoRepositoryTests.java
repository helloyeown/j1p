package com.jpaprac.j1p.repositories;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jpaprac.j1p.domain.Todo;
import com.jpaprac.j1p.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    // 더미데이터 삽입
    @Test
    public void testInsert(){

        // 100건
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Todo todo = Todo.builder()
                .title("title" + i).build();

            Todo result = todoRepository.save(todo);

            log.info(result);
        });
    }

    // 페이징
    @Test
    public void pagingTest(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

        Page<Todo> result = todoRepository.findAll(pageable);
        log.info(result);
        // Page 1 of 10 containing com.jpaprac.j1p.domain.Todo instances

    }

    // 조회
    // entity -> dto 변환
    @Test
    public void readTest(){

        Optional<Todo> result = todoRepository.findById(1L);

        Todo entity = result.orElseThrow();
        log.info("entity: " + entity);

        TodoDTO dto = modelMapper.map(entity, TodoDTO.class);
        log.info("dto: " + dto);

    }

}
