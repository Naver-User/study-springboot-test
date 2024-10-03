package org.zerock.myapp.service;


// 각 계층의 인터페이스에 대한 구현클래스는 반드시
// 기본생성자를 제공하여야 합니다. 그래야,
// 객체생성을 쉽게 해줘야, 계층간 연동시에도 그렇고
// 이들 클래스를 관리해야 하는 스프링 컨텍스트(즉,
// 스프링 빈즈 컨테이너 입장에서도 유리합니다.)

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.persistence.BoardRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;


//@Log4j2
@Slf4j

@NoArgsConstructor

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired(required = true)     // 필수주입
    private BoardRepository dao;


    @Override
    public String hello(String name) {
        log.trace("hello({}) invoked.", name);
        return "Hello, %s".formatted(name);
    } // hello

    @Override
    public BoardVO findBoard(Integer seq) {
        log.trace("findBoard({}) invoked.", seq);
        return new BoardVO(1, "TITLE_1", "WRITER_1", "CONTENT_1", 0, new Date());
    } // findBoard

    @Override
    public List<BoardVO> findAllBoards() {
        List<BoardVO> list = new Vector<>(10);

        IntStream.rangeClosed(1, 10).forEachOrdered(seq -> {
            BoardVO vo =
                new BoardVO(seq, "TITLE_"+seq, "WRITER_"+seq, "CONTENT_"+seq, seq, new Date());

            list.add(vo);
        }); // .forEachOrdered

        return list;
    } // findAllBoards

} // end class
