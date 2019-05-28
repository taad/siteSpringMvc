package br.com.fatecpp.projetosite.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



/**
 *
 * @author tadeu
 */


    @Entity
    @Table(name = "usuario")
   public class Usuario implements UserDetails, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String email;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
   
    
    @ManyToMany
    @JoinTable(
            name= "usuario_role",
            joinColumns = @JoinColumn(
                        name = "codigo_usuario",
                        referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "codigo_role",
                    referencedColumnName = "codigoRole"))
    
    
    
     private List<Role> roles;
    




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
            
    

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

  

    public void setPassword(String password) {
        this.password = (new BCryptPasswordEncoder().encode(password)) ;
    }

    public String getPassword() {
        return this.password;
    }
    


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
    return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

  
    
    
    
}
