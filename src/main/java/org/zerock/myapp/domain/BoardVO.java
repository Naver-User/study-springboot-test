package org.zerock.myapp.domain;

import lombok.Value;

import java.util.Date;


@Value

// 조회한 하나의 게시글 데이터를 읽기전용(Immutable)으로
// 제공하는 Value Object
public class BoardVO {
    Integer seq;    // 게시글번호
    String title;
    String writer;
    String content;
    Integer cnt;    // 조횟수
    Date createDate;


} // end class
