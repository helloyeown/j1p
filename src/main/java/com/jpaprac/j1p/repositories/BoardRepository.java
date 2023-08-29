package com.jpaprac.j1p.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpaprac.j1p.domain.Board;
import com.jpaprac.j1p.dto.board.BoardReadDTO;
import com.jpaprac.j1p.repositories.search.BoardSearch;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardSearch {
    
    @Modifying
    @Query("update Board b set b.title = :title where b.bno = :bno")
    int modifyTitle(@Param("title") String title, @Param("bno") Long bno);

    List<Board> findByTitleContaining(String title);

    Page<Board> findByContentContaining(String content, Pageable pageable);

    @Query("select b from Board b where b.title = :title")
    List<Board> listTitle(@Param("title") String title);

    // Object 배열로 반환하여 원하는 컬럼만 추출
    @Query("select b.bno, b.title from Board b where b.title like %:title%")
    Page<Object[]> listTitle2(@Param("title") String title, Pageable pageable);

    // native query
    @Query(value = "select * from t_board", nativeQuery = true)
    List<Object[]> listNative();    // JPA와 관련된 객체 매핑을 수행하지 않음

    @Query("select b.bno, b.title, b.writer, count(r) from Board b left outer join Reply r on r.board = b group by b order by b.bno desc")
    List<Object[]> getListWithRCnt();

    @Query("select b from Board b where b.bno = :bno")
    BoardReadDTO readBoard(@Param("bno") Long bno);

}
