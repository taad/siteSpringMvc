package br.com.fatecpp.projetosite.security;

import br.com.fatecpp.projetosite.entidades.Usuario;
import br.com.fatecpp.projetosite.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {
    @Autowired
    private UsuarioRepository ur;

    @Override
    public UserDetails loadUserByUsername(String nome) throws UsernameNotFoundException {
        Usuario usuario = ur.findByNome(nome);
        if(usuario == null)
            throw new UsernameNotFoundException("Usuario não encontrado!");
        return new User(usuario.getUsername(),
            usuario.getPassword(),
                true,true,true,true,
                        usuario.getAuthorities());
                
            
    }
    
}