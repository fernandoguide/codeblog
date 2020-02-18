package br.com.fernando.codeblog.controller;


import br.com.fernando.codeblog.model.Post;
import br.com.fernando.codeblog.repository.BlogRepository;
import br.com.fernando.codeblog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@Controller
public class BlogController {

    @Autowired
    private BlogService blogService;
    @Autowired
    private BlogRepository repository;

    @GetMapping
    public String index(){
        return "index";
    }

    @GetMapping(value = "/posts")
    public ModelAndView getPosts(){
        ModelAndView mv = new ModelAndView("posts");
        List<Post> posts = blogService.findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @GetMapping(value="/posts/{id}")
    public ModelAndView getPostDetails(@PathVariable("id") long id){
        ModelAndView mv = new ModelAndView("postDetails");
        Post post = blogService.findById(id);
        mv.addObject("post", post);
        return mv;
    }
    @DeleteMapping(value = "/posts/{id}")
        public ModelAndView excluir(@PathVariable("id") final Long id, final RedirectAttributes attr) {
        this.blogService.excluir(id);
        attr.addFlashAttribute("Excluir","Excluido com Sucesso");
        return this.getPosts();
    }

    @GetMapping(value="/newpost")
    public String getPostForm(){
        return "postForm";
    }

    @PostMapping(value="/newpost")
    public String savePost(@Valid Post post, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return "redirect:/newpost";
        }
        post.setData(LocalDate.now());
        blogService.save(post);
        return "redirect:/posts";
    }
}

