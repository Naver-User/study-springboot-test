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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


//@Log4j2
@Slf4j

@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

@AutoConfigureMockMvc

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class SampleControllerTests {
    @Autowired private MockMvc mockMvc;


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
    @DisplayName("1. testAuthenticate")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testAuthenticate() throws Exception {
        log.trace("testAuthenticate() invoked.");

        this.mockMvc
            .perform(
                post("/login")
                    .param("userid", "trinity")
                    .param("userpw", "1234")
            )
            .andDo(print())
            .andExpect(status().is(200))
            // 핸들러가 반환환 뷰의 이름 검증
            .andExpect(view().name("authOK"))
            // 핸들러가 반환환 모델상자안에 저장된 각 모델이 Key가 있는지 확인
            // 이때, attributeExists(가변인자)는
            // "userid"와 "userpw"란 2개 이름의 모델속성이 있는지 검증하는 메소드
            .andExpect(model().attributeExists("userid", "userpw"))
            // 모델 상자 안에 각 모델속성의 이름과 값이, 지정된 것과 일치하는지 검증하는 메소드
            .andExpect(model().attribute("userid", "trinity"))
            .andExpect(model().attribute("userpw", "1234"));
    } // testHello


    //    @Disabled
    @Order(2)
    @Test
//    @RepeatedTest(1)
    @DisplayName("2. testRedirect")
    @Timeout(value=1L, unit = TimeUnit.SECONDS)
    void testRedirect() throws Exception {
        log.trace("testRedirect() invoked.");

        this.mockMvc
            .perform(get("/redirect"))
            .andDo(print())                 // 이 코드는 무조건 수행하라!
            // 리다이렉션 응답코드(302)가 발생했는지 검증
            .andExpect(status().is(302))
            // 리다이렉트 Target URI과 더불어, RTTRS(임시상자)를 사용했다면
            // 추가로 함께 전송할 전송파라미터로 구성된 QueryString도 함께 검증해야
            // 합니다.
            .andExpect(redirectedUrl("/XXX?KEY1=VALUE1&KEY2=VALUE2"));
    } // testRedirect

} // end class
