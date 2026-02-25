package tw.brad.spring08.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tw.brad.spring08.dto.Login;
import tw.brad.spring08.entity.Member;
import tw.brad.spring08.repo.MemberRepo;
import tw.brad.spring08.response.LoginResponse;
import tw.brad.spring08.util.BCrypt;
import tw.brad.spring08.util.JwtToken;

@RequestMapping("/auth")
@RestController
public class AuthorController {

    @Autowired
    private MemberRepo memberRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        Member member = memberRepo.findByEmail(login.getEmail()).orElse(null);
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Account Error");
        }
        if (!BCrypt.checkpw(login.getPw(), member.getPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pw Error");
        }
        String token = JwtToken.createToken(member.getEmail());

        return ResponseEntity.ok(new LoginResponse(token, login.getEmail()));
    }
}
