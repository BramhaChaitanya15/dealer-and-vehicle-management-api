package com.dealer.vehicle.management.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.dealer.vehicle.management.api.security.JwtAuthFilter;

import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(auth -> auth
						.requestMatchers("/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()//pass all swagger endpoints
						.requestMatchers("/api/payment/**").authenticated().anyRequest().permitAll())//authenticate login for payment
				.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	//cors filter configurations
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfig = new CorsConfiguration();

		corsConfig.setAllowCredentials(true);
		corsConfig.addAllowedOriginPattern("*");
		corsConfig.addAllowedHeader("Authorization");
		corsConfig.addAllowedHeader("Content-Type");
		corsConfig.addAllowedHeader("Accept");
		corsConfig.addAllowedMethod("POST");
		corsConfig.addAllowedMethod("GET");
		corsConfig.addAllowedMethod("DELETE");
		corsConfig.addAllowedMethod("PUT");
		corsConfig.addAllowedMethod("OPTIONS");
		corsConfig.setMaxAge(3600L);

		source.registerCorsConfiguration("/**", corsConfig);

		FilterRegistrationBean<CorsFilter> frBean = new FilterRegistrationBean<>(new CorsFilter(source));

		frBean.setOrder(-110);

		return frBean;
	}
}