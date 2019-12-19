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

    @GetMapping(value="/posts")
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
    @DeleteMapping(value="/posts/{id}")
        public ModelAndView excluir(@PathVariable("id") final Long id, final RedirectAttributes attr) {
        this.blogService.excluir(id);
        attr.addFlashAttribute("Excluir","Excluido com Sucesso");
        return new ModelAndView("redirect:/post");
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
//    @Slf4j
//    @Controller
//    @RequestMapping("/cliente")
//    public class ClienteController {
//
//        @Autowired
//        private ClienteService clienteService;
//
//        @RequestMapping("/new")
//        public ModelAndView getIndex(final Cliente cliente) {
//            final ModelAndView mv = new ModelAndView(ConstantsCliente.Cliente.CRIAR);
//            return mv.addObject("cliente", cliente);
//        }
//
//        @RequestMapping(value = "/", method = RequestMethod.POST)
//        public ModelAndView createCliente(@Valid final Cliente clienteNew, final RedirectAttributes attr,
//                                          final Model model) {
//            if (!clienteNew.isValid()) {
//                final ModelAndView mv = new ModelAndView(ConstantsCliente.Cliente.CRIAR);
//                model.addAttribute("erro", "Preencha todos os dados do cliente.");
//                return mv.addObject("cliente", clienteNew);
//            }
//
//            try {
//                this.clienteService.save(clienteNew);
//                attr.addFlashAttribute(ConstantsCliente.Cliente.SUCCESS, ConstantsCliente.Cliente.MENSAGEM_SUCESSO);
//            } catch (Exception e) {
//                attr.addFlashAttribute(ConstantsCliente.Cliente.ERRO, ConstantsCliente.Cliente.ERRO);
//                log.error(e.getMessage());
//                return new ModelAndView(ConstantsCliente.Cliente.NEW);
//            }
//            final List<Cliente> clientes = this.clienteService.getAllCliente();
//            return new ModelAndView(ConstantsCliente.Cliente.REDIRECT_LISTAR).addObject("clientes", clientes);
//        }
//
//        @GetMapping("/editar/{id}")
//        public String preEditar(@PathVariable("id") final Long id, final ModelMap model) {
//            final Cliente cliente = clienteService.findById(id);
//            model.addAttribute("cliente", cliente);
//            return ConstantsCliente.Cliente.CRIAR;
//        }
//
//        @PostMapping("/editar")
//        public ModelAndView editar(@Valid final Cliente cliente, final BindingResult result,
//                                   final RedirectAttributes attr) {
//            if (result.hasErrors()) {
//                attr.addFlashAttribute(ConstantsCliente.Cliente.ERRO, ConstantsCliente.Cliente.EDITAR_ERRO);
//                return new ModelAndView(ConstantsCliente.Cliente.CRIAR);
//            }
//            clienteService.save(cliente);
//            attr.addFlashAttribute(ConstantsCliente.Cliente.SUCCESS, ConstantsCliente.Cliente.EDITAR_SUCESSO);
//            return new ModelAndView(ConstantsCliente.Cliente.REDIRECT_LISTAR);
//        }
//
//        @GetMapping("/listar")
//        public ModelAndView listar() {
//            final ModelAndView mv = new ModelAndView(ConstantsCliente.Cliente.LISTAR);
//            final List<Cliente> clientes = clienteService.getAllCliente();
//            mv.addObject("clientes", clientes);
//            return mv;
//        }
//
//        @GetMapping("/excluir/{id}")
//        public ModelAndView excluir(@PathVariable("id") final Long id, final RedirectAttributes attr) {
//            this.clienteService.excluir(id);
//            attr.addFlashAttribute(ConstantsCliente.Cliente.SUCCESS, ConstantsCliente.Cliente.EXCLUIR_SUCESSO);
//            return new ModelAndView(ConstantsCliente.Cliente.REDIRECT_LISTAR);
//        }
//    }

