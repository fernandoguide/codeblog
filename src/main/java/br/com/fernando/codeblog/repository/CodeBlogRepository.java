package br.com.fernando.codeblog.repository;

import br.com.fernando.codeblog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeBlogRepository extends JpaRepository<Post,Long> {
}
