package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.repository.CommentsRepository;

import java.util.List;

@Service
public class CommentService {

    private final CommentsRepository commentsRepository;

    public CommentService(CommentsRepository commentsRepository) {
        this.commentsRepository = commentsRepository;
    }

    public List<Comment> findAll() {
        return commentsRepository.findAllByOrderByCreationTimeDesc();
    }
}
