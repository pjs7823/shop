package com.apple.shop.member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Authentication auth) {
        if (auth != null && auth.isAuthenticated()){
            return "redirect:/list";
        }
        return "register";
    }

    @PostMapping("/member")
    public String addMember(String username, String password, String displayName){
        Member member = new Member();
        member.setUsername(username);
        member.setPassword(passwordEncoder.encode(password)); // 비밀번호는 hashing 해서 저장
        member.setDisplayName(displayName);
        memberRepository.save(member);
        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        var result = memberRepository.findByUsername("admin");
        return "login";
    }

    @GetMapping("/my-page")
    public String myPage(Authentication auth) {
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);
        return "mypage";
    }

    @GetMapping("/debug")
    public String debug(Authentication auth, HttpSession session) {
        System.out.println("Authentication: " + auth);
        System.out.println("Session ID: " + session.getId());
        return "debug";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public MemberDto getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        var data = new MemberDto(result.getUsername(),result.getDisplayName());
        return data;
    }
}

class MemberDto {
    // object 안에 있는 데이터를 변환해서 전송하려면 Map 자료형을 사용하거나 이처럼 DTO 클래스를 만들어서 사용한다.
    // 보통 별도 파일에 public 클래스를 만들어서 사용한다.
    // Map 자료형보다 장점-> 타입체크가 쉬움, 재사용 용이
    // 요즘에는 Mapstruct 같은 Mapping 라이브러리를 사용하기도함
    public String username;
    public String displayName;

    MemberDto(String username, String displayName) {
        this.username = username;
        this.displayName = displayName;
    }
}


