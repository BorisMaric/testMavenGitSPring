package fr.bonplans.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure");
		http
		.authorizeRequests()
		.antMatchers("/","/Accueil","/Inscription").permitAll()
		.anyRequest().authenticated();
		http
		.formLogin()
		.defaultSuccessUrl("/ok")
		.loginPage("/Connexion")
		.permitAll()
		.and()
		.logout()
		.permitAll();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		//authManagerBuilder.jdbcAuthentication().usersByUsernameQuery("SELECT email,password FROM utilisateur WHERE email = ?").authoritiesByUsernameQuery("SELECT email,role FROM utilisateur WHERE email = ?");
		System.out.println("configure2");
		authManagerBuilder.inMemoryAuthentication()
		.withUser("user").password("password").roles("USER");
	}
}