package br.com.fatecpp.projetosite.repository;

import br.com.fatecpp.projetosite.entidades.Dono;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonoRepository 
        extends CrudRepository<Dono, String>{
    
    public Dono findById(int id);
    
}
