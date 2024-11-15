package com.JWT.JWT.Authnetication.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    Logger logger = LoggerFactory.getLogger(JwtAuthFilter.class);
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");


        logger.info("Header info {}",authorization);
         String username = null;
         String token  =null;

         if(authorization != null && authorization.startsWith("Bearer")) {
             token = authorization.substring(7);
             try {
                 username = jwtHelper.getUserNameFromToken(token);
                 logger.info("Token username {}", username);
             } catch (IllegalArgumentException e) {
                 e.printStackTrace();
                 logger.info("illegal argument while fetching the argument " + e.getMessage());
             } catch (ExpiredJwtException ex) {
                 logger.info("Given JWT is Expired " + ex.getMessage());
             } catch (MalformedJwtException ex) {
                 logger.info("Some changed has done in token " + ex.getMessage());
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
         }
         else {
                logger.info("Invalid Header");
         }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //valid token
            if(username.equals(userDetails.getUsername()) && !jwtHelper.isTokenExpired(token)){
                //security contect ke andar authentication set karenge

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        };
        filterChain.doFilter(request,response);


    }
}
