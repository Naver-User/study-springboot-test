package org.zerock.myapp.persistence;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.myapp.domain.BoardDTO;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


//@Log4j2
@Slf4j

@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// 테스트 클래스 자체도 Spring Context 에 빈으로 자동등록됩니다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardRepositoryTests {
    // Step1. 개발자가 테스트 하길 원하는 계층(웹3계층)의 Spring Bean Component를
    //        주입받을 필드를 선언하고, Spring Context 에 주입 시그널을 보내기 위해
    //        @Autowired 같은 어노테이션을 붙여서, 런타임시에 테스트 대상 객체를 확보해야 합니다.
    @Autowired(required = true)     // 반드시 주입
    private BoardRepository dao;    // 테스트 대상 확보


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        Objects.requireNonNull(this.dao);
        log.info("\t+ this.dao: {}", this.dao);
    } // beforeAll


    // Step2. Step1 에서 제대로 주입받은 DAO 에 정의된 각각의 메소드마다
    //        정상적인 기능처리가 가능한지를 검증하는 단위테스트 메소드를
    //        아래와 같이 선언해서, 수행하고 검증합니다.
//    @Disabled
    @Order(1)
    @Test
//    @RepeatedTest(1)
    @DisplayName("1. testInsertBoard")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testInsertBoard() {
        log.trace("testInsertBoard() invoked.");

        // 테스트할 메소드는 BoardDTO를 매개변수로 받기 때문에
        // DTO 객체 생성
        BoardDTO dto = new BoardDTO();
        dto.setTitle("TITLE");
        dto.setContent("CONTENT");
        dto.setWriter("Yoseph");

        int affectedRows = this.dao.insertBoard(dto);      // 테스트할 메소드 수행

        // 기댓값(첫번째 매개변수)과 실제값(두번째 매개변수)의 차이를 검증
        assertEquals(1, affectedRows);
        log.info("\t+ affectedRows: {}", affectedRows);
    } // testInsertBoard

} // end class

