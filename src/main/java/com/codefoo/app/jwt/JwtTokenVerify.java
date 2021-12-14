package com.codefoo.app.jwt;

import static com.codefoo.app.constant.ExceptionMessageConstant.TOKEN_INVALID;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenVerify extends OncePerRequestFilter {

	private JwtConfig jwtConfig;
	private SecretKey secretKey;

	@Autowired
	public JwtTokenVerify(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader(this.jwtConfig.getAuthorizationHeaders());

		if (Strings.isNullOrEmpty(authorizationHeader)
				|| !authorizationHeader.startsWith(this.jwtConfig.getTokenPrefix())) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authorizationHeader.replace(this.jwtConfig.getTokenPrefix(), "");
		try {

			Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(this.secretKey).build().parseClaimsJws(token);

			Claims body = claimsJws.getBody();

			String username = body.getSubject();

			List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

			Set<SimpleGrantedAuthority> simpleGrantedAuthorities = authorities.stream()
					.map(p -> new SimpleGrantedAuthority(p.get("authority"))).collect(Collectors.toSet());

			Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
					simpleGrantedAuthorities);

			SecurityContextHolder.getContext().setAuthentication(authentication);

		} catch (JwtException e) {
			throw new IllegalArgumentException(TOKEN_INVALID);
		}

		filterChain.doFilter(request, response);

	}

}
