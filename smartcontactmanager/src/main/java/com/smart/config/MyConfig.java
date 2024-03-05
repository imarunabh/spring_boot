package com.smart.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MyConfig {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoauthenticationProvider1() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	

	// configure method
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
			throws Exception {
		return configuration.getAuthenticationManager();
	}
	
	@SuppressWarnings("removal")
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//	  http.authorizeHttpRequests()
//	      .requestMatchers("/admin/**").hasRole("ADMIN")
//	      .requestMatchers("/user/**").hasRole("USER")
//	      .requestMatchers("/**").permitAll()
//	      .and().formLogin().and().csrf().disable();
//	  http.formLogin().defaultSuccessUrl("/user/index", true);
//	  return http.build();
		
		
//		http.csrf(AbstractHttpConfigurer::disable)
//		
//		.authorizeHttpRequests(request->
//		
//		request.requestMatchers("/admin/**").hasRole("ADMIN")
//		.requestMatchers("/user/**").hasRole("USER")
//		.requestMatchers("/**").permitAll().anyRequest().authenticated()).formLogin(formLogin ->
//         formLogin.defaultSuccessUrl("/user/index", true)
//        .permitAll()
//  );
		
		 http.csrf(AbstractHttpConfigurer::disable)
         .authorizeHttpRequests(authorizeRequests ->
             authorizeRequests
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/user/**").hasRole("USER")
                 .requestMatchers("/**").permitAll().anyRequest().authenticated()
         )
         .formLogin().loginPage("/signin").loginProcessingUrl("/dologin")
         .defaultSuccessUrl("/user/index");

  http.authenticationProvider(daoauthenticationProvider1());
//     return http.build();
		
		
//		  http.authorizeHttpRequests()
//	      .requestMatchers("/admin/**").hasRole("ADMIN")
//	      .requestMatchers("/user/**").hasRole("USER")
//	      .requestMatchers("/**").permitAll()
//	      .and().formLogin().and().csrf().disable();
//	  http.formLogin().defaultSuccessUrl("/user/index", true);
	  
		
//		return http.build();
		
		
//		http.csrf().disable()
//		.authorizeHttpRequests()
//		.requestMatchers("/admin/**").hasRole("ADMIN")
//		.requestMatchers("/user/**").hasRole("USER")
//		.requestMatchers("/**")
//		.permitAll()
//		.anyRequest()
//		.authenticated()
//		.and().formLogin()
//		.defaultSuccessUrl("/user/index")
//		.defaultSuccessUrl("/user/index");
		return http.build();
		
	}


}