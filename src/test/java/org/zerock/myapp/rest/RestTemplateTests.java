package org.zerock.myapp.rest;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.web.client.RestTemplate;
import org.zerock.myapp.domain.BoardVO;

import java.util.List;
import java.util.Objects;

//@Slf4j
@Log4j2

@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RestTemplateTests {

    // @RestController 테스트 전용 빈을 스프링이 제공합니다.
    // 그 빈의 타입은 2가지가 있습니다:
    //  (1) TestRestTemplate    - for HTTP, but *Not HTTPs
    //  (2) RestTemplate        - for HTTPs, but *Not HTTP
    @Autowired(required = false)
    private TestRestTemplate testRestTemplate;  // 주입성공

    @Autowired(required = false)
    private RestTemplate restTemplate;          // 주입실패


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        //  (1) TestRestTemplate    - for HTTP, but *Not HTTPs
        Objects.requireNonNull(this.testRestTemplate);
        log.info("\t+ this.testRestTemplate: {}", testRestTemplate);

        //  (2) RestTemplate        - for HTTPs, but *Not HTTP
//        Objects.requireNonNull(this.restTemplate);
//        log.info("\t+ this.restTemplate: {}", restTemplate);
    } // beforeAll


    @Order(1)
    @Test
    void testHelloWithHTTP() {
        log.trace("testHelloWithHTTP() invoked.");

        // TestRestTemplate 의 사용법을 배우자!
//        this.testRestTemplate.getForObject(String uri, 응답객체의타입정보);
        String response =
            this.testRestTemplate.<String>getForObject("/hello?name=Yoseph", String.class);

        Objects.requireNonNull(response);
        log.info("\t+ response: {}", response);
    } // testHelloWithHTTP


    @Order(2)
    @Test
    void testFindBoardWithHTTP() {
        log.trace("testFindBoardWithHTTP() invoked.");

        // TestRestTemplate 의 사용법을 배우자!
//        this.testRestTemplate.getForObject(String uri, 응답객체의타입정보);
        BoardVO response =
                this.testRestTemplate.<BoardVO>getForObject("/findBoard?seq=111", BoardVO.class);

        Objects.requireNonNull(response);
        log.info("\t+ response: {}", response);
    } // testFindBoardWithHTTP


    @Order(3)
    @Test
    void testFindAllBoardsWithHTTP() {
        log.trace("testFindAllBoardsWithHTTP() invoked.");

        List results =
            this.testRestTemplate.<List>getForObject("/findAllBoards", List.class);

        Objects.requireNonNull(results);
        log.info("\t+ results: {}", results);
    } // testFindAllBoardsWithHTTP


} // end class


