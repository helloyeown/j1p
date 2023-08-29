package com.jpaprac.j1p.repositories;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;

import com.jpaprac.j1p.domain.Board;
import com.jpaprac.j1p.dto.page.PageRequestDTO;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardRepositoryTests {
 
    @Autowired
    private BoardRepository repository;

    @Test
    public void insertTest(){

        for(int i=0; i<100; i++){

            Board board = Board.builder()
                .title("test title" + i)
                .content("test content" + i)
                .writer("yeown" + i % 10)
                .build();

                repository.save(board);
        }
    }

    @Test
    public void readTest(){

        Optional<Board> result = repository.findById(1L);

        Board board = result.orElseThrow();

        log.info(board);

    }

    @Test
    @Transactional
    @Commit
    public void modifyTest(){

        // Optional<Board> result = repository.findById(1L);
        // Board board = result.orElseThrow();

        // board.changeTitle("modified Title");

        // repository.save(board);

        int count = repository.modifyTitle("Modify by repository", 1L);

        log.info(count);

    }

    @Test
    public void fineByTitleContainingTest(){

        log.info(repository.findByTitleContaining("1"));

    }

    @Test
    public void testQuery1(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

        Page<Board> list = repository.findByContentContaining("8", pageable);

        log.info(list);

    }

    @Test
    public void testQuery1_1(){

        List<Board> result = repository.listTitle("3");

        log.info(result.size());
        log.info(result);

    }

    @Test
    public void testQuery1_2(){

        // List<Object[]> list = repository.listTitle2("3");
        
        // log.info(list.size());
        
        // list.forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
	public void testQuery1_3(){

		Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());

		Page<Object[]> result = repository.listTitle2("1", pageable);

		log.info(result);

	}

    @Test
    public void testNatve(){

        List<Object[]> result = repository.listNative();

        result.forEach(arr -> log.info(Arrays.toString(arr)));

    }

    @Test
    public void testSearch1(){

        repository.search1("t", "8");

    }

    @Test
    public void RCntTest(){

        List<Object[]> result = repository.getListWithRCnt();

        for (Object[] board : result) {
            log.info(Arrays.toString(board));
        }
    }

    @Test
    public void testListWithRCntSearch(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("bno").descending());
        repository.searchWithRCnt(null, null, pageable);

    }

    @Test
    public void testListWithRCntSearch2(){

        log.info(repository.searchDTORCnt(new PageRequestDTO(1, 10, "t", "8")));

    }

    @Test
    public void readTest2(){

        log.info(repository.readBoard(102L));

    }

}
