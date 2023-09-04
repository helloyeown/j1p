package com.jpaprac.j1p.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "t_reply")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "board")
@Getter
public class Reply {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    private String reply;
    private String replyFile;
    private String replyer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    public void changeReply(String reply){
        this.reply = reply;
    }

    public void changeFile(String fileName){
        this.replyFile = fileName;
    }

}
