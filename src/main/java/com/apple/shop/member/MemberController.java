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




