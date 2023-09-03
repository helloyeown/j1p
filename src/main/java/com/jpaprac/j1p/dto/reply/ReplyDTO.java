package com.jpaprac.j1p.dto.reply;

import lombok.Builder;
import lombok.Data;

@Data
public class ReplyDTO {
    
    private Long rno;
    private String reply;
    private String replyer;
    private String replyFile;
    private Long bno;

}
