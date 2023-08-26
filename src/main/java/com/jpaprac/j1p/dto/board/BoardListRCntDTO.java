package com.jpaprac.j1p.dto.board;

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
public class BoardListRCntDTO {
    
    private Long bno;
    private String title;
    private String writer;
    private long rcnt;

}
