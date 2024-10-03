package org.zerock.myapp.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Slf4j
@NoArgsConstructor

@Controller
public class SampleController {

    @PostMapping(path="/login", params={"userid", "userpw"})
    String authenticate(String userid, String userpw, Model model) {
        log.trace("authenticate({}, {}) invoked.", userid, userpw);

        // 모델 데이터 저장 및 전송
        model.addAttribute("userid", userid);
        model.addAttribute("userpw", userpw);

        return "authOK";    // View 이름 반환
    } // authenticate


    // 다음 시간에 리다이렉션 검증 방법을 알아봅시다!!
    @GetMapping("/redirect")
    String redirect(RedirectAttributes rttrs) {
        log.trace("redirect() invoked.");

        // 아래의 코드는 결국 ?KEY1=VALUE1&KEY2=VALUE2 란 Query String을 만들어 내서,
        // 실제 Target URI (아래 /XXX)로 리다이렉션 수행될 때, 같이 전송되는
        // 전송파라미터를 만들어 냅니다.
        rttrs.addAttribute("KEY1", "VALUE1");
        rttrs.addAttribute("KEY2", "VALUE2");

        // 리다이렉션 응답코드는 302 으로 클라이언트에 전송됩니다!!!
        return "redirect:/XXX"; // /XXX 로 재요청 보내라!
    } // redirect

} // end class
