package ru.itmo.wp.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.domain.Comment;
import ru.itmo.wp.domain.Post;
import ru.itmo.wp.domain.Tag;
import ru.itmo.wp.domain.User;
import ru.itmo.wp.form.PostCredentials;
import ru.itmo.wp.repository.PostRepository;
import ru.itmo.wp.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final TagRepository tagRepository;

    public PostService(PostRepository postRepository, TagRepository tagRepository) {
        this.postRepository = postRepository;
        this.tagRepository = tagRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAllByOrderByCreationTimeDesc();
    }

    public Post findById(Long id) {
        return id == null ? null : postRepository.findById(id).orElse(null);
    }

    public void saveComment(long id, User user, String text) {
        Post post = findById(id);
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setText(text);
        post.addComment(comment);
        postRepository.save(post);
    }

    public Post makePost(PostCredentials post) {
        Post post1 = new Post();
        post1.setText(post.getText());
        post1.setTitle(post.getTitle());
        post1.setTags(new ArrayList<Tag>());
        String[] tags = post.getTags().split(" ");
        for (String tag : tags) {
            if (tag != null) {
                Tag tag1 = new Tag();
                tag1.setName(tag);
                if (!tagRepository.existsByName(tag)) {
                    tagRepository.save(tag1);
                }
                post1.addTag(tag1);
            }
        }
        return post1;
    }
}
