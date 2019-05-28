
package br.com.fatecpp.projetosite.repository;

import br.com.fatecpp.projetosite.entidades.Noticias;
import org.springframework.data.repository.CrudRepository;


public interface NoticiasRepository 
    extends CrudRepository<Noticias, String>{
    
    public Noticias findById(int id);
}
