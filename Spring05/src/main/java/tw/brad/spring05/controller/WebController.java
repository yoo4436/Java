package tw.brad.spring05.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import tw.brad.spring05.test.User;
import tw.brad.spring05.test.UserForm;

/*
request => Controller -> Model 網頁所需要的資料
                       > View (Resolver) Thymeleaf -> HTML
                       > Response

request => RestController
        -> return String(Web Page Content) or ResponseEntity.ok(物件)

*/

// @RestController
@Controller
@RequestMapping("/")
public class WebController {

    /*
    ThymeleafViewResolver: prefix + viewName + suffix
    spring.thymeleaf.prefix=classpath:/templates/
    spring.thymeleaf.suffix=.html
    */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/member/index")
    public String memberIndex() {
        return "member/index";
    }

    @RequestMapping("/page1")
    public String page1(Model model) {
        model.addAttribute("companyName", "Big Brad Company");
        model.addAttribute("userName", "Brad");
        
        User user = new User();
        user.setName("Brad");
        user.setGender(true);
        user.setAge(18);
        model.addAttribute("user", user);

        System.out.println(user);

        
        model.addAttribute("now", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        return "page1";
    }

    @RequestMapping("/page2/{status}")
    public String page2(Model model, @PathVariable String status) {
        model.addAttribute("status", status);
        return "page2";
    }

    @GetMapping("/page3")
    public String page3(Model model) {
        UserForm uf = new UserForm();
        uf.setEmail("輸入Email");
        model.addAttribute("userForm", uf);


        return "page3";
    }

    @PostMapping("/page3")
    public String page4(
                Model model,
                @ModelAttribute @Valid UserForm uf,
                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "page3";
        }

        System.out.println(uf.getEmail());
        System.out.println(uf.getPwd());
        System.out.println(uf.getName());

        return "page4";
    }

    @RequestMapping("/page5")
    public String page5() {
        
        
        return "page5"; 
    }
}
