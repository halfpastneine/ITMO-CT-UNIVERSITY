package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.form.CommentCredentials;
import ru.itmo.wp.security.Guest;
import ru.itmo.wp.service.CommentService;
import ru.itmo.wp.service.PostService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class PostPage extends Page {

    PostService postService;
    CommentService commentService;

    public PostPage(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @Guest
    @GetMapping({"/post/{id}", "/post/"})
    public String postGet(@PathVariable(required = false) String id, Model model) {
        try {
            long postId = Long.parseLong(id);
            model.addAttribute("post", postService.findById(postId));
            model.addAttribute("comment", new Comment());
            model.addAttribute("posts", commentService.findAll());
        } catch (NumberFormatException e) {
            return "PostPage";
        }
        return "PostPage";
    }

    @Guest
    @PostMapping({"/post/{id}", "/post/"})
    public String makeCommentPost(
            @Valid @ModelAttribute CommentCredentials commentCredentials,
            BindingResult bindingResult,
            @PathVariable long id,
            HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "redirect:/post/" + id;
        }
        postService.saveComment(id, getUser(httpSession), commentCredentials.getText());
        putMessage(httpSession, "Your comment was published");
        return "redirect:/post/" + id;
    }
}
