package com.apple.shop.comment;

import com.apple.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/comment")
    String postComment(@RequestParam String content,@RequestParam Long parent,Authentication auth) {
        CustomUser user =(CustomUser) auth.getPrincipal();
        commentService.saveComment(content,parent, user);
        return "redirect:/list/page/1";
    }
}
