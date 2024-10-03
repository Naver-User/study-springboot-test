package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


//@Log4j2
@Slf4j

@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// Step1. Controller Test 를 위해서는, 자동설정기반의 "가상의 MVC" 구현체가 필요하다!
// 아래의 어노테이션이 가상의 MVC 환경을 자동설정으로 구현하고, 더불어서,
// 단위테스트가 수행될 때, HTTP request 를 보내고, HTTP response를 받을 수 있는 도구를
// 함께 제공합니다.
@AutoConfigureMockMvc

// Step2. 실제 환경에서는 한번 WAS를 구동시키는데에, 상당한 시간이 소요되기 때문에,
//        매번 단위테스트 수행할 때마다, 실제 WAS를 올렸다/내렸다 기다릴 시간이 없다!
//        따라서, 실제 WAS의 구동없이, Mock-up으로 구현된 WAS를 구동시켜야 합니다.
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class BoardControllerTests {
    // Step3. Controller 를 테스트한다고, 개발자가 구현한 @Controller/@RestController
    //        어노테이션을 붙인 빈을 주입받는 필드를 선언해서는 안된다!!!
    //        실제 Controller 테스트에 필요한 것은, HTTP request/response 를 처리할 수
    //        있는 그 무언가가(like 웹브라우저, postman같은) 필요하다!!
    //        이 무언가가 바로, @AutoConfigureMockMvc (위에 선언된) 어노테이션이 제공하는
    //        "MockMvc" 객체를 주입받습니다.
    @Autowired private MockMvc mockMvc;         // ***


    @BeforeAll
    void beforeAll() {
        log.trace("beforeAll() invoked.");

        assertNotNull(this.mockMvc);
        log.info("\t+ this.mockMvc: {}", this.mockMvc);
    } // beforeAll


//    @Disabled
    @Order(1)
    @Test
//    @RepeatedTest(1)
    @DisplayName("1. testHello")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testHello() throws Exception {  // hello() handler test
        log.trace("testHello() invoked.");

        // @AutoConfigureMockMvc 어노테이션이 제공한 MockMvc 객체를 필드에 주입받아서
        // 이 MockMvc 객체가 제공하는 아래와 같은, 주로 import static 형태로 사용되는
        // 여러 메소드를 통해서, 테스트할 Controller의 Handler로 HTTP request를 만들어
        // 보내고, 그 응답으로 HTTP response 를 받아서, 그 결과로 검증을 수행해야 하는것이
        // 바로 @Controller 또는 @RestController 빈에 대한 테스트 방법입니다.

        // MockMvc가 제공하는 대부분의 메소드는 메소드 체이닝 형태이기 때문에,
        // 아래와 같이 가로로 코딩하지 않고, 세로방향으로 코딩합니다.
//        this.mockMvc.perform(get("/hello").param("name", "Yoseph")).andDo(print());
        this.mockMvc
            // 1. HTTP request 를 전송하는 메소드
            .perform(
                // 2. HTTP request 를 생성하는 메소드
                get("/hello")
                    // 3. 전송파라미터를 생성하는 메소드(계속 여러번 수행가능합니다)
                    .param("name", "Yoseph")
                    .param("age", "23")
                    // 4. 아래와 같이 다중값 전송파라미터인 경우는,
                    //    계속 쉼표(,)찍고 값을 나열하면 되는 가변인자로 되어있습니다.
                    .param("tel", "010", "020", "030")
            )
            .andDo(
                // 실제 주고받은 HTTP request/response 뿐만 아니라,
                // HTTP request를 받아 처리한 Controller에 대한 추가정보까지
                // (하다못해, 발생한 예외까지) 한번에 콘솔에 출력하는 메소드
                print()
            )
            .andExpect(status().isOk())
//            .andExpect(status().is(404))
            .andExpect(content().string("Hello, Yoseph"));
    } // testHello


    //    @Disabled
    @Order(1)
    @Test
//    @RepeatedTest(1)
    @DisplayName("2. testFindBoard")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testFindBoard() throws Exception {  // findBoard() handler test
        log.trace("testFindBoard() invoked.");

        String json =
            this.mockMvc
                .perform(
                    get("/findBoard").param("seq", "13")
                )
//                .andDo(print())
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        Objects.requireNonNull(json);
        log.info("\t+ json:\n{}", json);
    } // testFindBoard

} // end class
