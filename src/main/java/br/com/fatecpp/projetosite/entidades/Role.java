
package br.com.fatecpp.projetosite.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="roles")
public class Role implements GrantedAuthority, Serializable {
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int codigoRole;
    @NotEmpty
    private String funcao;
    @ManyToMany(mappedBy = "roles")
    private List<Usuario> usuarios;

    @Override
    public String getAuthority() {
         return this.funcao;
    }

    public int getCodigoRole() {
        return codigoRole;
    }

    public void setCodigoRole(int codigoRole) {
        this.codigoRole = codigoRole;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
 
}