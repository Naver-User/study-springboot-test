package org.zerock.myapp.service;


import org.zerock.myapp.domain.BoardVO;

import java.util.List;

// 웹 3계층을 구성하는 각 계층은 기본적으로
// 인터페이스 기반으로 구현합니다.
// 즉, 실제 다른 계층이 사용가능한 메소드를 인터페이스의
// 추상메소드로 공개하고, 실제 이 공개인터페이스의 구현타입은
// 공개하지 않도록 구현합니다.
public interface BoardService { // 표현계층에 공개된 인터페이스

    String hello(String name);
    BoardVO findBoard(Integer seq);   // READ
    List<BoardVO> findAllBoards();  // 전체 목록조회


} // end class

