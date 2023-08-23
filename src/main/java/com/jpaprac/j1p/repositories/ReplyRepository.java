package com.jpaprac.j1p.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpaprac.j1p.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    
    // JPQL
    @Query("select r From Reply r where r.board.bno = :bno")
    Page<Reply> listReply(@Param("bno") Long bno, Pageable pageable);

}
