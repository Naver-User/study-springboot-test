package org.zerock.myapp.service;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.zerock.myapp.domain.BoardVO;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@Log4j2
//@Slf4j

@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@ServletComponentScan

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardServiceTests {
    // Step1. 테스트 대상을 필드로 선언하고
    //        주입시그널로 주입을 받습니다.
    @Autowired private BoardService service;


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        Objects.requireNonNull(this.service);
        log.info("\t+ this.service: {}", this.service);
    } // beforeAll


    // Step2. Step1 에서 주입받은 테스트 대상이 가지고 있는 각각의 메소드에 대해서
    //        단위테스트 메소드를 선언하고, 검증을 수행

//    @Disabled
    @Order(1)
    @Test
//    @RepeatedTest(1)
    @DisplayName("1. testHello")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testHello() {
        log.trace("testHello() invoked.");

        final String name = "Yoseph";
        String result = this.service.hello(name);   // 실제값

        // 기댓값이 실제값과 같은지 비교검증 수행
        String expected = "Hello, Yoseph";          // 기대값
        assertEquals(expected, result);
        log.info("\t+ result: {}", result);
    } // testHello


    //    @Disabled
    @Order(2)
    @Test
//    @RepeatedTest(1)
    @DisplayName("2. testFindBoard")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testFindBoard() {
        log.trace("testFindBoard() invoked.");

        // 조회할 게시글 번호
        final Integer boardSeq = 33;

        BoardVO vo = this.service.findBoard(boardSeq);

        // 적어도 우리가 준 게시글 번호에 해당하는 글이 있다면,
        // 최소한 실제값이 null 은 아니어야 하기 때문에, 널 검증시도
        assertNotNull(vo);
        log.info("\t+ vo: {}", vo);

        // 테이블의 한 개의 튜플을 PK 값으로 조회하는 기능을 수행하는 메소드에 대한
        // 검증방법은, 테스트로 준 PK 값과 실제값의 PK 값이 같은지 비교해보는것이다!!
        assertEquals(boardSeq, vo.getSeq());
    } // testFindBoard


    //    @Disabled
    @Order(3)
    @Test
//    @RepeatedTest(1)
    @DisplayName("3. testFindAllBoards")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testFindAllBoards() {  // 전체 게시글 목록조회 검증
        log.trace("testFindAllBoards() invoked.");

        List<BoardVO> result = this.service.findAllBoards();

        assertNotNull(result);
        result.forEach(log::info);
    } // testFindAllBoards

} // end class


