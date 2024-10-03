package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;


//@Slf4j
@Log4j2

@NoArgsConstructor

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

// 서블릿 컨테이너에서 구동되는 컴포넌트들을 스캔으로 찾아내서
// 자동등록시킨다! (DD에)
@ServletComponentScan

// ================================
// 가상의 MVC 환경을 구성해서, 우리가 만든 컨트롤러,서비스,DAO 컴토넌트를
// 테스트할 수 있는 도구를 제공하는 어노테이션.

// 이 어노테이션에 의해서 아래의 어노테이션이 붙은 클래스를 Spring Context에
// 자동으로 빈으로 등록시켜 놓습니다:
//  (1) @Controller
//  (2) @RestController
//  (3) @Service
//  (4) @Repository
// ================================
@AutoConfigureMockMvc		// ***

// 이 테스트 클래스도 자동으로 빈으로 등록시킴
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)	// 1, Default
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)	// 2
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)	// 3
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)	// 4
class StudySpringbootTestAppTests {
	// Spring Context (== Spring Beans Container) 를 주입받자!
	@Autowired(required = false)	// 선택주입
	WebApplicationContext ctx;


	@BeforeAll
	void beforeAll() {	// 1회성 전처리
		log.trace("beforeAll() invoked.");

		Objects.requireNonNull(this.ctx);
		log.info("\t+ this.ctx: %s".formatted(this.ctx));


	} // beforeAll


//	@Disabled
	@Order(1)
	@Test
//	@RepeatedTest(1)
	@DisplayName("1. testSpringContext")
	@Timeout(value=1L, unit = TimeUnit.MINUTES)
	void testSpringContext() {
		log.trace("testSpringContext() invoked.");

		int totalBeanCount = this.ctx.getBeanDefinitionCount();
		log.info("\t+ totalBeanCount: {}", totalBeanCount);

		String[] beanNames = this.ctx.getBeanDefinitionNames();
		List<String> beanList = Arrays.<String>asList(beanNames);
		beanList.forEach(log::info);
	} // testSpringContext

} // end class

