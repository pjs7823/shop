package com.apple.shop.comment;

import com.apple.shop.member.CustomUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void saveComment(String content, Long parent, CustomUser user) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUsername(user.getUsername());
        comment.setParentId(parent);
        commentRepository.save(comment);
    }
}
