package com.jpaprac.j1p.repositories;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.jpaprac.j1p.domain.Board;
import com.jpaprac.j1p.domain.Reply;
import com.jpaprac.j1p.dto.reply.ReplyDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
    
    @Autowired
    private ReplyRepository repository;

    @Test
    public void insertOne(){

        Board board = Board.builder().bno(1L).build();

        Reply reply = Reply.builder()
            .reply("new reply")
            .replyer("tester")
            .replyFile("testFile.jpg")
            .board(board).build();

        repository.save(reply);

    }

    @Test
    public void insertDummies(){

        Long[] bnoArr = {99L, 96L, 92L, 84L, 81L};

        for (Long bno : bnoArr) {
            Board board = Board.builder().bno(bno).build();

            for(int i=0; i<5; i++){
            
                Reply reply = Reply.builder()
                    .reply("reply.." + bno + "--" + i)
                    .replyer("user" + i)
                    .board(board).build();

                repository.save(reply);
            }
        }
    }

    // 목록(페이징)
    @Test
    public void listTest(){

        Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").ascending());

        Page<Reply> reply = repository.listReply(102L, pageable);

        log.info(reply);
        reply.get().forEach(r -> log.info(r));

    }

    // 댓글 갯수
    @Test
    public void replyCountTest(){

        log.info(repository.getCountReply(99L));

    }

    // 등록
    @Test
    public void registerReplyTest(){

        Board board = Board.builder().bno(102L).build();

        Reply reply = Reply.builder()
            .reply("test")
            .replyer("user")
            .board(board)
            .build();

        repository.save(reply);

    }

    // 조회
    @Test
    public void readTest(){

        Optional<Reply> result = repository.findById(74L);
        Reply reply = result.orElseThrow();

        log.info(reply);

    }

}
