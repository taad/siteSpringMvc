package br.com.fatecpp.projetosite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
public class ProjetositeMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetositeMvcApplication.class, args);
               //System.out.print(new BCryptPasswordEncoder().encode("123"));
               System.out.print(new BCryptPasswordEncoder().encode("1234"));
	}
}
