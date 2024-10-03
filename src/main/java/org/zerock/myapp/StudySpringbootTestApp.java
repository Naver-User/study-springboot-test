package org.zerock.myapp;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.util.Arrays;


@Slf4j
@NoArgsConstructor

// @WebServlet, @WebListener, @WebFilter 등의 어노테이션 기반의
// 표준 Servlet Components 를 찾아 자동등록하기 위해서,
// 우리의 기본 Top-level 패키지부터 시작해서, 모든 하위패키지까지의
// 모든 클래스를 Scanning 해서 Web Servlet Component 로 자동등록시키는 어노테이션입니다.
@ServletComponentScan

@SpringBootApplication
public class StudySpringbootTestApp extends ServletInitializer {

	public static void main(String[] args) {
		// 1st. method
		SpringApplication.run(StudySpringbootTestApp.class, args);

		// 2nd. method
//		SpringApplication app = new SpringApplication(StudySpringbootTestApp.class);
//
//		// 스프링부트의 설정이 최우선적으로 적용됩니다.
//		app.setWebApplicationType(WebApplicationType.SERVLET);	// No WAS Run.
//		app.setBannerMode(Banner.Mode.CONSOLE);	// 배너활성화 설정
//
//		app.run(args);

		log.trace("main({}) invoked.", Arrays.toString(args));
	} // main

} // end class


