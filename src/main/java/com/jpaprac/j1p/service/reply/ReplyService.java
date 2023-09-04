package com.jpaprac.j1p.service.reply;

import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.dto.reply.ReplyDTO;
import com.jpaprac.j1p.dto.reply.ReplyPageReqeustDTO;

import jakarta.transaction.Transactional;

@Transactional
public interface ReplyService {
    
    PageResponseDTO<ReplyDTO> list(ReplyPageReqeustDTO dto);

    void registerReply(ReplyDTO dto);

    ReplyDTO read(Long rno);

    void deleteReply(Long rno);

    void modifyReply(ReplyDTO dto);

}
