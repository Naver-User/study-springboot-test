package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.zerock.myapp.domain.BoardDTO;


@Slf4j
@NoArgsConstructor

// 영속성 계층의 DAO 역할을 부여하는 StereoType(역할부여) 어노테이션입니다.
// 이런 스트레오 타입 어노테이션은 부가적으로, 해당 클래스를 자동으로
// Spring Context(==즉, Spring Beans Container)에 자동으로 빈으로 등록합니다.
@Repository
public class BoardRepository {  // DAO, POJO

    // 그런데, 우리의 지금 목표는 테스트 방법을 배우는 것이기 때문에
    // 이미 다 DAO 메소드를 구현했다라고 가정합니다.
    int insertBoard(BoardDTO dto) {
        log.trace("insertBoard({}) invoked.", dto);

        return 1;   // 제대로 INSERT 된 행의 개수 반환
    } // insertBoard

} // end class
