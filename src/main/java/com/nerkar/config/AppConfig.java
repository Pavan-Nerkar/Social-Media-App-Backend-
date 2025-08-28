package com.nerkar.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.tomcat.util.file.ConfigurationSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class AppConfig {     //Config class is liye ki konse end ponts ko security dena hai

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		http.sessionManagement(management -> management.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/api/**").authenticated()
				.anyRequest().permitAll())
		.addFilterBefore(new jwtValidator(), BasicAuthenticationFilter.class)
				.csrf(csrf -> csrf.disable())
				.cors(cors ->cors.configurationSource(corsConfigurationSource()));
		
		//jwtVAlidator pahile check karega user valid hai then api/** url ke pass bhejega
		
		return http.build();
	}
	
	private CorsConfigurationSource corsConfigurationSource() {
	
	return new CorsConfigurationSource() {
		
		@Override
		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
			
			CorsConfiguration cfg = new CorsConfiguration();
			cfg.setAllowedOrigins(Arrays.asList(
					"http://localhost:3000",
					"https://pavannerkar-socialmedia.vercel.app"
					));
			cfg.setAllowedMethods(Collections.singletonList("*"));
			cfg.setAllowCredentials(true);
			cfg.setAllowedHeaders(Collections.singletonList("*"));
			cfg.setExposedHeaders(Arrays.asList(
					"Authorization"));
			cfg.setMaxAge(3600L);
			
			return cfg;
		}
	};
}

	@Bean
	PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
	
}
