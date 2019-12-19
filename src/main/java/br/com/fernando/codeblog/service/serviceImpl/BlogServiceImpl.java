package br.com.fernando.codeblog.service.serviceImpl;


import br.com.fernando.codeblog.model.Post;
import br.com.fernando.codeblog.repository.BlogRepository;
import br.com.fernando.codeblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl  implements BlogService {

    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Post> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Post findById(long id) {
        return blogRepository.findById(id).get();
    }

    @Override
    public Post save(Post post) {
        return blogRepository.save(post);
    }

    @Transactional
    public void excluir(final Long id) {
        final Post post = this.blogRepository.getOne(id);
        this.blogRepository.delete(post);
    }


//    private Sort sortByIdAsc() {
//        return new Sort(Sort.Direction.DESC,"id");
//    }
}
