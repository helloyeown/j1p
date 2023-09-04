package com.jpaprac.j1p.service.reply;

import java.util.List;
import java.util.Optional;
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
            // last가 true일 경우 전체 페이지 수를 계산해서 pageNum을 끝 페이지로 설정해줌
            pageNum = (int)(Math.ceil(totalCount / (double)dto.getSize()));
            pageNum = pageNum <= 0 ? 1 : pageNum;
        }

        Pageable pageable = PageRequest.of(pageNum - 1, dto.getSize(), Sort.by("rno").ascending());
        Page<Reply> result = repository.listReply(dto.getBno(), pageable);

        long totalReplyCount = result.getTotalElements();
        List<ReplyDTO> dtoList = result.get().map(entity -> modelMapper.map(entity, ReplyDTO.class)).collect(Collectors.toList());

        PageResponseDTO<ReplyDTO> responseDTO = new PageResponseDTO<>(dtoList, totalReplyCount, dto);
        responseDTO.setPage(pageNum);

        return responseDTO;

    }

    @Override
    public void registerReply(ReplyDTO dto) {

        log.info(dto);

        Reply reply = modelMapper.map(dto, Reply.class);
        log.info(reply);

        repository.save(reply);

    }

    @Override
    public ReplyDTO read(Long rno) {

        Optional<Reply> result = repository.findById(rno);
        Reply reply = result.orElseThrow();

        ReplyDTO dto = modelMapper.map(reply, ReplyDTO.class);

        return dto;

    }

    @Override
    public void deleteReply(Long rno) {

        Optional<Reply> result = repository.findById(rno);
        Reply reply = result.orElseThrow();

        reply.changeReply("삭제된 댓글입니다.");
        reply.changeFile(null);

        repository.save(reply);

    }

    @Override
    public void modifyReply(ReplyDTO dto) {

        Optional<Reply> result = repository.findById(dto.getRno());
        Reply reply = result.orElseThrow();

        reply.changeReply(dto.getReply());
        reply.changeFile(dto.getReplyFile());

        repository.save(reply);

    }
    
}
