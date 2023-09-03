package com.jpaprac.j1p.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.dto.reply.ReplyDTO;
import com.jpaprac.j1p.dto.reply.ReplyPageReqeustDTO;
import com.jpaprac.j1p.service.reply.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyServiceTests {
    
    @Autowired
    private ReplyService replyService;

    @Test
    public void replyListTest(){

        ReplyPageReqeustDTO dto = ReplyPageReqeustDTO.builder()
            .bno(102L)
            .page(1)
            .last(true)
            .build();

        PageResponseDTO<ReplyDTO> list = replyService.list(dto);

        log.info(list);

    }

    // 등록
    @Test
    public void registerTest(){

        // ReplyDTO dto = ReplyDTO.builder()
        //     .reply("tester")
        //     .replyer("user8")
        //     .bno(102L).build();

        // replyService.registerReply(dto);
        
    }

    // 조회
    @Test
    public void readTest(){

        log.info(replyService.read(74L));

    }

}
