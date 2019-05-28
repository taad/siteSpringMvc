
package br.com.fatecpp.projetosite.controller;

import br.com.fatecpp.projetosite.entidades.Noticias;
import br.com.fatecpp.projetosite.entidades.Usuario;
import br.com.fatecpp.projetosite.repository.NoticiasRepository;
import br.com.fatecpp.projetosite.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author tadeu
 */
@Controller
public class DashboardController {
    
    
    @Autowired
    private  NoticiasRepository notRepo;
    
    @Autowired
    private UsuarioRepository usuRepo;
    
    
        


}
