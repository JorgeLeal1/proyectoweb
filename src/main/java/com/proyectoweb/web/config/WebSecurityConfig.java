package com.proyectoweb.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {

	@Value("${spring.security.user.name}")
	private String username;
	
	@Value("${spring.security.user.password}")
	private String pass;
	
	@Value("${spring.security.user.roles}")
	private String roles;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

         /*
		http.authorizeHttpRequests(request -> request
					.requestMatchers("/index","/").permitAll()
    				.anyRequest().authenticated()
    				)
			.formLogin(form -> form.loginPage("/index").permitAll());
			 
         return  http.build();
		*/
         
         
        return http
    		.authorizeHttpRequests(request -> request
    				.anyRequest()
    				.authenticated()
				)
        	.httpBasic(Customizer.withDefaults())
        	.build();
        		
		//return http.build();
		
    }

	//Permite todas las url
	
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().anyRequest();
    }
    
    

    //Filtrar las url
	@Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
	
	
}




/*
@Bean
public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
	
    UserDetails user = User.withUsername(username)
        //.password("{noop}"+pass)				//password SIN encriptar
        .password(passwordEncoder.encode(pass)) //se encripta el password
		.roles(roles)
        .build();

    UserDetails admin = User.withUsername("admin")
            //.password("{noop}"+pass)				//password SIN encriptar
            .password(passwordEncoder.encode("admin")) //se encripta el password
    		.roles("ADMIN","USER")
            .build();
    
    return new InMemoryUserDetailsManager(user,admin);
    
}

*/











