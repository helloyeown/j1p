package com.jpaprac.j1p.service.reply;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jpaprac.j1p.domain.Reply;
import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.jpaprac.j1p.dto.reply.ReplyDTO;
import com.jpaprac.j1p.dto.reply.ReplyPageReqeustDTO;
import com.jpaprac.j1p.repositories.ReplyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public PageResponseDTO<ReplyDTO> list(ReplyPageReqeustDTO dto) {

        boolean last = dto.isLast();
        int pageNum = dto.getPage();

        if(last){
            long totalCount = repository.getCountReply(dto.getBno());
            pageNum = (int)(Math.ceil(totalCount / (double)dto.getSize()));
        }

        Pageable pageable = PageRequest.of(pageNum - 1, dto.getSize(), Sort.by("rno").ascending());
        Page<Reply> result = repository.listReply(dto.getBno(), pageable);

        long totalReplyCount = result.getTotalElements();
        List<ReplyDTO> dtoList = result.get().map(entity -> modelMapper.map(entity, ReplyDTO.class)).collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, dto);
        responseDTO.setPage(pageNum);

        return responseDTO;

    }
    
}
