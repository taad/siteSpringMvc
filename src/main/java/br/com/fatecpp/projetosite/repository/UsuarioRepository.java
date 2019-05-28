
package br.com.fatecpp.projetosite.repository;

import br.com.fatecpp.projetosite.entidades.Usuario;
import org.springframework.data.repository.CrudRepository;


/**
 *
 * @author tadeu
 */

public interface UsuarioRepository 
         extends CrudRepository<Usuario, String>{
    
    
    Usuario findById(int id);
    Usuario findByNome(String nome);
   
}