package com.nerkar.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// hum jab secure end points ko excess karenge tab header me tooken dege.

		String jwt = request.getHeader(JwtConstant.JWT_HEADER); // header se tooken nikala hai

		if (jwt != null && jwt.startsWith("Bearer ")) {
			try {
				String pureToken = jwt.substring(7); // ✅ Remove "Bearer " prefix

				String email = JwtProvider.getEmailFromJwtToken(pureToken); // ✅ Only token passed

				// String email = JwtProvider.getEmailFromJwtToken(jwt); //jwt pass kiya wo upar
				// wale line 22 se aya with (Bearer token) ke sath Bearer remove kar ke jwt me
				// se email milega

				List<GrantedAuthority> authorities = new ArrayList<>();

				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);

				SecurityContextHolder.getContext().setAuthentication(authentication);

			} catch (Exception e) {
				e.printStackTrace();
				throw new BadCredentialsException("Invalid token...");
			}
		}

		filterChain.doFilter(request, response);

	}

}
