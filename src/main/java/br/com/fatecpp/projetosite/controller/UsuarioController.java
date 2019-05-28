package br.com.fatecpp.projetosite.controller;

import br.com.fatecpp.projetosite.entidades.Role;
import br.com.fatecpp.projetosite.entidades.Usuario;

import br.com.fatecpp.projetosite.repository.UsuarioRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuRepo;
   

    @RequestMapping(value = "/NovoUsuario", method = RequestMethod.GET)
    public String formularioInserirUsuario()
    {
        /*ModelAndView mv = new ModelAndView();
        try{
        Iterable<Role> roles = roleRepo.findAll();
        mv.addObject("roles", roles);
        }catch(Exception e){
            System.out.println("Error: "+e);
        }*/
        return "/NovoUsuario" ;
    }
    
    @RequestMapping(value = "/NovoUsuario", method = RequestMethod.POST)
    public String novoUsuario(@Valid Usuario usuario, BindingResult results, RedirectAttributes attributes) {
        if (results.hasErrors()) {
            attributes.addFlashAttribute("mensagemErro", "Preencha corretamente os campos!");
        } else {
            try {
                usuRepo.save(usuario);
                attributes.addFlashAttribute("mensagemSucesso", "Dados registrados com sucesso!");
            } catch (Exception e) {
                attributes.addFlashAttribute("mensagemErro", "Não foi possível inserir o registro");
            }
        }
        return "redirect:/NovoUsuario";
    }
    
    @RequestMapping(value = "/{opcao}/{id}", method = RequestMethod.GET)
    public ModelAndView escolherFormularios(@PathVariable String opcao, @PathVariable int id) {
        if (opcao.equals("alterar")){
            return formularioAlterarUsuario(id);
        } else if (opcao.equals("excluir")){
            return formularioExcluirUsuario(id);
        } else {
            return null;
        }
    }
    
    @RequestMapping(value = "/AlterarUsuario", method = RequestMethod.GET)
    public String redirecionarAlterarDono() {
        return "/ListarUsuarios";
    }
    
    public ModelAndView formularioAlterarUsuario(int id) {
        ModelAndView mv = new ModelAndView("/AlterarUsuario");
        try {
            Usuario usuario = usuRepo.findById(id);
            mv.addObject("usuario", usuario);
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }
    
    @RequestMapping(value = "/AlterarUsuario", method = RequestMethod.POST)
    public ModelAndView alterarUsuario(@Valid Usuario usuario) {
        ModelAndView mv = new ModelAndView("/ListarUsuario");
        try {
            usuRepo.save(usuario);            
            Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuario);
            mv.addObject("mensagemSucesso", "Dados alterados com sucesso!");
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível alterar o registro");
        }
        return mv;
    }
    
    @RequestMapping(value = "/ExcluirUsuario", method = RequestMethod.GET)
    public String redirecionarExcluirUsuario() {
        return "/ListarUsuario";
    }
    
    public ModelAndView formularioExcluirUsuario(int id) {
        ModelAndView mv = new ModelAndView("/ExcluirUsuario");
        try {
              Usuario usuario = usuRepo.findById(id);
            mv.addObject("usuario", usuario);
        } catch (Exception e) {
            System.out.println(e);
        }
        return mv;
    }

    @RequestMapping(value = "/ExcluirUsuario", method = RequestMethod.POST)
    public ModelAndView excluirUsuario(@Valid Usuario usuario ) {
        ModelAndView mv = new ModelAndView("/ListarUsuario");
        try {
            usuRepo.delete(usuario);
            Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuarios);
            mv.addObject("mensagemSucesso", "Registro excluído!");
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível excluir o registro");
        }
        return mv;
    }

    @RequestMapping(value = "/ListarUsuario", method = RequestMethod.GET)
    public ModelAndView listarUsuario() {
        ModelAndView mv = new ModelAndView("/ListarUsuario");
        try {
            Iterable<Usuario> usuarios = usuRepo.findAll();
             
            mv.addObject("usuarios", usuarios);
            
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível listar os registros!");
        }
        return mv;
    }
    
    
        @RequestMapping(value = "/Dashboard", method = RequestMethod.GET)
    public ModelAndView listarUsuarionadash() {
        ModelAndView mv = new ModelAndView("/Dashboard");
        try {
            Iterable<Usuario> usuarios = usuRepo.findAll();
            mv.addObject("usuarios", usuarios);
             mv.addObject("mensagemSucesso", "Bem vindo!");
             
        } catch (Exception e) {
            mv.addObject("mensagemErro", "Não foi possível listar os registros!");
        }
        return mv;
    }
    
        
    @ControllerAdvice
public class CurrentUserControllerAdvice {
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication) {
        return (authentication == null) ? null : (UserDetails) authentication.getPrincipal();
    }
}
    

}