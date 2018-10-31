package ufc.cmu.promocity.backend;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import ufc.cmu.promocity.backend.controller.BookController;
import ufc.cmu.promocity.backend.controller.StoreController;
import ufc.cmu.promocity.backend.controller.UserController;

/**
 * Faz a configuracao do Servico Jersey
 * @author armandosoaressousa
 * Agradecimento a https://dzone.com/articles/spring-boot-building-restful-web-services-with-jersey
 */
@Configuration
@ApplicationPath("promocity")
public class JerseyConfiguration extends ResourceConfig {
	public JerseyConfiguration() {
		
	}
	
	/**
	 * Faz os registros dos controllers
	 */
	@PostConstruct
	public void setUp() {
		register(BookController.class);
		register(UserController.class);
		register(StoreController.class);
		register(GenericExceptionMapper.class);
	}
}