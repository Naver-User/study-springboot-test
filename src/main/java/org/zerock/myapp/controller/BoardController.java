package org.zerock.myapp.controller;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.myapp.domain.BoardVO;
import org.zerock.myapp.service.BoardService;

import java.util.List;

//@Log4j2
@Slf4j

@NoArgsConstructor

//@Controller
@RestController
public class BoardController {  // POJO
    @Autowired(required = true)     // 반드시 주입되어야 한다!
    private BoardService service;


    @GetMapping("/hello")
    String hello(String name) { // name 매개변수 이름이 곧, 전송파라미터 이름과 같다!!
        log.trace("hello({}) invoked.", name);

        // 비지니스 계층의 서비스 객체를 통해, 요청처리하도록 하고
        // 그 결과데이터를 바로 JSON 으로 변환하여 응답전송
        return this.service.hello(name);
    } // hello

    @GetMapping("/findBoard")
    BoardVO findBoard(Integer seq) {    // 하나의 게시글조회 및 반환
        log.trace("findBoard({}) invoked.", seq);
        return this.service.findBoard(seq);
    } // findBoard

    @GetMapping("/findAllBoards")
    List<BoardVO> findAllBoards() { // 전체 게시글목록조회 및 반환
        log.trace("findAllBoards() invoked.");
        return this.service.findAllBoards();
    } // findAllBoards

} // end class
