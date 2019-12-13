package br.com.fernando.codeblog.service;

import br.com.fernando.codeblog.model.Post;

import java.util.List;

public interface BlogService {
    List<Post> findAll();
    Post findById(long id);
    Post save(Post post);
}
