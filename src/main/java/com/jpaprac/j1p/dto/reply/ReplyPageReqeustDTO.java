package com.jpaprac.j1p.dto.reply;

import com.jpaprac.j1p.dto.page.PageRequestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyPageReqeustDTO extends PageRequestDTO {
    
    private Long bno;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;

    private boolean last;

}
