
package br.com.fatecpp.projetosite.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;



@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private ImplementsUserDetailsService userDetailsService;
    
    @Override
    protected void configure (HttpSecurity http) throws Exception{
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/NovaNoticia").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/NovaNoticia").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/AlterarNoticia").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/AlterarNoticia").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ExcluirNoticias").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/ExcluirNoticias").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/ListarUsuario").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/ListarUsuario").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/NovoUsuario").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/NovoUsuario").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/Dashboard").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/Dashboard").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/index2").permitAll()
                .antMatchers(HttpMethod.GET, "/novocabecalho").permitAll()
                .antMatchers(HttpMethod.GET, "/novorodape").permitAll()
                .antMatchers(HttpMethod.GET, "/carrossel").permitAll()
                .antMatchers(HttpMethod.GET, "/post").permitAll()
                .antMatchers(HttpMethod.GET, "/sobre").permitAll()
                .antMatchers(HttpMethod.GET, "/cabecalhosobre").permitAll()
                .antMatchers(HttpMethod.GET, "/cabecalhoconta").permitAll()
                .antMatchers(HttpMethod.GET, "/cabecalhonoticias").permitAll()
               
                .antMatchers(HttpMethod.GET, "/participe").permitAll()
                .antMatchers(HttpMethod.POST, "/participe").permitAll()
                .antMatchers(HttpMethod.GET, "/aprenda").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutRequestMatcher(
                new AntPathRequestMatcher("/logout"));
               
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
            auth.userDetailsService(userDetailsService)
            .passwordEncoder(new BCryptPasswordEncoder());
    
}
    
    //  <script th:src="@{/jquery.min.js}"></script>
//    <script th:src="@{/bootstrap.bundle.min.js}"></script>

    //<!-- Custom scripts for this template -->
  ///  <script th:src="@{/clean-blog.min.js}"></script>
//<script th:src="@{/slick.js}"></script>
    @Override
   public void configure(WebSecurity web) throws Exception{
       web.ignoring().antMatchers("/resourses/**","/static/**",
               "/font-awesome.css.map/**",
               "/bootstrap.css/**","/bootstrap.css.map/**","bootstrap.min.css.map/**",
               "/font-awesome.css/**",
               "/font-awesome.min.css/**",
               "/fontawesome-webfont/**",
               "/bootstrap.min.css/**","/logo.png/**","/noticias.jpg/**",
               "/jquery.min.js/**","/bootstrap.bundle.min.js/**",
               "/clean-blog.min.js/**","/slick.js/**",
               "/clean-blog.css/**","/slick.css/**","/src/main/resources/static/**",
               "/webjars/**","/**/favicon.ico","/sobre1.jpg/**","/sobre2.jpg/**");
   }
}