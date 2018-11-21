package ufc.cmu.promocity.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.*;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * Baseado nos tutoriais https://github.com/eugenp/tutorials
 * @author armandosoaressousa
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private CustomAccessDeniedHandler accessDeniedHandler;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private MySavedRequestAwareAuthenticationSuccessHandler mySuccessHandler;

    private SimpleUrlAuthenticationFailureHandler myFailureHandler = new SimpleUrlAuthenticationFailureHandler();
	
    //Create BCryptPassword encoder
    PasswordEncoder encoder = new BCryptPasswordEncoder();
	
    
    /**
     * Create users with different roles in SecurityJavaConfig that we will be using to authenticate our API endpoints:
     */
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
	    auth.inMemoryAuthentication()
	        .withUser("admin").password(encoder.encode("adminPass")).roles("ADMIN")
	        .and()
	        .withUser("user").password(encoder.encode("userPass")).roles("USER");
	}
	
	/**
	 * configure security for our API endpoints:
	 */
	@Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .and()
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(restAuthenticationEntryPoint)
            .and()
            .authorizeRequests()
            .antMatchers("/promocity/stores/**").permitAll()
            .antMatchers("/promocity/promotions/**").authenticated()
            .antMatchers("/promocity/users/**").hasRole("ADMIN")
            .and()
            .formLogin()
            .successHandler(mySuccessHandler)
            .failureHandler(myFailureHandler)
            .and()
            .httpBasic()
            .and()
            .logout();           
    }
	 
}
