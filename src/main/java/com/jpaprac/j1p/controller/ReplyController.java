package com.jpaprac.j1p.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.dto.reply.ReplyDTO;
import com.jpaprac.j1p.dto.reply.ReplyPageReqeustDTO;
import com.jpaprac.j1p.service.reply.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/replies")
public class ReplyController {
    
    private final ReplyService replyService;

    @GetMapping("/{bno}/list")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable Long bno, ReplyPageReqeustDTO dto){

        return replyService.list(dto);

    }

    // 등록
    @PostMapping("/")
    public Map<String, String> register(@RequestBody ReplyDTO dto){

        log.info("controller............... " + dto);

        replyService.registerReply(dto);

        return Map.of("result", "success");

    }

    // 조회
    @GetMapping("/{rno}")
    public ReplyDTO read(@PathVariable Long rno){

        return replyService.read(rno);

    }

}
