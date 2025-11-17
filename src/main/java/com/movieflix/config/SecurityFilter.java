package com.movieflix.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain){
        String authorizationHeader = httpServletRequest.getHeader("Authorization");
        if(Strings.isNotEmpty(authorizationHeader) && authorizationHeader.startsWith("Bearer ")){
            String token = authorizationHeader.substring("Bearer ".length());

            Optional<JWTUserData> optjwtUserData = tokenService.verifyToken(token);
            if(optjwtUserData.isPresent()){
                  JWTUserData jwtUserData = optjwtUserData.get();

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken( jwtUserData, null, null);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }



            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
        else
            filterChain.doFilter(httpServletRequest,httpServletResponse);



    }

}
