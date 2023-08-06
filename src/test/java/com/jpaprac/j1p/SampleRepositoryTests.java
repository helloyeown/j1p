package com.jpaprac.j1p;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jpaprac.j1p.domain.Sample;
import com.jpaprac.j1p.repositories.SampleRepository;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class SampleRepositoryTests {
    
    @Autowired
    private SampleRepository repository;

    @Test
    public void test1(){
        log.info(repository.getClass().getName());
    }

    // 더미 데이터 삽입
    @Test
    public void insertTest(){

        IntStream.range(1, 100).forEach(i -> {
            Sample obj = Sample.builder()
                .keyCol("u" + i)
                .first("first1")
                .last("last").build();
                
                repository.save(obj);
        });

    }

    // 조회
    @Test
    public void readTest(){

        Optional<Sample> result = repository.findById("u1");

        Sample obj = result.orElseThrow();

        log.info(obj);

    }

    // 삭제
    @Test
    public void deleteTest(){

        repository.deleteById("u1");

    }

    // 페이징
    @Test
    public void pagingTest(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("keyCol").descending());
        // 페이지는 0부터 시작(주의)

        Page<Sample> result = repository.findAll(pageable);
        // Pageable의 리턴 타입은 항상 Page

        log.info(result.getTotalElements());        // 총 데이터 갯수
        log.info(result.getTotalPages());           // 총 페이지 수
        log.info(result.hasNext());                 // 다음 페이지 존재 유무
        log.info(result.hasPrevious());

        result.getContent().forEach(obj -> log.info(obj));  // 내용물 리스트

    }

}
