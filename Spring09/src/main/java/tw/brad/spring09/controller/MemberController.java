package tw.brad.spring09.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class MemberController {

    @RequestMapping("/test")
    public String test1() {
        return "test1";
    }
}
