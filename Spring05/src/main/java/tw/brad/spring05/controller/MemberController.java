package tw.brad.spring05.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import tw.brad.spring05.dto.MemberForm;
import tw.brad.spring05.entity.Member;
import tw.brad.spring05.service.MemberService;

@Controller
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@ModelAttribute MemberForm memberForm, Model model) {
        try {
            memberService.register(memberForm);
            return "redirect:/member/login";
        } catch (Exception e) {
            System.out.println(e);
            model.addAttribute("error", "Accout already exist");
            return "register";
        }
    }

    @GetMapping("/login")
    public String login() {
        
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String email, @RequestParam String pwd, HttpSession session) {
        Member member = memberService.login(email, pwd);
        if (member != null) {
            session.setAttribute("member", member);
            return "redirect:/member/home";
        }

        return "login";
    }
}
