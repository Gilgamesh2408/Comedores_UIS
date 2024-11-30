package com.uis.ComedoresUIS.auth.filters;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.uis.ComedoresUIS.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class JwtTokenValidator extends OncePerRequestFilter {

    private JwtUtils jwtUtils;

    public JwtTokenValidator(JwtUtils jwtUtil) {
        this.jwtUtils = jwtUtil;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        /* Get the token from the request header */
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(token != null && token.startsWith("Bearer ")) {

            token = token.substring(7);

            DecodedJWT decodedJWT = jwtUtils.validateToken(token);

            String codeAdmin = decodedJWT.getSubject();
            String role = decodedJWT.getClaim("role").asString();

            Collection<? extends GrantedAuthority> authorities = AuthorityUtils.
                    commaSeparatedStringToAuthorityList(role);

            SecurityContext contextHolder = SecurityContextHolder.createEmptyContext();
            Authentication authentication = new UsernamePasswordAuthenticationToken(codeAdmin, token, authorities);
            contextHolder.setAuthentication(authentication);
            SecurityContextHolder.setContext(contextHolder);

        }

        doFilter(request, response, filterChain);
    }
}
