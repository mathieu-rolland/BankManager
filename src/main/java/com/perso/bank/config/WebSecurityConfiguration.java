package com.perso.bank.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.cors()
        .and()
            .headers()
            .frameOptions()
            .disable()
        .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            // by default uses a Bean by the name of corsConfigurationSource
            .and()
            	.csrf().disable();
    }
	
	@Bean
	@Profile( value = { ApplicationProfile.DEV , ApplicationProfile.DEV_LOCAL , ApplicationProfile.PROD} )
	public CorsConfigurationSource corsConfigurationSource() throws Exception {
		System.out.println("Test MRO");
		CorsConfiguration corsConfig = new CorsConfiguration();
		//corsConfig.setAllowedOrigins(Arrays.asList("*"));
		//corsConfig.setAllowedMethods(Arrays.asList("*"));
		corsConfig.addAllowedHeader("*");
		corsConfig.addAllowedMethod("*");
		corsConfig.addAllowedOrigin("*");
//		corsConfig.applyPermitDefaultValues();
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);
        return source;
	}
	
}
