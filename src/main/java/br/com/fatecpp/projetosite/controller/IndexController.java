package br.com.fatecpp.projetosite.controller;

import br.com.fatecpp.projetosite.entidades.Associado;
import br.com.fatecpp.projetosite.entidades.Noticias;
import br.com.fatecpp.projetosite.entidades.Usuario;
import br.com.fatecpp.projetosite.repository.AssociadoRepository;
import br.com.fatecpp.projetosite.repository.NoticiasRepository;
import br.com.fatecpp.projetosite.repository.UsuarioRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class IndexController {
   
        @Autowired
    private  NoticiasRepository notRepo;
    
    @Autowired
    private UsuarioRepository usuRepo;
    
    @Autowired
    private AssociadoRepository assRepo;
    
    @RequestMapping("/")
    public String paginaPrincipal(){
        return "/index2";
    }
    
    @RequestMapping("/sobre")
    public String sobreabout(){
        return "/sobre";
    }
    @RequestMapping("/participe")
    public String partic(){
            return "/participe";
    }
    
      @RequestMapping("/aprenda")
    public String aprenda(){
            return "/aprenda";
    }
    
       @RequestMapping("/login")
    public String login(){
            return "/login";
    }
       @RequestMapping("/Dashboard")
       public String dash(){
           return "/Dashboard";
       }
    
         @RequestMapping("/post")
       public String posta(){
           return "/post";
       }
             @RequestMapping("/logout")
       public String sair(){
           return "/index2";
       }
  @RequestMapping(value = "/post", method = RequestMethod.GET)
    public ModelAndView listarpost() {
        ModelAndView mv = new ModelAndView("/post");
        try {
            Iterable<Usuario> usuarios = usuRepo.findAll();
            Iterable<Noticias> noticias = notRepo.findAll();
            mv.addObject("noticias", noticias);
            mv.addObject("usuario", usuarios);
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível listar os registros!");
        }
        return mv;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView listarNoticias() {
        ModelAndView mv = new ModelAndView("/index2");
        try {
            Iterable<Usuario> usuarios = usuRepo.findAll();
            Iterable<Noticias> noticias = notRepo.findAll();
            mv.addObject("noticias", noticias);
            mv.addObject("usuario", usuarios);
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível listar os registros!");
        }
        return mv;
    }
     @RequestMapping(value = "/participe", method = RequestMethod.POST)
    public String novoAssociado(@Valid Associado associado, BindingResult results, RedirectAttributes attributes) {
        if (results.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Preencha corretamente os campos!");
        } else {
            try {
                assRepo.save(associado);
                attributes.addFlashAttribute("mensagemSucesso", "Dados registrados com sucesso!");
            } catch (Exception e) {
                attributes.addFlashAttribute("mensagemErro", "Não foi possível inserir o registro");
            }
        }
        return "redirect:/participe";
    }
    
     @RequestMapping(value = "/participe", method = RequestMethod.GET)
    public ModelAndView listarAssociados() {
        ModelAndView mv = new ModelAndView("/participe");
        try {
            Iterable<Associado> associados = assRepo.findAll();
            
            mv.addObject("associado", associados);
            
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível listar os registros!");
        }
        return mv;
    }
    
}
