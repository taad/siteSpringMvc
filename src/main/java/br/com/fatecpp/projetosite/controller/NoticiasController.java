package br.com.fatecpp.projetosite.controller;

import br.com.fatecpp.projetosite.entidades.Noticias;
import br.com.fatecpp.projetosite.entidades.Usuario;
import br.com.fatecpp.projetosite.repository.NoticiasRepository;
import br.com.fatecpp.projetosite.repository.UsuarioRepository;
import java.util.Set;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class NoticiasController {

    @Autowired
    private  NoticiasRepository notRepo;
    
    @Autowired
    private UsuarioRepository usuRepo;

    @RequestMapping(value = "/NovaNoticia", method = RequestMethod.GET)
    public ModelAndView formularioInserirNoticia() {
        
        ModelAndView mv = new ModelAndView("/NovaNoticia");
        try {
            
            Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuarios);
        
        } catch (Exception e) {
            System.out.println("ERROR: "+e);
        }
        
        return mv;
    }
    
    /*@RequestMapping(value = "/NovaNoticia", method = RequestMethod.POST)
    public String novaNoticia(@Valid Noticias noticias , BindingResult results, RedirectAttributes attributes) {
        if (results.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Preencha corretamente os campos!");
        } else {
            try {
                notRepo.save(noticias);
                attributes.addFlashAttribute("mensagemSucesso", "Dados registrados com sucesso!");
            } catch (Exception e) {
                attributes.addFlashAttribute("mensagemErro", "Não foi possível inserir o registro");
            }
        }
        return "redirect:/NovaNoticia";
    }
    */
    @RequestMapping(value ="/NovaNoticia", method=RequestMethod.POST)
public ModelAndView inserirNoticia(@Valid Noticias noticias,
   @RequestParam("usuario") String usuario,
           
           BindingResult results){
    ModelAndView mv = new ModelAndView("NovaNoticia");
    if(results.hasErrors()){
       mv.addObject("mensagem","Erro no formulario");
        }else{
        Usuario usuario1 = usuRepo.findById(Integer.parseInt(usuario));
        noticias.setUsuarioId(usuario1);
        
       
        notRepo.save(noticias);
        mv.addObject("mesagem","Dados Salvos");
        Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuarios);
            
        
    }
    return mv;
}
    
    
    
    
    
    @RequestMapping(value = "/alterarnoticias/{id}", method = RequestMethod.GET)
    public ModelAndView redirecionarAlterarNoticias(@PathVariable int id) {
        return formularioAlterarNoticias(id);
    }
    
       @RequestMapping(value = "/excluirnoticias/{id}", method = RequestMethod.GET)
    public ModelAndView redirecionarExcluirNoticias(@PathVariable int id) {
        return formularioExcluirNoticias(id);
    }
   
    @RequestMapping(value = "/AlterarNoticias", method = RequestMethod.GET)
    public String redirecionarAlterarNoticias() {
        return "/ListarNoticias";
    }
    
    

    
    
    public ModelAndView formularioAlterarNoticias(int id) {
        ModelAndView mv = new ModelAndView("/AlterarNoticias");
        try {
            
            
             Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuarios);
            Noticias noticias = notRepo.findById(id);
            mv.addObject("noticias", noticias);
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }
  
    
    
    
    @RequestMapping(value = "AlterarNoticias", method = RequestMethod.POST)
    public ModelAndView alterarNoticias(@Valid Noticias noticias, 
     @RequestParam("usuario") String usuario,
           
           BindingResult results){
    ModelAndView mv = new ModelAndView("ListarNoticias");
    if(results.hasErrors()){
       mv.addObject("mensagem","Erro no formulario");
        }else{
        Usuario usuario1 = usuRepo.findById(Integer.parseInt(usuario));
        noticias.setUsuarioId(usuario1);
        
       
        notRepo.save(noticias);
        mv.addObject("mesagem","Dados Salvos");
        Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuarios);
            
        
    }
    return mv;
    }
    
    
    
    
    /*@RequestMapping(value = "/AlterarNoticias", method = RequestMethod.POST)
    public ModelAndView alterarNoticias(@Valid Noticias noticia) {
        ModelAndView mv = new ModelAndView("/ListarNoticias");
        try {
            notRepo.save(noticia);            
            Iterable<Noticias> noticias = notRepo.findAll();
            mv.addObject("noticias", noticia);
            mv.addObject("mensagemSucesso", "Dados alterados com sucesso!");
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível alterar o registro");
        }
        return mv;
    }
    */
    @RequestMapping(value = "/ExcluirNoticias", method = RequestMethod.GET)
    public String redirecionarExcluirNoticias() {
        return "/ListarNoticias";
    }
    
    public ModelAndView formularioExcluirNoticias(int id) {
        ModelAndView mv = new ModelAndView("/ExcluirNoticias");
        try {
              Noticias noticias = notRepo.findById(id);
            mv.addObject("noticias", noticias);
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }

    @RequestMapping(value = "/ExcluirNoticias", method = RequestMethod.POST)
    public ModelAndView excluirNoticias(@Valid Noticias noticia) {
        ModelAndView mv = new ModelAndView("/ListarNoticias");
        try {
            notRepo.delete(noticia);
            Iterable<Noticias> noticias = notRepo.findAll();
            mv.addObject("noticias", noticias);
            mv.addObject("mensagemSucesso", "Registro excluído!");
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível excluir o registro");
        }
        return mv;
    }

    @RequestMapping(value = "/ListarNoticias", method = RequestMethod.GET)
    public ModelAndView listarNoticias() {
        ModelAndView mv = new ModelAndView("/ListarNoticias");
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

}