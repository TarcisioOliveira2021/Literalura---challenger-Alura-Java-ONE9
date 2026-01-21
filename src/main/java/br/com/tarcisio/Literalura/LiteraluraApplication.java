package br.com.tarcisio.Literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.com.tarcisio.Literalura.principal.Principal;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(LiteraluraApplication.class, args);
		Principal principal = ctx.getBean(br.com.tarcisio.Literalura.principal.Principal.class);
		principal.exibeMenu();
	}
}
