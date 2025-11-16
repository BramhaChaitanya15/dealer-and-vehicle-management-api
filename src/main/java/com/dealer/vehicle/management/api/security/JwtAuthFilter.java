package com.dealer.vehicle.management.api.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// get token
		String requestToken = request.getHeader("Authorization");
		String username = null;
		String token = null;

		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			System.out.println(token);
			System.out.println(requestToken);
			try {
				username = this.jwtUtil.extractUsername(token);
			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt");
			} catch (ExpiredJwtException e) {
				System.out.println("jwt expired");
			} catch (MalformedJwtException e) {
				System.out.println("invalid jwt");
			}
		} else {
			System.out.println("does not begin with Bearer");
		}

		// validate token
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

			if (this.jwtUtil.validateToken(token, userDetails)) {
				// authentication

				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userDetails, null,
						userDetails.getAuthorities());
				WebAuthenticationDetailsSource wads = new WebAuthenticationDetailsSource();
				upat.setDetails(wads.buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(upat);
			} else {
				System.out.println("invalid jwt token");
			}

		} else {
			System.out.println("username is null or context is not null");
		}

		filterChain.doFilter(request, response);

	}

	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) {
		String path = request.getServletPath();
		return path.startsWith("/api/v1/auth/");
	}

}
