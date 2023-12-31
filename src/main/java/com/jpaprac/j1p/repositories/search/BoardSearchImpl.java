package com.jpaprac.j1p.repositories.search;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.jpaprac.j1p.domain.Board;
import com.jpaprac.j1p.domain.QBoard;
import com.jpaprac.j1p.domain.QReply;
import com.jpaprac.j1p.dto.board.BoardListRCntDTO;
import com.jpaprac.j1p.dto.page.PageRequestDTO;
import com.jpaprac.j1p.dto.page.PageResponseDTO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class BoardSearchImpl extends QuerydslRepositorySupport implements BoardSearch {
    
    public BoardSearchImpl() {
        super(Board.class);
    }

    @Override
    public Page<Board> search1(String searchType, String keyword) {

        QBoard board = QBoard.board;

        JPQLQuery<Board> query = from(board);

        if(keyword != null && searchType != null) {

            String[] searchArr = searchType.split("");

            BooleanBuilder searchBuilder = new BooleanBuilder();

            for(String type : searchArr) {

                switch (type) {
                    case "t" -> searchBuilder.or(board.title.contains(keyword));
                    case "c" -> searchBuilder.or(board.content.contains(keyword));
                    case "w" -> searchBuilder.or(board.writer.contains(keyword));
                }
            }

            query.where(searchBuilder);

        }

        List<Board> list = query.fetch();
        long count = query.fetchCount();

        log.info(list);
        log.info(count);

        return null;

    }

    @Override
    public Page<Object[]> searchWithRCnt(String searchType, String keyword, Pageable pageable) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply1;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));
        query.groupBy(board);

        JPQLQuery<Tuple> tuplQuery = query.select(board.bno, board.title, board.writer, reply.countDistinct());
        List<Tuple> tuples = tuplQuery.fetch();
        log.info(tuples);

        long count = tuplQuery.fetchCount();
        log.info("count: " + count);

        return null;

    }

    @Override
    public PageResponseDTO<BoardListRCntDTO> searchDTORCnt(PageRequestDTO dto) {

        QBoard board = QBoard.board;
        QReply reply = QReply.reply1;

        JPQLQuery<Board> query = from(board);
        query.leftJoin(reply).on(reply.board.eq(board));

        String keyword = dto.getKeyword();
        String type = dto.getType();

        if(keyword != null && type != null){

            String[] types = type.split("");

            // where에 해당하는 부분
            BooleanBuilder searchBuilder = new BooleanBuilder();

            for (String eachType : types) {
                
                switch (eachType) {
                    case "t" -> searchBuilder.or(board.title.contains(keyword));
                    case "c" -> searchBuilder.or(board.content.contains(keyword));
                    case "w" -> searchBuilder.or(board.writer.contains(keyword));
                }
            }
            query.where(searchBuilder);
        }

        this.getQuerydsl().applyPagination(makePageable(dto), query);
        query.groupBy(board);

        JPQLQuery<BoardListRCntDTO> listQuery = query.select
            (Projections.bean(BoardListRCntDTO.class, board.bno, board.title, board.writer, reply.countDistinct().as("rcnt")));

        List<BoardListRCntDTO> list = listQuery.fetch();
        long totalCount = listQuery.fetchCount();

        return new PageResponseDTO<>(list, totalCount, dto);

    }
    
}
